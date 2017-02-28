/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Customers implements GeneralDomainObject, Serializable{
    private int customerID;
    private String firstName;
    private String lastName;
    private String mName;
    private String email;
    private String phoneNumber;
    private String address;
    private int statusID;
    private int userID;
    private String iDNumber;
    
    private Country country;
    private CityArea city;
    private Indentification indentification;    

    public Customers() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getiDNumber() {
        return iDNumber;
    }

    public void setiDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String getTableName() {
        return "customer";
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public CityArea getCity() {
        return city;
    }

    public void setCity(CityArea city) {
        this.city = city;
    }

    public Indentification getIndentification() {
        return indentification;
    }

    public void setIndentification(Indentification indentification) {
        this.indentification = indentification;
    }
    
    @Override
    public String getValuesForInsert() {
        return '(' + getFirstName()+ ',' + getLastName() + ',' + getmName() + ',' + getIndentification().getID()+ ',' + getiDNumber() + ',' + getEmail()+ ',' + getPhoneNumber()+ ',' + getCity().getCityAreaID() + ',' + getStatusID() + ',' + getCountry().getID() + ',' + getUserID()+ ')';
    }

    @Override
    public String getColumnsForInsert() {
        return "(customer_fname, customer_lname, customer_mname, indentification_id, id_number, email, phone_number, city, status_id, country_id, user_id)";
    }
 
    @Override
    public List<GeneralDomainObject> getObjectList(ResultSet rs) throws SQLException {
        List<GeneralDomainObject> obj = new ArrayList<>();
        while(rs.next()){
            Customers customers = new Customers();
            customers.setAddress(rs.getString("address"));
            //customers.setCityID(rs.getInt("city"));
            //customers.setCountryID(rs.getInt("country_id"));
            customers.setEmail(rs.getString("email"));
            customers.setFirstName(rs.getString("customer_fname"));
            //customers.setIndentNo(rs.getInt("indentification_id"));
            customers.setLastName(rs.getString("customer_lname"));
            customers.setPhoneNumber(rs.getString("phone_number"));
            customers.setStatusID(rs.getInt("status_id"));
            customers.setUserID(rs.getInt("user_id"));
            customers.setmName(rs.getString("customer_mname"));
            customers.setCustomerID(rs.getInt("customer_id"));
            customers.setiDNumber(rs.getString("id_number"));
            
            obj.add(customers);
        }
        return obj;    
    }

    @Override 
    public String toString() {
        return getFirstName()+" "+getmName()+" "+getLastName()+" "; //To change body of generated methods, choose Tools | Templates.
    }
}
