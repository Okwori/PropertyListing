/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Customers implements GeneralDomainObject{
    private int customerID;
    private String firstName;
    private String lastName;
    private String mName;
    private int indentNo;
    private String email;
    private String phoneNumber;
    private String address;
    private int cityID;
    private int statusID;
    private int countryID;
    private int userID;

    public Customers() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public int getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(int indentNo) {
        this.indentNo = indentNo;
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

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
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

    @Override
    public String getValuesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnsForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public List<GeneralDomainObject> getObjectList(ResultSet rs) throws SQLException {
        List<GeneralDomainObject> obj = new ArrayList<>();
        while(rs.next()){
            Customers customers = new Customers();
            customers.setAddress(rs.getString("address"));
            customers.setCityID(rs.getInt("city"));
            customers.setCountryID(rs.getInt("country_id"));
            customers.setEmail(rs.getString("email"));
            customers.setFirstName(rs.getString("customer_fname"));
            customers.setIndentNo(rs.getInt("indentification_id"));
            customers.setLastName(rs.getString("customer_lname"));
            customers.setPhoneNumber(rs.getString("phone_number"));
            customers.setStatusID(rs.getInt("status_id"));
            customers.setUserID(rs.getInt("user_id"));
            customers.setmName(rs.getString("customer_mname"));
            customers.setCustomerID(rs.getInt("customer_id"));
            obj.add(customers);
        }
        return obj;    
    }

    @Override 
    public String toString() {
        return getFirstName()+" "+getmName()+" "+getLastName()+" "; //To change body of generated methods, choose Tools | Templates.
    }
}
