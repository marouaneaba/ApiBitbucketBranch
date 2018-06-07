package fr.branch.git.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import fr.branch.git.dto.Branch;
import fr.branch.git.info.UserInfo;

import static fr.branch.git.info.UserInfo.SETTINGS_NAME;

public class BitbucketUtils {

	private static final Logger LOG = LoggerFactory.getLogger(BitbucketUtils.class);
	private static final String ERREUR_JSON_FORMAT = "Erreur json format";
	public static final String COMMIT_NOT_FIND = "Aucun commit trouver dans le repositories";
	public static final String EMPTY_BRANCHLIST = "branchList cannot be empty";
	public static final String INCORRECT_CONTENT = "le branch doit contenir au moins 3 '/' où 2 '-' ";

	/**
	 * @param stringjson une string json contient les noms des branches d'un controller.
	 * @return les noms des branches inclus dans un ficiher Json passer en paramétre
	 */
	public static Collection<String> getNamesBranchs(String stringjson) {

		Collection<String> branchesName = new Vector<String>();

		try {

			JSONObject json = new JSONObject(stringjson);
			JSONArray values = json.getJSONArray(UserInfo.VALUES);

			for (int i = 0; i < values.length(); i++) {
				JSONObject jsonObjectIndex = new JSONObject(values.getString(i));
				String branchName = (String) jsonObjectIndex.get(UserInfo.NAME);
				branchesName.add(branchName);
			}

		} catch (JSONException e) {

			LOG.error("Erreur json format :getNamesBranchs : {}", e);
			throw new IllegalArgumentException(ERREUR_JSON_FORMAT);//
		}

		return branchesName;
	}

	/**
	 * @param jsonString une string json contient les commits d'une branche.
	 * @param branchName nom de la branche.
	 * @return toute les commits d'une branch.
	 */
	public static String getLastCommitInBranchDate(String jsonString, String branchName) {

		try {

			JSONObject json = new JSONObject(jsonString);
			JSONArray values = json.getJSONArray(UserInfo.VALUES);

			for (int i = 0; i < values.length(); i++) {

				JSONObject jsonObjectIndex = new JSONObject(values.getString(i));
				if (jsonObjectIndex.get(UserInfo.NAME)
								   .equals(branchName)) {
					JSONObject target = jsonObjectIndex.getJSONObject(UserInfo.TARGET);
					JSONArray parents = target.getJSONArray(UserInfo.PARENTS);
					if (parents.length() > 0) {
						return BitbucketUtils.getDate((String) target.get(UserInfo.DATE));
					}
				}

			}
		} catch (JSONException e) {
			LOG.error("Erreur json format :getLastCommitInBranchDate : {}", e);
			throw new IllegalArgumentException(ERREUR_JSON_FORMAT);

		}

		throw new IllegalArgumentException(COMMIT_NOT_FIND);
	}

	/**
	 * @param jsonString une string json contient les commits d'une branche.
	 * @return la date de la premiére commit dans une branche.
	 */
	public static String getFirstCommitInBranchDate(String jsonString) {

		try {

			JSONObject json = new JSONObject(jsonString);
			JSONArray values = json.getJSONArray(UserInfo.VALUES);

			for (int i = values.length() - 1; i >= 0; i--) {
				JSONObject jsonObjectIndex = values.getJSONObject(i);

				if (jsonObjectIndex.getJSONArray(UserInfo.PARENTS)
								   .length() > 0) {
					String date = (String) jsonObjectIndex.get(UserInfo.DATE);

					return BitbucketUtils.getDate(date);
				}
			}
		} catch (JSONException e) {
			LOG.error("Erreur json format :getFirstCommitInBranchDate : {}", e);
			throw new IllegalArgumentException(ERREUR_JSON_FORMAT);

		}

		throw new IllegalArgumentException(COMMIT_NOT_FIND);
	}

