package fr.branch.git.service;

import java.util.Collection;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import com.google.common.collect.Lists;

import fr.branch.git.dto.Branch;
import fr.branch.git.dto.User;
import fr.branch.git.info.UserInfo;
import fr.branch.git.utils.BitbucketUtils;

import static fr.branch.git.info.UserInfo.pseudo;

@Service
public class GetBranchService extends BranchService {
	/**
	 *
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GetBranchService.class);
	/**
	 *
	 */
	private static final String BRANCHS_GET_ERREUR = "Erreur récupérer les branches d'une repositories";
	/**
	 *
	 */
	@Autowired
	private AuthentificationService authentification;

	/**
	 * @param repositoriesName le nom de controller.
	 * @return body de l'entité ,contient tous les branches d'un controller.
	 */
	public String getBranchEntityBody(String repositoriesName, User user) {

		if (StringUtils.isEmpty(repositoriesName)) {
			LOG.warn("repositoriesName cannot be emty or null");
		}

		String authentificationJsonString = authentification.getToken(user);
		String token = BitbucketUtils.getAccessToken(authentificationJsonString);

		final String url =
				UserInfo.BITBUCKET_API_URL + pseudo + "/" + repositoriesName + UserInfo.BRANCHES_URL + "?access_token=" + token;

		HttpHeaders headers = new HttpHeaders();
		HttpEntity requestEntity = new HttpEntity(headers);

		LOG.info("start call service API bitbucket : getBranchEntityBody ");

		try {

			return getRessource(url, requestEntity, HttpMethod.GET);

		} catch (HttpClientErrorException e) {
			LOG.error("Erreur delete méthode :getBranchEntityBody : {}", e);
			throw new IllegalArgumentException(BRANCH_ERREUR);
		}

	}

	/**
	 * @param repositoriesName le nom de controller.
	 * @param branchName       le nom de la branche.
	 * @return le body de l'entité ,contient tous les commits d'une branche.
	 */
	public String getCommitBranchBody(String repositoriesName, String branchName, User user) {
		if (StringUtils.isEmpty(repositoriesName)) {
			LOG.warn("repositoriesName cannot be emty or null");
		}

		String authentificationJsonString = authentification.getToken(user);
		String token = BitbucketUtils.getAccessToken(authentificationJsonString);

		final String url = UserInfo.BITBUCKET_API_URL + pseudo + "/" + repositoriesName + UserInfo.COMMITS_URL + branchName
				+ "?access_token=" + token;

		HttpHeaders headers = new HttpHeaders();
		HttpEntity requestEntity = new HttpEntity(headers);

		LOG.info("start call service API bitbucket : getCommitBranchBody ");

		try {

			return getRessource(url, requestEntity, HttpMethod.GET);

		} catch (HttpClientErrorException e) {
			LOG.error("Erreur delete méthode :getBranchEntityBody : {}", e);
			throw new IllegalArgumentException(BRANCHS_GET_ERREUR);
		}

	}

	/**
	 * @param repositoriesName le nom de repositories.
	 * @param user             objet user contient : userName , password , key , secret.
	 * @return lister les branches de repositories.
	 */
	public Collection<Branch> getBranches(String repositoriesName, User user) {

		if (StringUtils.isEmpty(repositoriesName)) {
			LOG.warn("repositoriesName cannot be emty or null");
			return null;
		}
		String bodyResponceBranch = "";
		try {
			bodyResponceBranch = getBranchEntityBody(repositoriesName, user);
		} catch (IllegalArgumentException e) {
			LOG.error("Erreur récuperer les branch de repositories : getBranchEntityBody : {}", e);
			throw new IllegalArgumentException(BRANCHS_GET_ERREUR);
		}

		Collection<String> branchsNames = BitbucketUtils.getNamesBranchs(bodyResponceBranch);
		Collection<Branch> branches = new Vector<Branch>();
		int annee = 0, release = 0, sprint = 0;
		Branch branch = null, recentBranch = null;
		for (String branchName : branchsNames) {

			String bodyResponceCommit = getCommitBranchBody(repositoriesName, branchName, user);

			try {
				// la date en string la premiére commit .
				String firstCommitInBranchDate = BitbucketUtils.getFirstCommitInBranchDate(bodyResponceCommit);
				// la date en string la dernier commit .
				String lastCommitInBranchDate = BitbucketUtils.getLastCommitInBranchDate(bodyResponceBranch, branchName);
				// auteur : la personne commité.
				String author = BitbucketUtils.getAuthorLastCommitInBranchDate(bodyResponceBranch, branchName);
				// les commits d'une branche
				String commitsOfBranch = BitbucketUtils.getCommitsInBranch(bodyResponceCommit);

				String name = BitbucketUtils.getLocalUser("name");
				String email = BitbucketUtils.getLocalUser("email");

				boolean isMaBranch = (name + " <" + email + ">").equals(author) ? true : false;

				branch = new Branch.BranchBuilder(branchName).creationDate(firstCommitInBranchDate)
															 .lastCommitDate(lastCommitInBranchDate)
															 .author(author)
															 .commits(commitsOfBranch)
															 .maBranch(isMaBranch)
															 .build();
				branches.add(branch);

			} catch (IllegalArgumentException e) {
				LOG.warn(BitbucketUtils.COMMIT_NOT_FIND);
				branch = new Branch.BranchBuilder(branchName).creationDate("")
															 .lastCommitDate("")
															 .author("Branche vide")
															 .commits("")
															 .maBranch(false)
															 .build();
				branches.add(branch);
			}
		}

		branches.add(new Branch("toto/17/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/19/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto-8-1-9", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto-19-1-4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/11/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/19/1/4", "dateCreation", "lastCommit", "author", "commit"));
		return BitbucketUtils.getBranchesTrie(Lists.newArrayList(branches));
	}
}
