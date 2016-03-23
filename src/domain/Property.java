/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Property {
    
    private List<Property> property;
    private String name;
    private String structure;
    private String type;
    private double price;
    private String address;
    private String area;
    private String furniture;
    private String description;

    public Property() {
    }

    public Property(String name, String structure, String type, double price, String address, String area, String furniture, String description) {
       
        this.name = name;
        this.structure = structure;
        this.type = type;
        this.price = price;
        this.address = address;
        this.area = area;
        this.furniture = furniture;
        this.description = description;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getProperty() {
        return property;
    }

    public void setProperty(List<Property> property) {
        this.property = property;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
