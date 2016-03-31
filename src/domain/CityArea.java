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
 * @author consc
 */
public class CityArea implements GeneralDomainObject{
    private int cityAreaID;
    private String name;
    private String zipcode;
    private String town;

    public CityArea() {
    }
    
    public CityArea(String name, String zipcode, String town) {
        this.name = name;
        this.zipcode = zipcode;
        this.town = town;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String getTableName() {
        return "city_area";
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
            CityArea cityArea = new CityArea();
            cityArea.setCityAreaID(rs.getInt("city_Area_id"));
            cityArea.setName(rs.getString("city_Area"));
            cityArea.setZipcode(rs.getString("zip_code"));
            cityArea.setTown(rs.getString("town"));
            obj.add(cityArea);
        }
        return obj;
    }

    public int getCityAreaID() {
        return cityAreaID;
    }

    public void setCityAreaID(int cityAreaID) {
        this.cityAreaID = cityAreaID;
    }

 }