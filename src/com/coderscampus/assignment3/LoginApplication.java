package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginApplication {
	public static void main(String[] args){
		UserService userService = new UserService(); //This is to instantiate the UserService
		User[] userArrayList = new User[4]; //This is for the user ArrayList
		
		BufferedReader fileReader = null;
		try {
			fileReader = new BufferedReader(new FileReader("data.txt")); //Read each line from the .txt file.
			int i = 0;
			String line = "";
			while ((line = fileReader.readLine()) != null) {
				String[] accounts = line.split(","); //This will split each String on a line seperated by comma. 
				userArrayList[i] = userService.createUser(accounts[0], accounts[1], accounts[2]); //This will create a UserList from accounts and read the data based on the index.
				i++;
			}
		//These exception classes will be read if it can't read the file or there's an issue with the input	
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was an IOException");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close(); //Close the file reader
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Scanner scanner = new Scanner(System.in); 
		for(int i = 0; i < 5; i++){ //For loop for the user and password input. 
			System.out.println("Please enter your email address: ");
			String username = scanner.nextLine();
			username = username.toUpperCase(); //Input the username to authenticate.

			System.out.println("Please enter your password: "); 
			String password = scanner.nextLine(); //Input the password to authenticate.
			
			for(int index = 0; index < userArrayList.length; index++){
				if(userArrayList[index].getUsername().toUpperCase().equals(username) && userArrayList[index].getPassword().equals(password)){
					System.out.println("Welcome: "+ userArrayList[index].getName());
					System.exit(0);
				} else {
					
				}
			}
			System.out.println("Invalid login, try again");
		}
		System.out.println("Too many failed login attempts, you are now locked out."); 
		//Fails authorization if the login is incorrect five times.
		scanner.close();
	}
}
