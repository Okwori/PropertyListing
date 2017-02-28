/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Indentification implements GeneralDomainObject{
    private int ID;
    private String name;
    private String number;
    private int countryID;
    private Date expriryDate;
    
    private Country country;

    public Indentification() {
    }

    public Indentification(int ID, String name, String number, int countryID, Date expriryDate, Country country) {
        this.ID = ID;
        this.name = name;
        this.number = number;
        this.countryID = countryID;
        this.expriryDate = expriryDate;
        this.country = country;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public Date getExpriryDate() {
        return expriryDate;
    }

    public void setExpriryDate(Date expriryDate) {
        this.expriryDate = expriryDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String getTableName() {
       return "indentification";
    }

    @Override
    public String getValuesForInsert() {
        return '(' + getName()+ ',' + getNumber()+ ',' + getCountry().getID()+ ',' + getExpriryDate() + ')';
    }

    @Override
    public String getColumnsForInsert() {
        return "(name, number, country_ID, expiry_date)";
    }

    @Override
    public List<GeneralDomainObject> getObjectList(ResultSet rs) throws SQLException {
       List<GeneralDomainObject> obj = new ArrayList<>();
        while(rs.next()){
            Indentification indentification = new Indentification();
            indentification.setID(rs.getInt("ID_id"));
            indentification.setName(rs.getString("name"));
            indentification.setNumber(rs.getString("number"));
            indentification.setCountryID(rs.getInt("country_ID"));
            indentification.setExpriryDate(rs.getDate("expiry_date"));
            obj.add(indentification);
        }
        return obj;  
    }
    
    @Override
    public String toString(){
        return getName();
    }

    @Override
    public String getTableNameWithJoins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFieldList() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return "*";
    }
}