	/**
	 * @param jsonString une string json contient les commits d'une branche.
	 * @param branchName le nom de la branche.
	 * @return l'utilisateur qui as créer la branche.
	 */
	public static String getAuthorLastCommitInBranchDate(String jsonString, String branchName) {

		try {

			JSONObject json = new JSONObject(jsonString);
			JSONArray values = json.getJSONArray(UserInfo.VALUES);

			for (int i = 0; i < values.length(); i++) {

				JSONObject jsonObjectIndex = new JSONObject(values.getString(i));
				if (jsonObjectIndex.get(UserInfo.NAME)
								   .equals(branchName)) {
					JSONObject target = jsonObjectIndex.getJSONObject(UserInfo.TARGET);
					JSONObject Author = target.getJSONObject(UserInfo.AUTHOR);
					return (String) Author.get(UserInfo.RAW);

				}

			}
		} catch (JSONException e) {
			LOG.error("Erreur json format :getAuthorLastCommitInBranchDate : {}", e);
			throw new IllegalArgumentException(ERREUR_JSON_FORMAT);
		}

		throw new IllegalArgumentException(COMMIT_NOT_FIND);
	}

	public static boolean isEmptyParent(String jsonString) {
		return true;
	}

	/**
	 * @param jsonString une string json contient les commits d'une branche.
	 * @return les commits d'une branche.
	 */
	public static String getCommitsInBranch(String jsonString) {

		Collection<String> commits = new Vector<>();
		// modifier la methode.
		try {

			JSONObject json = new JSONObject(jsonString);
			JSONArray values = json.getJSONArray(UserInfo.VALUES);

			for (int i = 0; i < values.length(); i++) {
				// verifier si le parent is emty --> master
				// abandonner.
				JSONObject jsonObjectIndex = new JSONObject(values.getString(i));

				JSONArray parnets = jsonObjectIndex.getJSONArray("parents");
				if (parnets.length() > 0) {
					JSONObject author = jsonObjectIndex.getJSONObject(UserInfo.AUTHOR);
					String authorName = (String) author.getJSONObject(UserInfo.USER)
													   .get(UserInfo.DISPLAY_NAME);

					commits.add(authorName);
				}

			}
		} catch (JSONException e) {
			LOG.error("Erreur json format :getCommitsInBranch : {}", e);
			throw new IllegalArgumentException(ERREUR_JSON_FORMAT);
		}

		return getNombreCommitsAuthor(commits);
	}

	/**
	 * @param commits les commits d'une branch
	 * @return nombre de commit dans une branch.
	 */
	public static String getNombreCommitsAuthor(Collection<String> commits) {

		StringBuilder stringBuilder = new StringBuilder();
		Set set = new HashSet<String>();
		for (String author : commits) {
			if (!set.contains(author)) {
				stringBuilder.append(author + " ( " + Collections.frequency(commits, author) + " )");
			}
			set.add(author);
		}
		return stringBuilder.toString();
	}

	/**
	 * @param stringJson une string json contient le token (connection aù controller).
	 * @return token
	 */
	public static String getAccessToken(String stringJson) {
		try {
			return (String) new JSONObject(stringJson).get(UserInfo.ACCESS_TOKEN);

		} catch (JSONException e) {
			LOG.error("erreur json format :getAccessToken : {}", e);
			throw new IllegalArgumentException(ERREUR_JSON_FORMAT);
		}

	}

