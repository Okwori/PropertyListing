/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import domain.CityArea;
import domain.Country;
import domain.Customers;
import domain.GeneralDomainObject;
import domain.Indentification;
import domain.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        //System.out.println(query);
        st.executeUpdate(query);   
        closeConnection();
    }
    
    public List<GeneralDomainObject> ListDomainObject(GeneralDomainObject gdo, String whereClause) throws SQLException, Exception{
        //whereClause = "";
        String query = "Select * from "+gdo.getTableName()+whereClause;
        makeConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        //closeConnection();
        return  gdo.getObjectList(rs);
    }
    
    
    public void closeConnection() throws SQLException{        
        connection.close(); 
    }   
    
    public static void main(String[] args) throws Exception {
        DatabaseBroker dbo = new DatabaseBroker();
        Users user = new Users(); //UserGroup ug = new UserGroup();
        user.setUserName("'"+"Simon78"+"'"); user.setUserPassword("'"+"Tony"+"'"); user.setGroupID(1);
        dbo.insertDomainObject(user);
        //System.out.println("Successful!");
        //System.out.println(user.getValuesForInsert()); 
        /*Property ppt = new Property();
        ppt.setAddress("23 Simon Street"); ppt.setDescription("This is life with me bro!");
        ppt.setArea("23m"); ppt.setCityAreaID(1); ppt.setCustomerID(1);
        ppt.setFurnitureID(1); ppt.setName("Here u go!"); ppt.setPrice(24.00);
        ppt.setStatusID(1);dbo.insertDomainObject(ppt);*/
        
        //Customers customers = new Customers();
        /*Customers customers1 = controller.Controller.getInstance().getCustomer();
        
        Indentification indentification = new Indentification();
        indentification.setID(2);
        CityArea cityArea = controller.Controller.getInstance().getCityArea();
        cityArea.setCityAreaID(1);
        Users users = new Users();
        users.setUserID(4);
        Country country =  new Country();
        country.setID(22);
                
        
        customers1.setFirstName("Marcellinus"); customers1.setIndentification(indentification);
        customers1.setCity(cityArea);
        customers1.setAddress("20 jhdvcf"); //customers1.setCityID(3);customers1.setCountryID(4);
        customers1.setEmail("simoen@gmail.com"); customers1.setUserID(3); customers1.setmName("Simon");
        customers1.setStatusID(1); customers1.setPhoneNumber("080934234"); customers1.setLastName("Okwori");
        customers1.setUserID(4); customers1.setCountry(country);
        dbo.insertDomainObject(customers1);            */
        //System.out.println(dbo.ListDomainObject(customers, ""));
        //String usern = "Simon", password = "Tony"; 
        //System.out.println(dbo.ListDomainObject(ug, " where group_id=1")); //+usern+" and user_password="+password).size());  
        //System.out.println(dbo.ListDomainObject(user, " where user='"+usern+"' and user_password='"+password+"'").size());        
  
}    
}
