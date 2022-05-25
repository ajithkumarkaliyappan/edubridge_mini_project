package mini_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {

	FileWriter writer = null;
	Scanner scanner = new Scanner(System.in);

	// print menu
	public static void printMenu() {
		System.out.println("------------------------------");
		System.out.println("Available options >>> press the option to perform sprcific function");
		System.out.println(
				"0 - to Exit\n" + "1 - signup\n" + "2 - login\n" + "3 - view list of songs \n" + "4 - play next song\n"
						+ "5 - play previous song \n" + "6 - add currently playing song to favorite playlist \n"
						+ "7 - view songs in favorite album\n" + "8 - admin functions ");
		System.out.println(".........................");
		System.out.println("Enter options to perform");
	}

	// song count
	int count = 0;

	public int songCount() throws IOException {
		try {
			try (BufferedReader reader = new BufferedReader(
					new FileReader("songs.txt"))) {
				while ((reader.readLine()) != null) {
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	// view song in favorite
	public void printPlayList(ArrayList<String> favorite) {
		Iterator<String> iterator = favorite.iterator();
		System.out.println("------------------------------");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("------------------------------");
	}

	// add new songs
	public void addNewSong(String newSong) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					new File("songs.txt"), true));
			writer.write("\n" + newSong);
			System.out.println("successfully added " + newSong);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void removeSong(int songIndex) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(new File("songs.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()) {
			list.add(s.next());
		}

		/*
		 * for (int i = 0; i < list.size(); i++) { System.out.println(list.get(i)); }
		 */

		s.close();
		
		System.out.println("successfully removed " + list.get(songIndex).toString());
		list.remove(songIndex);
		System.out.println(".........................................");
		System.out.println("song list after the deletion of song ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public String username2;

	public void usernameValidator(String username) {
		// TODO Auto-generated method stub
		this.username2 = username;
		String regex = "^[a-zA-Z][a-zA-z0-9_]{6,19}$";
		Pattern p = Pattern.compile(regex);
		if (username == null)
			System.out.println("enter username!it is empty");
		Matcher m = p.matcher(username);
		if (m.matches()) {
			System.out.println("valid user name !!");
		} else
			System.out.println("user name is invalid  !!");
	}

	public void mailIdValidator(String mailId) {
		// TODO Auto-generated method stub
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mailId);
		if (matcher.matches()) {
			System.out.println("valid mailID !!");
		} else
			System.out.println("mailID is invalid  !!");
	}

	// public String pasValid =null;
	public String pasValid;
	public String password2;

	public void passwordValidator(String password, String confrimPassword) {
		// TODO Auto-generated method stub
		if (password.equals(confrimPassword)) {
			pasValid = "true";
			this.password2 = password;
		}
	}

	public String loggedin;

	public void login(String username1, String password1) {
		// TODO Auto-generated method stub
		if (username1.equals(username2) && password1.equals(password2)) {
			loggedin = "true";
			System.out.println("Successfully loggedIn>>>>");
		} else {
			System.out.println("something wrong re-enter username and password");
		}
	}

}
