/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DatabaseBroker;
import domain.*;
import gui.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Controller extends Exception {

    private static Controller instance;
    DatabaseBroker dbb = new DatabaseBroker();
    Users user = new Users();
    UserGroup group = new UserGroup();
    Furniture furniture;
    PropertyType propertyType;
    Structure structure;
    CityArea cityArea;
    

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return (instance);
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public Structure getStructure() {
        return structure;
    }

    public CityArea getCityArea() {
        return cityArea;
    }

    public Users getUser() {
        return user;
    }
    
    public UserGroup getUserGroup(){
        return group;
    }

    public void insertUsers() throws SQLException, Exception {
        dbb.insertDomainObject(getUser());
    }

    public List<GeneralDomainObject> listUserGroupCombo(GeneralDomainObject gdo) throws SQLException, Exception {
        return dbb.ListDomainObject(gdo, "");
    }
    
    public  List<GeneralDomainObject> listLoggedInUser(String username, String user_password) throws SQLException, Exception{
        return dbb.ListDomainObject(getUser(), " where user='"+username+"' and user_password='"+user_password+"'");
    }
    
//    public List<GeneralDomainObject> listUserGroupCombo() throws SQLException, Exception {
//        return dbb.ListDomainObject(getUserGroup(), "");
//    }
    
}
