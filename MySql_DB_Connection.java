/**
 * Author: Jonathan S. Kwiat
 * Date: 12/5/1016
 * This is from my classroom notes and I had extensive help by looking at the tutorial and instructions
 * at a course at NorthEastern University. I did not have to use any of their DBDemo code but they helped
 * me understand how to set up the connector in Eclipse and how to plug the SQL NorthWind Databse into
 * the MySql server on Ubuntu 15.10
 * 
 * From: Northeastern University, Instructor Kathleen Durant
 * https://www.linkedin.com/in/kathleendurant
 * 
 * Page for Course Database Design: http://www.ccs.neu.edu/home/kathleen/classes/cs3200/index.htm
 * Link to the JDBCtutorial.pdf I have included can be found there.
 * 
 * I used MySql and followed the installation instructions in MySQL_UBUNTU_Instllation.html found
 * at: https://help.ubuntu.com/12.04/serverguide/mysql.html
 * 
 * I downloaded the Northwind database from: https://northwinddatabase.codeplex.com/
 */
 

import java.sql.*;
import java.util.Enumeration;
public class MySql_DB_Connection {

	public static void main(String[] args) {
		/**
		 * Looking at DriverManager class, the getDrivers() lists all the drivers available to your program will return a generic
		 * Enumeration, which itself hasMoreElements() and nextElement()
		 */
		Enumeration<Driver> ed = DriverManager.getDrivers();
		while (ed.hasMoreElements()) {
			System.out.println((Driver)ed.nextElement());
		}
		

		Connection connect = null;
		//Just manually inserting the default username: root, and the password:tungsten^2 that I entered
		// when I set it up.
		// Followed the instructions in Introduction to Java Programming by Liang starting at pg 1180
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:" 
					+"3306/northwind", "root", "tungsten^2" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement state1 = null;
		try {
			state1 = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			ResultSet resultTable1 = null;
			//Pick a table in the png at random to try and get and print
		try {
			resultTable1 = state1.executeQuery("SELECT * FROM Suppliers");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSetMetaData metaInfoTable1 = null;
		try {
			metaInfoTable1 = resultTable1.getMetaData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int numCols = 0;
		try {
			numCols = metaInfoTable1.getColumnCount();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(resultTable1.next()) {
				for(int i = 1; i < numCols ; i++) {
					System.out.print(resultTable1.getString(i)+ "\t");
				}
				System.out.print("\n\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
