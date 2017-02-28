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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        String query = "Select "+gdo.getFieldList()+" from "+gdo.getTableName()+whereClause;
        makeConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        //closeConnection();
        return  gdo.getObjectList(rs);
    }
    
    public List<GeneralDomainObject> ListDomainObjectWithJoin(GeneralDomainObject gdo, String whereClause) throws SQLException, Exception{
        //whereClause = "";
        String query = "Select "+gdo.getFieldList()+" from "+gdo.getTableNameWithJoins()+whereClause;
        makeConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        
                for(int i=1;i<=rsmd.getColumnCount();i++){                    
                    System.out.println(i+"."+rsmd.getColumnLabel(i));
                }
        
        //closeConnection();
        return  gdo.getObjectList(rs);
    }
        
//    public List<GeneralDomainObject> listDomainProperty() throws SQLException, Exception{
//        String query = "select `p`.`address` AS `address`,`p`.`area` AS `area`,`p`.`city_area_id` AS `fk_city_area_id`,`p`.`customer_id` AS `fk_customer_id`,`p`.`description` \n" +
//"AS `description`,`p`.`furniture_id` AS `fk_furniture_id`,`p`.`price` AS `price`,`p`.`property_id` AS `property_id`,`p`.`property_name` AS `property_name`,`p`.`property_type_id` \n" +
//"AS `fk_property_type_id`,`p`.`structure_id` AS `fk_structure_id`,`p`.`status_id` AS `fk_status_id`,`pty`.`property_type` AS `property_type`,`pty`.`property_type_id` \n" +
//"AS `property_type_id`,`s`.`structure` AS `structure`,`s`.`structure_id` AS `structure_id`,`f`.`furniture` AS `furniture`,`f`.`furniture_id` AS `furniture_id`,`c`.`address` \n" +
//"AS `customer_address`,`c`.`city` AS `city`,`c`.`country_id` AS `fk_country_id`,`c`.`customer_fname` AS `customer_fname`,`c`.`customer_id` AS `customer_id`,`c`.`customer_lname` \n" +
//"AS `customer_lname`,`c`.`customer_mname` AS `customer_mname`,`c`.`email` AS `email`,`c`.`indentification_id` AS `fk_indentification_id`,`c`.`phone_number` \n" +
//"AS `phone_number`,`c`.`status_id` AS `fk_customer_status`,`c`.`user_id` AS `fk_user_id`,`sp`.`status` AS `status`,`sp`.`status_id` AS `status_id`,`ca`.`city_Area` \n" +
//"AS `city_Area`,`ca`.`city_Area_id` AS `city_Area_id`,`ca`.`town` AS `town`,`ca`.`zip_code` AS `zip_code` from \n" +
//"\n" +
//"((( (((`property` `p` join `property_type` `pty` on((`p`.`property_type_id` = `pty`.`property_type_id`))) \n" +
//"join `structure` `s` on((`s`.`structure_id` = `p`.`property_id`))) \n" +
//"join `furniture` `f` on((`f`.`furniture_id` = `p`.`furniture_id`))) \n" +
//"join `customer` `c` on((`c`.`customer_id` = `p`.`customer_id`))) \n" +
//"join `status_property` `sp` on((`sp`.`status_id` = `p`.`status_id`))) \n" +
//"join `city_area` `ca` on((`ca`.`city_Area_id` = `p`.`city_area_id`)))";
//        makeConnection();
//        Statement st = connection.createStatement();
//        ResultSet rs = st.executeQuery(query);
//        return gdo.getObjectList(rs);
//    }
//    
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