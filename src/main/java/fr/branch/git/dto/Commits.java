package fr.branch.git.dto;

public class Commits {

	/**
	 * l'auteur.
	 */
	private String author;
	/**
	 * nombre de commit par author.
	 */
	private int nombreCommit;

	/**
	 * @param author       auteur.
	 * @param nombreCommit nombre de commit par author.
	 */
	public Commits(String author, int nombreCommit) {
		this.author = author;
		this.nombreCommit = nombreCommit;
	}

	/**
	 * @return auteur nom.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author nom d'auteur.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return nombre de commit par auteur.
	 */
	public int getNombreCommit() {
		return nombreCommit;
	}

	/**
	 * @param nombreCommit nombre de commit par auteur.
	 */
	public void setNombreCommit(int nombreCommit) {
		this.nombreCommit = nombreCommit;
	}

	@Override
	public String toString() {
		return "Commits{" + "author='" + author + '\'' + ", date='" + nombreCommit + '\'' + '}';
	}
}