	/**
	 * @param jsonStringDate une string date avec la format yyyy-MM-dd'T'hh:mm:ss.SSSZ
	 * @return une string date avec la format dd-MM-yyyy.
	 */
	public static String getDate(String jsonStringDate) {

		LocalDateTime date = LocalDateTime.parse(jsonStringDate, DateTimeFormatter.ISO_DATE_TIME);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UserInfo.FORMAT);
		return date.format(formatter);
	}

	/**
	 * @param nameProperties : le nom complet d'attribut
	 * @return valeur de l'attribute passé en paramétre.
	 */
	public static String getValueOfNameProperties(String nameProperties) {
		Properties properties = new Properties();

		try (InputStream in = BitbucketUtils.class.getClassLoader()
												  .getResourceAsStream(SETTINGS_NAME)) {
			properties.load(in);

		} catch (IOException e) {
			LOG.warn(" Name of properties is not existe : {}", e.getMessage());
		}

		return properties.getProperty(nameProperties);
	}

	/**
	 * @param keyString key identifé la donnée a récuperer.
	 * @return si la key est trouvé retourne la valeur de key passé en paramétre sinon null .
	 */
	public static String getLocalUser(String keyString) {

		HierarchicalINIConfiguration iniConfObj = null;
		try {
			iniConfObj = new HierarchicalINIConfiguration("L:" + File.separator + File.separator + ".gitconfig");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// Get Section names in ini file
		Set setOfSections = iniConfObj.getSections();
		Iterator sectionNames = setOfSections.iterator();

		while (sectionNames.hasNext()) {

			String sectionName = sectionNames.next()
											 .toString();

			SubnodeConfiguration sObj = iniConfObj.getSection(sectionName);
			Iterator it1 = sObj.getKeys();

			while (it1.hasNext()) {
				// Get element
				Object key = it1.next();
				if (key.equals(keyString))
					return sObj.getString(key.toString());
			}

		}
		return null;
	}

	public static boolean isEntier(String numberString) {
		try {
			Integer.parseInt(numberString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param branchList list continet année , release, sprint
	 * @return une chaine concat année,release,sprint sous la format YYRRII.
	 */
	public static String concatYearReleasSprint(List<String> branchList) {

		if (StringUtils.isEmpty(branchList)) {
			LOG.error(EMPTY_BRANCHLIST);
			throw new IllegalArgumentException(EMPTY_BRANCHLIST);
		}

		try {
			Integer.parseInt(branchList.get(1));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("branch name is not correct : {} ", e);
		}

		if (!StringUtils.isEmpty(branchList) && branchList.get(1)
														  .length() == 4) {
			branchList.get(1)
					  .substring(2, 4);
		}

		String releas = branchList.get(2);
		String sprint = branchList.get(3);

		if (releas.length() == 1) {
			releas = String.join("", "0", branchList.get(2));
		}
		if (sprint.length() == 1) {
			sprint = String.join("", "0", branchList.get(3));
		}

		return String.join("", branchList.get(1), releas, sprint);

	}

	/**
	 * verifier si l'année just aprés '/' où '-'
	 * @return
	 */
	public static String getYearReleaseSprint(String brancheName) {

		if ((brancheName.contains("/") && brancheName.indexOf("/") < 3) || (brancheName.contains("-")
				&& brancheName.indexOf("-") < 2) || (!brancheName.contains("/") && !brancheName.contains("-"))) {
			LOG.error(INCORRECT_CONTENT);
			throw new IllegalArgumentException(INCORRECT_CONTENT);
		}

		if (brancheName.contains("/")) {
			return concatYearReleasSprint(Lists.newArrayList(Splitter.on("/")
																	 .split(brancheName)));
		}

		return concatYearReleasSprint(Lists.newArrayList(Splitter.on("-")
																 .split(brancheName)));
	}

	/**
	 * mettre les branches de la nouvelle sprint en fin de la collection.
	 * @param branches list des branch de repositories
	 * @return liste des branches est les branches de la nouvelle releas en bas.
	 */
	public static Collection<Branch> getBranchesTrie(List<Branch> branches) {

		branches.sort(BitbucketUtils::compareBranches);

		return branches;
	}

	/**
	 * trouver le plus grand
	 * @param branch1 branch 1
	 * @param branch2 branch 2
	 * @return si branch 1 > branch 2 return 1 sinon si branch 1 < branch 2 return -1
	 * sinon equalité return 0;
	 */
	private static int compareBranches(Branch branch1, Branch branch2) {

		return fetchSprint(branch1).compareTo(fetchSprint(branch2));
	}

	/**
	 * @param branch objet branch
	 * @return Integer foramt YYRRII
	 */
	private static Integer fetchSprint(Branch branch) {

		int value = 0;
		try {
			value = Integer.parseInt(getYearReleaseSprint(branch.getName()));
		} catch (IllegalArgumentException e) {
			LOG.warn("branch name not containes '/' où '-' :{}", e);
		}
		return value;
	}

}
