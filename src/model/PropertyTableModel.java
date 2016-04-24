/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import domain.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class PropertyTableModel extends AbstractTableModel implements GeneralDomainObject{
    
    List<GeneralDomainObject> propertyList;
    
    private String name;
    private Structure structureID;
    private PropertyType propertyTypeID;
    private double price;
    private String address;
    private String area;
    private Furniture furnitureID;
    private String description;
    private CityArea cityAreaID;
    private int statusID;
    private Customers customerID;
    private String structure;
    private String propertyType;
    private String furniture;

    public PropertyTableModel() {
    }
   
    public PropertyTableModel(List<GeneralDomainObject> propertyList) {
        this.propertyList = propertyList;
    }
    
    @Override
    public int getRowCount() {
        return propertyList.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Property propertyTableModel = (Property)propertyList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return propertyTableModel.getName();            
            case 1:
                return propertyTableModel.getStructure();
            case 2:
                return propertyTableModel.getPropertyType();
            case 3:
                return propertyTableModel.getPrice();
            case 4:
                return propertyTableModel.getAddress();
            case 5: 
                return propertyTableModel.getArea();
            case 6:
                return propertyTableModel.getFurniture();
            case 7:
                return propertyTableModel.getDescription();            
        }
        return null;     
    }

    @Override
    public String getTableName() {
        return "propertyListing";
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
        while (rs.next()) {
            Property property = new Property();
            property.setPropertyID(rs.getInt("property_id"));
            property.setName(rs.getString("property_name"));
            property.setStructure(rs.getString("structure"));
            property.setPropertyType(rs.getString("property_type"));
            property.setPrice(rs.getInt("price"));
            property.setAddress(rs.getString("address"));
            property.setArea(rs.getString("area"));
            property.setFurniture(rs.getString("furniture"));
            property.setDescription(rs.getString("description"));
            obj.add(property);
        }
        return obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }
}
