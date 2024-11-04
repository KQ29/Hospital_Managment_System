package Hospital_Managment_System;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

	public static Connection connectDB() {
		try {
			Class.forName("org.sqlite.JDBC");

			Connection connect = DriverManager
					.getConnection("jdbc:sqlite:/Users/km/Documents/database/hosRestaurant.db");
			System.out.println("Connection to database has been established successfully.");
			return connect;

		} catch (Exception e) {
			System.out.print("Not connected");
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		connectDB();
	}
}
