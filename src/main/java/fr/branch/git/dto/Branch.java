package fr.branch.git.dto;

public class Branch {

	/**
	 * nom de la branche.
	 */
	private String name;
	/**
	 * la date de la création de la branche.
	 */
	private String creationDate;
	/**
	 * la date derniére commits
	 */
	private String lastCommitDate;
	/**
	 * le créateur de la branche.
	 */
	private String author;
	/**
	 * les commits d'une branche.
	 * format : nom ( nombre commit )
	 */
	private String commits;

	/**
	 * retourne true si la branch m'appartient sinon false.
	 */
	private boolean isMaBranch;

	/**
	 * @param name           nom branch.
	 * @param creationDate   date la création branch.
	 * @param lastCommitDate la dérniere commit dans la branch.
	 * @param author         auteur branch.
	 * @param commits        nombre de commit dans la branch.
	 */
	public Branch(String name, String creationDate, String lastCommitDate, String author, String commits) {
		this.name = name;
		this.creationDate = creationDate;
		this.lastCommitDate = lastCommitDate;
		this.author = author;
		this.commits = commits;
	}

	/**
	 * @param branch objet représent une branch.
	 */
	public Branch(BranchBuilder branch) {
		this.name = branch.name;
		this.creationDate = branch.creationDate;
		this.lastCommitDate = branch.lastCommitDate;
		this.author = branch.author;
		this.commits = branch.commits;
		this.isMaBranch = branch.isMaBranch;
	}

	/**
	 * @return nom de la branch.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name le nom de la branch
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return la date création la branch.
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate date creation branch.
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return la date derniére commit de la branch.
	 */
	public String getLastCommitDate() {
		return lastCommitDate;
	}

	/**
	 * @param lastCommitDate la date dernier commit de la branch.
	 */
	public void setLastCommitDate(String lastCommitDate) {
		this.lastCommitDate = lastCommitDate;
	}

	/**
	 * @return le nom de commit.
	 */
	public String getCommits() {
		return commits;
	}

	/**
	 * @param commits le nom de commit.
	 */
	public void setCommits(String commits) {
		this.commits = commits;
	}

	/**
	 * @return le nom auteur créer la branch.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author auteur créer la branch.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isIsMaBranch() {
		return isMaBranch;
	}

	public void setIsMaBranch(boolean isMaBranch) {
		this.isMaBranch = isMaBranch;
	}

	@Override
	public String toString() {
		return "Branch{" + "name='" + name + '\'' + ", creationDate='" + creationDate + '\'' + ", lastCommitDate='"
				+ lastCommitDate + '\'' + ", author='" + author + '\'' + ", commits='" + commits + '\'' + ", isMaBranch="
				+ isMaBranch + '}';
	}

	/**
	 * builder
	 */
	public static class BranchBuilder {
		private final String name;
		private String creationDate;
		private String lastCommitDate;
		private String author;
		private String commits;
		private boolean isMaBranch;

		public BranchBuilder(String name) {
			this.name = name;
		}

		public BranchBuilder creationDate(String creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public BranchBuilder lastCommitDate(String lastCommitDate) {
			this.lastCommitDate = lastCommitDate;
			return this;
		}

		public BranchBuilder author(String author) {
			this.author = author;
			return this;
		}

		public BranchBuilder commits(String commits) {
			this.commits = commits;
			return this;
		}

		public BranchBuilder maBranch(boolean maBranch) {
			this.isMaBranch = maBranch;
			return this;
		}

		public Branch build() {
			Branch branch = new Branch(this);
			return branch;
		}

	}
}
