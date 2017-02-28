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
            
            Structure structure = new Structure();
            structure.setID(rs.getInt("structure_id"));
            structure.setName(rs.getString("structure"));
            property.setStructureID(structure);

            PropertyType pt = new PropertyType();
            
            pt.setID(rs.getInt("property_type_id"));
            pt.setName("property_type");
            property.setPropertyTypeID(pt);
                System.out.println(""+rs.getDouble("structure.price"));
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

    @Override
    public String getTableNameWithJoins() {
        
        String innerjoinClause =  "property p INNER JOIN structure s on p.structure_id=s.structure_id "
                + " INNER JOIN property_type pt on p.property_type_id=pt.property_type_id";
        return innerjoinClause;
    }

    @Override
    public String getFieldList() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return "*"; /*"`p`.`address` AS `address`,\n `p`.`area` AS `area`,\n `p`.`city_area_id` AS `fk_city_area_id`,\n `p`.`customer_id` AS `fk_customer_id`,\n" +
"        `p`.`description` AS `description`,\n `p`.`furniture_id` AS `fk_furniture_id`,\n `p`.`price` AS `price`,\n `p`.`property_id` AS `property_id`,\n" +
"        `p`.`property_name` AS `property_name`,\n `p`.`property_type_id` AS `fk_property_type_id`,\n" +
"        `p`.`structure_id` AS `fk_structure_id`,\n" +
"        `p`.`status_id` AS `fk_status_id`,\n" +
"        `pty`.`property_type` AS `property_type`,\n" +
"        `pty`.`property_type_id` AS `property_type_id`,\n" +
"        `s`.`structure` AS `structure`,\n" +
"        `s`.`structure_id` AS `structure_id`,\n" +
"        `f`.`furniture` AS `furniture`,\n" +
"        `f`.`furniture_id` AS `furniture_id`,\n" +
"        `c`.`address` AS `customer_address`,\n" +
"        `c`.`city` AS `city`,\n" +
"        `c`.`country_id` AS `fk_country_id`,\n" +
"        `c`.`customer_fname` AS `customer_fname`,\n" +
"        `c`.`customer_id` AS `customer_id`,\n" +
"        `c`.`customer_lname` AS `customer_lname`,\n" +
"        `c`.`customer_mname` AS `customer_mname`,\n" +
"        `c`.`email` AS `email`,\n" +
"        `c`.`indentification_id` AS `fk_indentification_id`,\n" +
"        `c`.`phone_number` AS `phone_number`,\n" +
"        `c`.`status_id` AS `fk_customer_status`,\n" +
"        `c`.`user_id` AS `fk_user_id`,\n" +
"        `sp`.`status` AS `status`,\n" +
"        `sp`.`status_id` AS `status_id`,\n" +
"        `ca`.`city_Area` AS `city_Area`,\n" +
"        `ca`.`city_Area_id` AS `city_Area_id`,\n" +
"        `ca`.`town` AS `town`,\n" +
"        `ca`.`zip_code` AS `zip_code`"; */
    }
}
