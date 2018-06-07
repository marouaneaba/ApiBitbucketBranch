package fr.branch.git.controller.authentification;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.branch.git.dto.User;
import fr.branch.git.info.UserInfo;
import fr.branch.git.service.AuthentificationService;
import fr.branch.git.utils.BitbucketUtils;

import static fr.branch.git.controller.repositories.RepositoriesController.BRANCHES_REDIRECT;

/**
 *
 */
@Controller
public class AuthentificationController {
	/**
	 * la constant LOG
	 */
	final private static Logger LOG = LoggerFactory.getLogger(AuthentificationController.class);
	final private static String LOGIN_REDIRECT = "redirect:/login";
	final private static String AUTHENTIFICATION = "authentification";
	/**
	 * service d'authentification
	 */
	@Autowired
	private AuthentificationService authentificationService;

	/**
	 * @return formulaire d'authentification
	 */
	@GetMapping("/login")
	public String getAuthentification() {

		return AUTHENTIFICATION;
	}

	/**
	 * @return list des branches de repositories.
	 */
	@PostMapping("/login")
	public String postAuthentification(@ModelAttribute User user, HttpServletRequest request) {

		/**/
		user.setKey(UserInfo.getKey());
		user.setSecret(UserInfo.getSecret());
		user.setUserName(UserInfo.getUserName());
		user.setPassword(UserInfo.getPassword());
		/**/

		request.getSession()
			   .setAttribute("user", user);

		String authentificationJsonString = authentificationService.getToken(user);

		try {
			BitbucketUtils.getAccessToken(authentificationJsonString);
		} catch (IllegalArgumentException e) {
			LOG.error("Erreur d'Authentification de Systeme : {}", e);
			return LOGIN_REDIRECT;
		}

		return BRANCHES_REDIRECT;
	}

}
