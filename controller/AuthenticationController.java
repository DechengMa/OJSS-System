package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.security.auth.login.CredentialException;

import model.Account;
import model.DataStorage;

public class AuthenticationController {
 	
	public static Account volidateaccount(String username,String password){
		Account useraccount = DataStorage.getInstance().validateAccount(username, password);
		return useraccount;
	}
	
	public boolean regisiter(String username,String password,String usertype,String email){
		//Validation
		Account account = new Account(DataStorage.getInstance().generateAccountId(), username, password, usertype, email);
		boolean regisiterresult = DataStorage.getInstance().register(account);
		return regisiterresult;
	}
}
