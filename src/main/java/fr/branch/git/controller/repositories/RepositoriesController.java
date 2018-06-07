package fr.branch.git.controller.repositories;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import fr.branch.git.dto.Branch;
import fr.branch.git.dto.User;
import fr.branch.git.service.DeleteBranchService;
import fr.branch.git.service.GetBranchService;

@Controller
public class RepositoriesController {

	/** la Constante LOG. */
	private final static Logger LOG = LoggerFactory.getLogger(RepositoriesController.class);
	final public static String BRANCHES_REDIRECT = "redirect:/branches";
	/**
	 * récupérer les Branches Service.
	 */
	@Autowired
	private GetBranchService getBranchService;
	/**
	 * supprimer les branches service.
	 */
	@Autowired
	private DeleteBranchService deleteBranchService;

	/**
	 * @param repositoriesName le nom de repositories.
	 * @param request          request
	 * @param model            model
	 * @return les branches d'une repositories.
	 */
	@GetMapping(value = "/branches")
	public String getAllBranches(@RequestParam(value = "repositoriesName", defaultValue = "") String repositoriesName,
			HttpServletRequest request, Model model) {

		if (StringUtils.isEmpty(repositoriesName)) {
			LOG.warn("controller name cannot be empty or null");
		}

		request.getSession()
			   .setAttribute("repositorieName", repositoriesName);

		final User user = (User) request.getSession()
										.getAttribute("user");
		Collection<Branch> branches = null;
		try {
			branches = getBranchService.getBranches(repositoriesName, user);

		} catch (IllegalArgumentException | HttpClientErrorException e) {
			model.addAttribute("messageErreur", true);
			model.addAttribute("message", "le nom de répositories non trouvé.");

			return "brancheListe";
		}

		model.addAttribute("branches", branches);

		return "brancheListe";

	}

	/**
	 * @param request request.
	 * @return true si la branche a bien été supprimer sinon false.
	 */
	@DeleteMapping("/branche")
	public String deleteBranches(@RequestBody final String branchName, HttpServletRequest request, Model model) {

		final String repositoriesName = (String) request.getSession()
														.getAttribute("repositorieName");

		if (StringUtils.isEmpty(branchName) || StringUtils.isEmpty(repositoriesName)) {
			LOG.warn("repositoriesName and request cannot be emty or null");
		}

		final User user = (User) request.getSession()
										.getAttribute("user");
		try {
			deleteBranchService.deleteBranch(branchName, repositoriesName, user);
		} catch (IllegalArgumentException e) {
			LOG.error("Cannot delete the main branch ,or Branch not exist  : {}", branchName);
			model.addAttribute("messageErreur", true);
			model.addAttribute("message", "impossible de supprimer la branche.");

			return "404";
		}

		return "200";
	}

}
