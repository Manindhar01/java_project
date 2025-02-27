package com.java.atm_machine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddAccountService {

	public void add_account() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Name: ");
		String name = scanner.next();
		System.out.println("Enter Account no:");
		String accountNumber = scanner.next();
		System.out.println("Enter Pin no: ");
		String pinNumber = scanner.next();
		if (accountNumber.matches("[0-9]{8}")) {
			if (pinNumber.matches("[0-9]{4}")) {
				ArrayList<Accountdetails> arrayList = new ArrayList<>();
				arrayList.add(new Accountdetails(name, accountNumber, pinNumber));
				try {
					String insertQuery = "insert into AccountDetails(name,account_no,atm_pin) values(?,?,?)";
					Connection connection = DBConnection.getConnection();
					PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
					prepareStatement.setString(1, name);
					prepareStatement.setString(2, accountNumber);
					prepareStatement.setString(3, pinNumber);
					int row = prepareStatement.executeUpdate();
					System.out.println("Your Account has Been Created: " + row);
					connection.close();
					scanner.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("pin no must have 4 digits");
			}
		} else {
			System.out.println("Account number must have 8 digits");
		}

	}

}
