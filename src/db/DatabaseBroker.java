/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import domain.GeneralDomainObject;
import domain.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class DatabaseBroker {
    Connection connection;
    
    public void makeConnection() throws Exception{
        
        // load a Driver class
        Class.forName("com.mysql.jdbc.Driver");
        //making a connection        
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/propertylisting", "root", "");
       
    }
    
    public void insertDomainObject(GeneralDomainObject gdo) throws SQLException, Exception{              
        String query = "INSERT INTO "+ gdo.getTableName()+gdo.getColumnsForInsert()+" VALUES" +gdo.getValuesForInsert(); 
        //String query = "INSERT INTO user(user, user_password, group_id) VALUES("+"'"+"Simon5"+"'"+", 'Okwori', 1)";
        makeConnection();
        Statement st = connection.createStatement();        
        st.executeUpdate(query);   
        closeConnection();
    }
    
    
    public void closeConnection() throws SQLException{        
        connection.close();
    }   
    
    public static void main(String[] args) throws Exception {
        DatabaseBroker dbo = new DatabaseBroker();
        Users user = new Users();
        user.setUserName("'"+"Simon78"+"'"); user.setUserPassword("'"+"Tony"+"'"); user.setGroupID(1);
        dbo.insertDomainObject(user);
        System.out.println("Successful!");
        System.out.println(user.getValuesForInsert());
    }    
}
