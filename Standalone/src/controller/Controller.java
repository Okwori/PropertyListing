/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DatabaseBroker;
import domain.*;
import java.sql.SQLException;
import java.util.List;
import model.PropertyTableModel;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Controller extends Exception {

    private static Controller instance;
    DatabaseBroker dbb = new DatabaseBroker();
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
    
    private PropertyTableModel propertyTableModel = new PropertyTableModel();

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
        dbb.insertDomainObject(gdo);
    }

    public List<GeneralDomainObject> listCombos(GeneralDomainObject gdo) throws SQLException, Exception {
        return dbb.ListDomainObject(gdo, "");
    }
    
    public  List<GeneralDomainObject> listLoggedInUser(String username, String user_password) throws SQLException, Exception{
        return dbb.ListDomainObject(getUser(), " where user='"+username+"' and user_password='"+user_password+"'");
    }
    
    public List<GeneralDomainObject> listDomainObjects(GeneralDomainObject gdo, String whereClause) throws SQLException, Exception{
        return dbb.ListDomainObject(gdo, whereClause);
    }
    
    public List<GeneralDomainObject> listPropertyDomainObjects() throws SQLException, Exception{
        return dbb.ListDomainObjectWithJoin(new Property(), "");
    }

    public PropertyTableModel getPropertyTableModel() {
        return propertyTableModel;
    }

   public void setPropertyTableModel(PropertyTableModel propertyTableModel) {
        this.propertyTableModel = propertyTableModel;
    }
}
