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
public class Property extends AbstractTableModel implements GeneralDomainObject{
    
    private int propertyID;
    //private List<Property> property;
    private String name;
    private int structureID;
    private int propertyTypeID;
    private double price;
    private String address;
    private String area;
    private int furnitureID;
    private String description;
    private int cityAreaID;
    private int statusID;
    private int customerID; 
    private List<Customers> custList;
    private List<Property> propertyList;

    public Property() {
    }
   
    public int getPropertyID() {
        return propertyID;
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

    public int getStructureID() {
        return structureID;
    }

    public void setStructureID(int structureID) {
        this.structureID = structureID;
    }

    public int getPropertyTypeID() {
        return propertyTypeID;
    }

    public void setPropertyTypeID(int propertyTypeID) {
        this.propertyTypeID = propertyTypeID;
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

    public int getFurnitureID() {
        return furnitureID;
    }

    public void setFurnitureID(int furnitureID) {
        this.furnitureID = furnitureID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCityAreaID() {
        return cityAreaID;
    }

    public void setCityAreaID(int cityAreaID) {
        this.cityAreaID = cityAreaID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }         
    
    @Override
    public String getTableName() {
        return "property";
    }

    @Override
    public String getValuesForInsert() {
        return '('+ getName() + ','+getStructureID() + ',' +getPropertyTypeID()+','+getPrice()+','+getAddress()+','+getArea()+','+getFurnitureID()+','+"'"+getDescription()+"'"+','+getCityAreaID()+','+getStatusID()+','+getCustomerID()+')';
    }

    @Override
    public String getColumnsForInsert() {
        return "(property_name, structure_id, property_type_id, price, address, area, furniture_id, description, city_area_id, status_id, customer_id)";
    }

    @Override
    public List<GeneralDomainObject> getObjectList(ResultSet rs) throws SQLException {
        List<GeneralDomainObject> obj = new ArrayList<>();
        while(rs.next()){
            Users user = new Users();
            user.setUserID(rs.getInt("user_id"));
            user.setUserName(rs.getString("user"));
            user.setUserPassword(rs.getString("user_password"));
            user.setGroupID(rs.getInt("group_id"));
            user.setStatusID(rs.getInt("status_id"));      
            obj.add(user);
        }
        return obj;
    }    

    public List<Customers> getCustList() {
        return custList;
    }

    public void setCustList(List<Customers> custList) {
        this.custList = custList;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
