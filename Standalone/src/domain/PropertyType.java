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
public class PropertyType implements GeneralDomainObject{
    private String name;
    private int ID;

    public PropertyType() {
    }

    public PropertyType(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getTableName() {
        return "property_type";
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
            PropertyType pptype = new PropertyType();
            pptype.setID(rs.getInt("property_type_id"));
            pptype.setName(rs.getString("property_type"));   
            obj.add(pptype);
        }
        return obj;
    }

    @Override
    public String toString() {
        return getName(); //To change body of generated methods, choose Tools | Templates.
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