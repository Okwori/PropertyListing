/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import constants.Operations;
import domain.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PropertyTableModel;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class CominicationController extends Exception {

    private static CominicationController instance;
    Users user = new Users();
    UserGroup group = new UserGroup();
    Furniture furniture = new Furniture();
    PropertyType propertyType = new PropertyType();
    Structure structure = new Structure();
    CityArea cityArea = new CityArea();
    Property property = new Property();
    Customers customer = new Customers();
    Indentification indentification = new Indentification();
    Country country = new Country();
    
    Socket connectionToServer;
    
    ObjectOutputStream outToServer;
    ObjectInputStream inFromServer;
    
    private PropertyTableModel propertyTableModel = new PropertyTableModel();

    private CominicationController() {
        
        try {
            connectionToServer = new Socket("127.0.0.1", 9000);            
            outToServer = new ObjectOutputStream(connectionToServer.getOutputStream());
            inFromServer = new ObjectInputStream(connectionToServer.getInputStream());
                 
            
        } catch (IOException ex) {
            Logger.getLogger(CominicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static CominicationController getInstance() {
        if (instance == null) {
            instance = new CominicationController();
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

    public Property getProperty() {
        return property;
    }    
    
    public Customers getCustomer() {
        return customer;
    }

    public Users getUser() {
        return user;
    }
    
    public UserGroup getUserGroup(){
        return group;
    }    

    public Indentification getIndentification() {
        return indentification;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    public void setIndentification(Indentification indentification) {
        this.indentification = indentification;
    }
    
    public void insertDomainObject(GeneralDomainObject gdo) throws SQLException, Exception {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setOpertion(Operations.INSERT_DOMAIN_OBJECT);
        clientRequest.setObjectOfOperation(gdo);
        
        outToServer.writeObject(clientRequest);
        
        ServerResponse serverResponse = (ServerResponse) inFromServer.readObject();
        if (serverResponse.getEx()!= null){
            throw serverResponse.getEx();
        }
        
        //dbb.insertDomainObject(gdo);
    }

    public List<GeneralDomainObject> listCombos(GeneralDomainObject gdo) throws SQLException, Exception {
        return getListOfObjects(gdo, "");
        //return dbb.ListDomainObject(gdo, "");
    }
    
    public  List<GeneralDomainObject> listLoggedInUser(String username, String user_password) throws SQLException, Exception{
        return getListOfObjects(getUser(), " where user='"+username+"' and user_password='"+user_password+"'");
    }
    
    public List<GeneralDomainObject> listDomainObjects(GeneralDomainObject gdo, String whereClause) throws SQLException, Exception{
        return getListOfObjects(gdo, whereClause);
    } 
    
    public List<GeneralDomainObject> getListOfObjects(GeneralDomainObject gdo, String condition) throws Exception{
        
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setOpertion(Operations.LIST_DOMAIN_OBJECT);
        clientRequest.setObjectOfOperation(gdo);
        clientRequest.setAnotherObject(condition);
        outToServer.writeObject(clientRequest);
        
        ServerResponse serverResponse = (ServerResponse) inFromServer.readObject();
        
        if(serverResponse.getEx() != null){
            throw serverResponse.getEx();
        }
        return (List<GeneralDomainObject>) serverResponse.getResultOfOperation();
        
    }
    
    //public List<GeneralDomainObject> getListOfPropertyTableModel(GeneralDomainObject gdo, String condition) throws Exception{
      //  return getListOfObjects(gdo, "")
    //}
    
    public PropertyTableModel getPropertyTableModel() {
        return propertyTableModel;
    }

   public void setPropertyTableModel(PropertyTableModel propertyTableModel) {
        this.propertyTableModel = propertyTableModel;
    }
}
