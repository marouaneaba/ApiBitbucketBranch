package fr.branch.git.info;

import fr.branch.git.utils.BitbucketUtils;

public class UserInfo {

	/**
	 * nom d'utilisateur.
	 */
	public final static String nameUser = "abk.marwane@gmail.com";
	/**
	 * pseudo d'utilisateur.
	 */
	public final static String pseudo = "abk_marwane";
	/**
	 * mot de passe.
	 */
	public final static String password = "101202303/pic";
	/**
	 * api Bitbucket version 2.0
	 */
	public final static String BITBUCKET_API_URL = "https://bitbucket.org/!api/2.0/repositories/";
	/**
	 * url récupérer les branchs d'un controller.
	 */
	public final static String BRANCHES_URL = "/refs/branches";
	/**
	 * url récupérer les commits d'un controller.
	 */
	public final static String COMMITS_URL = "/commits/";
	/**
	 * date format.
	 */
	public final static String FORMAT = "dd-MM-yyyy";
	/**
	 * key
	 */
	public final static String key = "uGsvYxNvfbbDMppkTR";
	/**
	 * secret
	 */
	public final static String secret = "kuFKduRbhRBXLznxLLKqFWMtzQhD7eQJ";
	/**
	 * Default le chemin de fichier de configuration "application.properties".
	 */
	public static final String SETTINGS_NAME = "application.properties";
	/**
	 * l'attribut values json
	 */
	public final static String VALUES = "values";
	/**
	 * l'attribut name json
	 */
	public final static String NAME = "name";

	/**
	 * l'attribut target json
	 */
	public final static String TARGET = "target";

	/**
	 * l'attribut parents json
	 */
	public final static String PARENTS = "parents";
	/**
	 * l'attribut date json
	 */
	public final static String DATE = "date";
	/**
	 * l'attribut author json
	 */
	public final static String AUTHOR = "author";
	/**
	 * l'attribut raw json
	 */
	public final static String RAW = "raw";
	/**
	 * l'attribut user json
	 */
	public final static String USER = "user";
	/**
	 * l'attribut display_name json
	 */
	public final static String DISPLAY_NAME = "display_name";
	/**
	 * l'attribut access_token json
	 */
	public final static String ACCESS_TOKEN = "access_token";
	/**
	 * nom d'utilisateur.
	 */
	public static final String NAME_USER_PROPERTY = "nameUser";
	/**
	 * password.
	 */
	public static final String PASSWORD_PROPERTY = "password";
	/**
	 * pseudo.
	 */
	public static final String PSEUDO_PROPERTY = "pseudo";
	/**
	 * key.
	 */
	public static final String KEY_PROPERTY = "key";
	/**
	 * secret.
	 */
	public static final String SECRET_PROPERTY = "secret";

	/**
	 * @return le nom d'utilisateur.
	 */
	public static String getUserName() {
		return BitbucketUtils.getValueOfNameProperties(NAME_USER_PROPERTY);
	}

	/**
	 * @return pseudo.
	 */
	public static String getPseudo() {
		return BitbucketUtils.getValueOfNameProperties(PSEUDO_PROPERTY);
	}

	/**
	 * @return password.
	 */
	public static String getPassword() {
		return BitbucketUtils.getValueOfNameProperties(PASSWORD_PROPERTY);
	}

	/**
	 * @return key.
	 */
	public static String getKey() {
		return BitbucketUtils.getValueOfNameProperties(KEY_PROPERTY);
	}

	/**
	 * @return secret.
	 */
	public static String getSecret() {
		return BitbucketUtils.getValueOfNameProperties(SECRET_PROPERTY);
	}

}
