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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Property implements GeneralDomainObject {

    private int propertyID;
    //private List<Property> property;
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
    private int custID;
    private int structID, propTypeID, furnitID;

    private List<Customers> custList;
    private List<Property> propertyList;

    public Property() {
    }

    public Property(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public int getPropTypeID() {
        return propTypeID;
    }

    public void setPropTypeID(int propTypeID) {
        this.propTypeID = propTypeID;
    }

    public int getCustID() {
        return custID;
    }

    public int getStructID() {
        return structID;
    }

    public void setStructID(int structID) {
        this.structID = structID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public int getFurnitID() {
        return furnitID;
    }

    public void setFurnitID(int furnitID) {
        this.furnitID = furnitID;
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

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public Structure getStructureID() {
        return structureID;
    }

    public void setStructureID(Structure structureID) {
        this.structureID = structureID;
    }

    public PropertyType getPropertyTypeID() {
        return propertyTypeID;
    }

    public void setPropertyTypeID(PropertyType propertyTypeID) {
        this.propertyTypeID = propertyTypeID;
    }

    public Furniture getFurnitureID() {
        return furnitureID;
    }

    public void setFurnitureID(Furniture furnitureID) {
        this.furnitureID = furnitureID;
    }

    public CityArea getCityAreaID() {
        return cityAreaID;
    }

    public void setCityAreaID(CityArea cityAreaID) {
        this.cityAreaID = cityAreaID;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getTableName() {
        return "property";
    }

    @Override
    public String getValuesForInsert() {
        return '(' + getName() + ',' + getStructureID().getID() + ',' + getPropertyTypeID().getID() + ',' + price + ',' + getAddress() + ',' + getArea() + ',' + getFurnitureID().getID() + ',' + "'" + getDescription() + "'" + ',' + getCityAreaID().getCityAreaID() + ',' + getStatusID() + ',' + getCustomerID().getCustomerID() + ')';
    }

    @Override
    public String getColumnsForInsert() {
        return "(property_name, structure_id, property_type_id, price, address, area, furniture_id, description, city_area_id, status_id, customer_id)";
    }

    @Override
    public List<GeneralDomainObject> getObjectList(ResultSet rs) throws SQLException {
        List<GeneralDomainObject> obj = new ArrayList<>();
        while (rs.next()) {
            Property property = new Property();
            property.setName(rs.getString("property_name"));
            property.setStructID(rs.getInt("structure_id"));
            property.setPropTypeID(rs.getInt("property_type_id"));
            property.setPrice(rs.getInt("price"));
            property.setAddress(rs.getString("address"));
            property.setArea(rs.getString("area"));
            property.setFurnitID(rs.getInt("furniture_id"));
            property.setDescription(rs.getString("description"));
            property.setStatusID(rs.getInt("status_id"));
            property.setCustID(rs.getInt("customer_id"));
            property.setPropertyID(rs.getInt("property_id"));
            obj.add(property);
        }
        return obj;
    }

    public List<Customers> getCustList() {
        return custList;
    }

    public void setCustList(List<Customers> custList) {
        this.custList = custList;
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

    @Override
    public String toString() {
        return getName();
    }

}
