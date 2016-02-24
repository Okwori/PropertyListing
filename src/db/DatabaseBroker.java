/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

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
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "");
        
        //System.out.println("Connection is established!");
    }
    
    
    public void closeConnection() throws SQLException{
        
        connection.close();
    }
    
    //  Property Classes
    // public 

    /*
    public List<City> getAllCities() throws SQLException{
        
        List<City> cities = new ArrayList<>(); 
        
        String sql = "Select * from City";
        
        Statement st = connection.createStatement();
        
        ResultSet rs = st.executeQuery(sql);
                
        while(rs.next()){
            
            City city = new City();
            
            city.setZipCode(rs.getLong("zipCode"));
            city.setName(rs.getString("name"));
            
            cities.add(city);
            
        }        
        
        return cities;
        
    }
    
    public void insertPerson(Object p) throws SQLException{
        
        //String sql = "INSERT INTO Person VALUES("+p.getPersonID()+",'"+p.getFirstName()+"'...)";
        
        String sql = "INSERT INTO  Person  VALUES (?,?,?,?,?)";
        
        PreparedStatement ps = connection.prepareStatement(sql);
        
        ps.setInt(1, p.getPersonID());
        ps.setString(2, p.getFirstName());
        ps.setString(3,p.getLastName());
        ps.setString(4, p.getStreet());
        ps.setLong(5, p.getCity().getZipCode());
        
        ps.executeUpdate();
        
    }
    
    */
    
}
