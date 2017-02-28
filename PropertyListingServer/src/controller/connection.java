/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import java.sql.*;
//import javax.sql.*;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class connection {
    public static void main(String[] args) {
       ResultSet myResult, myPResult; Connection mycon; PreparedStatement myPPstmt;
       try {
            // 1. Connection 
            mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/propertylisting", "root", "");
            
            // 2. Query
            Statement stmt = mycon.createStatement();
            // 3 Execute
            // 3.1 Insert 
            stmt.executeUpdate("INSERT INTO `propertylisting`.`test1` (`name`) VALUES ('hbvdkjf')");
            // 3.2 Update
            String updatestmt = "Update test1 set name = 'okwori' where id = 8";
            stmt.executeUpdate(updatestmt);
            // 3.3 Delete
            String deletestmt = "Delete from test1 where name = 'hbvdkjf'";
            int no_deleted = stmt.executeUpdate(deletestmt);
            // 3.4 Prepared Statements
            String preparedstmt = "Select * from test1 where id = ?";
            myPPstmt = mycon.prepareStatement(preparedstmt);
            
                //setting parameters
            myPPstmt.setInt(1, 2);
            myPResult = myPPstmt.executeQuery();
            // 3 Query - Select -
            myResult = stmt.executeQuery("select * from test1");
            
            // 4. Process Result
            //System.out.println("Insert Complete");
            while(myResult.next()){
                System.out.println(myResult.getInt("id") + ", " + myResult.getString("name"));                
            }
            System.out.println(no_deleted);
            display(myPResult);
            
        } catch (Exception e) {
           e.printStackTrace();
        }       
    }
    private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			int the_id = myRs.getInt("id");
			String the_name = myRs.getString("name");
			
			System.out.println("ID: " + the_id + ", " + "Name: " + the_name);
		}
	}
}
