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
    
    
    private Controller() {                         
    }
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return (instance);
    }
    
   public Users getUser(){
        return new Users();
    }
    
    public void insertUsers() throws SQLException, Exception{
        dbb.insertDomainObject(getUser());
    }
}
