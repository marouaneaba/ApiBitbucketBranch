package com.bitbucket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import fr.branch.git.dto.Branch;
import fr.branch.git.utils.BitbucketUtils;

public class marouaneTest {

	@Test
	public void marouaneTest() {

		String t = "toto";
		tes(t);
		System.out.println(t);

		/*for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());

		}*/
	}

	public void tes(String t) {
		t = "marouane";
	}

	@Test
	public void run() {
		List branches = new ArrayList();
		branches.add(new Branch("master", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/17/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/19/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/1/9", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/19/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("master", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/11/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/18/1/4", "dateCreation", "lastCommit", "author", "commit"));
		branches.add(new Branch("toto/19/1/4", "dateCreation", "lastCommit", "author", "commit"));

		Collection col = BitbucketUtils.getBranchesTrie(branches);

		col.forEach(x -> System.out.println("marouaen : " + x));
		//HashSet<String> j = new HashSet<>();
		//String year = "2015";
		//System.out.println(year.substring(2, 4));
		//col.forEach(x -> System.out.println(x));

		//System.out.println("2".compareTo("1"));

	}

	@Test
	public void mk() {
		String jsonString = "{\"pagelen\": 30, \"values\": [{\"hash\": \"091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3\", \"repository\": {\"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak\"}, \"avatar\": {\"href\": \"https://bytebucket.org/ravatar/{acc5c95e-642e-487f-ab63-0f498e292841}?ts=default\"}}, \"type\": \"repository\", \"name\": \"abak\", \"full_name\": \"abk_marwane/abak\", \"uuid\": \"{acc5c95e-642e-487f-ab63-0f498e292841}\"}, \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3\"}, \"comments\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3/comments\"}, \"patch\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/patch/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak/commits/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3\"}, \"diff\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/diff/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3\"}, \"approve\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3/approve\"}, \"statuses\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/091ab1cccb4f7c1c2eebdb542ec8d1b5501618d3/statuses\"}}, \"author\": {\"raw\": \"abk.marwane <abk.marwane@gmail.com>\", \"type\": \"author\", \"user\": {\"username\": \"abk_marwane\", \"display_name\": \"abk.marwane\", \"type\": \"user\", \"uuid\": \"{142329ce-a0ee-4716-949f-c349c0943a2d}\", \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/users/abk_marwane\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/\"}, \"avatar\": {\"href\": \"https://bitbucket.org/account/abk_marwane/avatar/32/\"}}}}, \"summary\": {\"raw\": \"gt edited online with Bitbucket\", \"markup\": \"markdown\", \"html\": \"<p>gt edited online with Bitbucket</p>\", \"type\": \"rendered\"}, \"parents\": [{\"hash\": \"964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\", \"type\": \"commit\", \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak/commits/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\"}}}], \"date\": \"2018-06-06T08:13:43+00:00\", \"message\": \"gt edited online with Bitbucket\", \"type\": \"commit\"}, {\"hash\": \"964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\", \"repository\": {\"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak\"}, \"avatar\": {\"href\": \"https://bytebucket.org/ravatar/{acc5c95e-642e-487f-ab63-0f498e292841}?ts=default\"}}, \"type\": \"repository\", \"name\": \"abak\", \"full_name\": \"abk_marwane/abak\", \"uuid\": \"{acc5c95e-642e-487f-ab63-0f498e292841}\"}, \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\"}, \"comments\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c/comments\"}, \"patch\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/patch/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak/commits/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\"}, \"diff\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/diff/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c\"}, \"approve\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c/approve\"}, \"statuses\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/964dcd6c0e1a9a8fd7c944c2a34f9b2d302c846c/statuses\"}}, \"author\": {\"raw\": \"abk.marwane <abk.marwane@gmail.com>\", \"type\": \"author\", \"user\": {\"username\": \"abk_marwane\", \"display_name\": \"abk.marwane\", \"type\": \"user\", \"uuid\": \"{142329ce-a0ee-4716-949f-c349c0943a2d}\", \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/users/abk_marwane\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/\"}, \"avatar\": {\"href\": \"https://bitbucket.org/account/abk_marwane/avatar/32/\"}}}}, \"summary\": {\"raw\": \"gt created online with Bitbucket\", \"markup\": \"markdown\", \"html\": \"<p>gt created online with Bitbucket</p>\", \"type\": \"rendered\"}, \"parents\": [{\"hash\": \"6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\", \"type\": \"commit\", \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak/commits/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\"}}}], \"date\": \"2018-06-06T08:13:22+00:00\", \"message\": \"gt created online with Bitbucket\", \"type\": \"commit\"}, {\"hash\": \"6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\", \"repository\": {\"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak\"}, \"avatar\": {\"href\": \"https://bytebucket.org/ravatar/{acc5c95e-642e-487f-ab63-0f498e292841}?ts=default\"}}, \"type\": \"repository\", \"name\": \"abak\", \"full_name\": \"abk_marwane/abak\", \"uuid\": \"{acc5c95e-642e-487f-ab63-0f498e292841}\"}, \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\"}, \"comments\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d/comments\"}, \"patch\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/patch/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\"}, \"html\": {\"href\": \"https://bitbucket.org/abk_marwane/abak/commits/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\"}, \"diff\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/diff/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d\"}, \"approve\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d/approve\"}, \"statuses\": {\"href\": \"https://bitbucket.org/!api/2.0/repositories/abk_marwane/abak/commit/6f8a5ca1549d5ec4e057fb12fe8b19ce6ee3a15d/statuses\"}}, \"author\": {\"raw\": \"abakarim <marouane.abakarim@pictime-groupe.com>\", \"type\": \"author\", \"user\": {\"username\": \"marouaneaba\", \"display_name\": \"marouaneaba\", \"type\": \"user\", \"uuid\": \"{c0a1ffc5-785e-4ff1-b6db-5118a6af42d1}\", \"links\": {\"self\": {\"href\": \"https://bitbucket.org/!api/2.0/users/marouaneaba\"}, \"html\": {\"href\": \"https://bitbucket.org/marouaneaba/\"}, \"avatar\": {\"href\": \"https://bitbucket.org/account/marouaneaba/avatar/32/\"}}}}, \"summary\": {\"raw\": \" add readme.md to master\\n\", \"markup\": \"markdown\", \"html\": \"<p>add readme.md to master</p>\", \"type\": \"rendered\"}, \"parents\": [], \"date\": \"2018-05-24T12:35:44+00:00\", \"message\": \" add readme.md to master\\n\", \"type\": \"commit\"}]}";

	}

	@Test
	public void maroaurn() {
		int[] tab = { 1, 2, 4, 4, 5, 6, 7, 8, 9, 10, 12, 15 };
		int[] piece = { 5, 10, 20, 50, 100, 200 };
		double rendu = 258;

		rendreMoney(piece, rendu);
	}

	public void rendreMoney(int[] piece, double rendu) {

		Vector<Integer> pieceRendu = new Vector<>();

		for (int i = piece.length - 1; i >= 0; i--) {

			if ((rendu % piece[i]) < rendu) {
				rendu = rendu % piece[i];
				pieceRendu.add(piece[i]);
			}
		}
	}

}
