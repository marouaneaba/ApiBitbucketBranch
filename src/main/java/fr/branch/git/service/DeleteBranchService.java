package fr.branch.git.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import fr.branch.git.dto.User;
import fr.branch.git.info.UserInfo;
import fr.branch.git.utils.BitbucketUtils;

@Service
public class DeleteBranchService extends BranchService {

	/**
	 * la constant LOG.
	 */
	final private static Logger LOG = LoggerFactory.getLogger(DeleteBranchService.class);
	final private static String NULL_ARGS = "Args cannot be empty or null";
	final private static String DELETE_BRANCH_ERREUR = "on peut pas supprimer la branche master , ou non trouver";
	/**
	 * service d'authentification.
	 */
	@Autowired
	private AuthentificationService authentificationService;

	/**
	 * @param branchName       nom de la branche.
	 * @param repositoriesName nom de repositories.
	 * @param user             Dto User.
	 * @return les nom des branchs non supprimer.
	 */
	public void deleteBranch(String branchName, String repositoriesName, User user) {

		if (StringUtils.isEmpty(branchName) || StringUtils.isEmpty(repositoriesName)) {
			LOG.warn(" BranchsNamesString où repositoriesName cannot be empty or null");
			throw new IllegalArgumentException(NULL_ARGS);
		}

		String authentificationJsonString = authentificationService.getToken(user);
		String token = BitbucketUtils.getAccessToken(authentificationJsonString);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity requestEntity = new HttpEntity(headers);

		final String url =
				UserInfo.BITBUCKET_API_URL + UserInfo.getPseudo() + "/" + repositoriesName + UserInfo.BRANCHES_URL + "/"
						+ branchName + "?access_token=" + token;

		LOG.info("start call service API bitbucket : getBranchEntityBody ");

		try {
			getRessource(url, requestEntity, HttpMethod.DELETE);

		} catch (IllegalArgumentException e) {
			LOG.error("Cannot delete the main branch ,or Branch not exist  : {}", branchName);
			throw new IllegalArgumentException(DELETE_BRANCH_ERREUR);
		}

	}
}
