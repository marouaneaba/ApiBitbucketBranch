package fr.branch.git.dto;

public class User {
	/**
	 * nom d'utilisateur.
	 */
	private String userName;
	/**
	 * password.
	 */
	private String password;
	/**
	 * key.
	 */
	private String key;
	/**
	 * secret.
	 */
	private String secret;

	/**
	 * @return le nom d'utilisateur.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName : nom d'utilisateur.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password : password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key : key.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return secret.
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret : secret.
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "User{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + ", key='" + key + '\'' + ", secret='"
				+ secret + '\'' + '}';
	}
}
