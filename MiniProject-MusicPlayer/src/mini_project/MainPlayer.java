package mini_project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPlayer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Functions function = new Functions();
		boolean quit = false;
		int song = 0;
		String role = null;
		String valid = "false";
		int count = function.songCount();
		ArrayList<String> favorite = new ArrayList<>();
		Functions.printMenu();
		int login = 0;
		while (!quit) {

			int option = scanner.nextInt();
			

			switch (option) {
			case 0:
				quit = true;
				break;
			case 1:// signup
				System.out.println("Enter the user role - Admin/Customer");
				role = scanner.next();
				System.out.println("Enter the email id : ");
				String mailId = scanner.next();
				function.mailIdValidator(mailId);
				System.out.println("Enter the username : ");
				String username = scanner.next();
				function.usernameValidator(username);

				do {
					System.out.println("Enter the password");
					String password = scanner.next();
					System.out.println("Enter the password again to confrim");
					String confrimPassword = scanner.next();
					function.passwordValidator(password, confrimPassword);
				} while (function.pasValid.equals(valid));

				System.out.println("Successfully signedup!!!!");
				login = 1;
				if (role.equals("admin")) {
					System.out.println("Welcome admin");
				} else
					System.out.println("welcome " + username);
				break;
			case 2:// login
				if (login == 1) {
					try {
						do {
							System.out.println("Enter the username : ");
							String username1 = scanner.next();
							System.out.println("Enter the password");
							String password1 = scanner.next();
							function.login(username1, password1);
						} while (function.loggedin.equals(valid));
						System.out.println("total songs available in the player : " + count);

						try {
							String ss = Files.readAllLines(Paths.get("songs.txt")).get(song);
							System.out.println(ss + " is playing now*");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else
					System.out.println("first signup!!!!");
				break;
			case 3:// view list of songs

				try {
					BufferedReader reader = new BufferedReader(new FileReader("songs.txt"));
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
					}
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:// play next song
				try {
					if (song + 1 < count) {
						song++;
						String ss = Files.readAllLines(Paths.get("songs.txt")).get(song);
						System.out.println(ss + " is playing");
					} else {
						System.out.println("We are at end of playList");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case 5:// play previous song

				try {
					if (song != 0) {
						song--;
						String ss = Files.readAllLines(Paths.get("songs.txt")).get(song);
						System.out.println(ss + " is playing");
					} else {
						System.out.println("We are at begining of playList");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 6:// add currently playing song to favorite
				String ss = Files.readAllLines(Paths.get("songs.txt")).get(song);

				boolean added = favorite.add(ss);
				if (added) {
					System.out.println(ss + " is added successfully added to the favorite list! ");
				} else {
					System.out.println("unable to add!!!!");
				}

				/*
				 * try { BufferedWriter writer = new BufferedWriter(new
				 * FileWriter("favorite.txt")); writer.write(ss); writer.newLine();
				 * writer.close(); System.out.println(ss + " is added to favorite playlist"); }
				 * catch (IOException e) { e.printStackTrace(); }
				 */

				break;
			case 7:// view songs in favorite album
				function.printPlayList(favorite);

				break;
			case 8:

				if (role.equals("admin")) {
					System.out.println("Welcome admin");
					
					System.out.println("1- to add new song\n" + "2 - to remove song from songs\n"
							+ "Enter the options to perform>>");
					int adminOption = scanner.nextInt();
					switch (adminOption) {
					case 1: // add new song
						System.out.println("total song " + function.songCount());
						System.out.println("Enter the song count+1 title without spaces!! ");
						System.out.println("EX: 10-songtitle");
						String songTitle = scanner.next();
						function.addNewSong(songTitle);

						break;
					case 2: // remove song
						System.out.println("Enter the song index to remove the respective song");
						System.out.println("NOTE: song index is number before the -song title ");
						int songIndex = scanner.nextInt();
						function.removeSong(songIndex);

						break;
					}
				} else
					System.out.println("you are not a admin....you have no rights to do admin functions");

				
				break;
			}
			if (option != 0) {
				System.out.println(".........................");
				System.out.println("Enter other option from main menu to perform");
			}

		}
		System.out.println("...........BYE bye..............");

		scanner.close();

	}

}
