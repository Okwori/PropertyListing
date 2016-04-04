/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import com.sun.java.swing.plaf.windows.resources.windows;
import db.DatabaseBroker;
import domain.*;
import domain.UserGroup;
import gui.*;
import controller.Controller;
import domain.GeneralDomainObject;
import domain.UserGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class FrmLoginListener implements ActionListener {

    FrmLogin frmLogin;
    FrmList frmList;
    FrmHome frmHome;

    Controller controller;

    public FrmLoginListener(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
    }

    public FrmLoginListener(FrmList frmList) {
        this.frmList = frmList;
    }

    public FrmLoginListener(FrmHome frmHome) {
        this.frmHome = frmHome;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void registerUsers(ActionEvent e) {
        try {
            if ("".equals(frmLogin.getTxtNewUsername().getText().trim()) | "".equals(frmLogin.getTxt_pwd().getText().trim())) {
                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");
            } else if (frmLogin.getTxt_pwd().getText().equals(frmLogin.getTxt_pwdConfirm().getText()) == false) {
                javax.swing.JOptionPane.showMessageDialog(null, "Password did not match! Try Again!");
            } else {
                Users user = Controller.getInstance().getUser();
                user.setUserName("'" + frmLogin.getTxtNewUsername().getText() + "'");
                user.setUserPassword("'" + frmLogin.getTxt_pwd().getText() + "'");
                user.setGroupID(frmLogin.getCombo_UserGroup().getSelectedIndex() + 1);
                user.setStatusID(1);

                Controller.getInstance().insertDomainObject(user);

                javax.swing.JOptionPane.showMessageDialog(null, "Successfully registered " + frmLogin.getTxtNewUsername().getText().toUpperCase() + ". You can now Login with your details!");
                frmLogin.getTxtNewUsername().setText("");
                frmLogin.getTxt_pwdConfirm().setText("");
                frmLogin.getTxt_pwdConfirm().setText("");
                frmLogin.getjTabbedPane1().setSelectedIndex(0);
            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "User already exits");
            //Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Something is not right! Check Form Listerner.registerUser.java");
            //Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logginUser(ActionEvent e) {
        List<GeneralDomainObject> listGdo;
        try {
            String username = frmLogin.getTxt_username().getText().trim();
            String password = frmLogin.getTxt_password().getText().trim();
            listGdo = Controller.getInstance().listLoggedInUser(username, password);
            if (listGdo.size() > 0) {
                Users obj = (Users) listGdo.get(0);
                switch (obj.getGroupID()) {
                    case 1:
                        javax.swing.JOptionPane.showMessageDialog(null, "Perform Admin Tasks!");
                        break;
                    case 2:
                        javax.swing.JOptionPane.showMessageDialog(null, "Perform Help Desk Tasks");
                        break;
                    case 3:
                        javax.swing.JOptionPane.showMessageDialog(null, "Perform Agent Admin Tasks");
                        break;
                    default:
                        javax.swing.JOptionPane.showMessageDialog(null, "Something is not right! Your UserGroup doesnt exit!");
                        break;
                }
                javax.swing.JOptionPane.showMessageDialog(null, "Username or Password is CORRECT!" + listGdo.size() + "Status ID:" + obj.getGroupID());
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Username or Password is incorrect! Please Try again" + listGdo.size());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillUserGroupCombo() {
        List<GeneralDomainObject> listGDO;
        UserGroup ugp = Controller.getInstance().getUserGroup();
        try {
            listGDO = Controller.getInstance().listCombos(ugp);
            for (int i = 0; i < listGDO.size(); i++) {
                frmLogin.getCombo_UserGroup().addItem(((UserGroup) listGDO.get(i)).getGroupName());
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillFrmListCombos() {
        List<GeneralDomainObject> listGdo_structure, listGdo_furniture, listGdo_ppty, listGdo_cityArea, listGDO_customers;
        Structure structure = Controller.getInstance().getStructure();
        Furniture furniture = Controller.getInstance().getFurniture();
        PropertyType ppty = Controller.getInstance().getPropertyType();
        CityArea cityArea = Controller.getInstance().getCityArea();
        Customers customers = Controller.getInstance().getCustomer();
        try {
            listGdo_structure = Controller.getInstance().listCombos(structure);
            listGdo_furniture = Controller.getInstance().listCombos(furniture);
            listGdo_ppty = Controller.getInstance().listCombos(ppty);
            listGdo_cityArea = Controller.getInstance().listCombos(cityArea);
            listGDO_customers = Controller.getInstance().listCombos(customers);
            for (int i = 0; i < listGdo_structure.size(); i++) {
                frmList.getComBoxStructure().addItem(((Structure) listGdo_structure.get(i)).getName());
            }
            for (int i = 0; i < listGdo_cityArea.size(); i++) {
                frmList.getComBoxCityArea().addItem(((CityArea) listGdo_cityArea.get(i)).getName());
            }
            for (int i = 0; i < listGdo_furniture.size(); i++) {
                frmList.getComBoxFurniture().addItem(((Furniture) listGdo_furniture.get(i)).getName());                
            }
            for (int i = 0; i < listGdo_ppty.size(); i++) {
                frmList.getComBoxPropertyType().addItem(((PropertyType) listGdo_ppty.get(i)).getName());
            }
            for (int i = 0; i < listGDO_customers.size(); i++) {
                frmList.getComboCustomer().addItem(((Customers) listGDO_customers.get(i)).getFirstName()+" " +((Customers) listGDO_customers.get(i)).getmName()+" "+((Customers) listGDO_customers.get(i)).getLastName());
            }            
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertProperty(ActionEvent e) {
        try {
            if ("".equals(frmList.getTxtNameOfProperty().getText().trim()) | "".equals(frmList.getTxtAddress().getText().trim()) | "".equals(frmList.getTxtPrice().getText().trim())
                    | "".equals(frmList.getTxtArea().getText().trim()) | frmList.getComBoxFurniture().getSelectedIndex() < 0 | frmList.getComBoxPropertyType().getSelectedIndex() < 0 | frmList.getComBoxCityArea().getSelectedIndex() < 0 | frmList.getComBoxStructure().getSelectedIndex() < 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");
            } else {
                Property property = Controller.getInstance().getProperty();
                property.setName("'" + frmList.getTxtNameOfProperty().getText() + "'");
                property.setAddress("'" + frmList.getTxtAddress().getText() + "'");
                property.setPrice(Double.parseDouble(frmList.getTxtPrice().getText()));
                property.setArea("'" + frmList.getTxtArea().getText() + "'");
                property.setFurnitureID(frmList.getComBoxFurniture().getSelectedIndex() + 1);
                property.setPropertyTypeID(frmList.getComBoxPropertyType().getSelectedIndex() + 1);
                property.setCityAreaID(frmList.getComBoxCityArea().getSelectedIndex() + 1);
                property.setStructureID( frmList.getComBoxStructure().getSelectedIndex() + 1);
                property.setDescription(frmList.getTxtAreaDescription().getText());     
                property.setCustomerID(frmList.getComboCustomer().getSelectedIndex() + 1);
                
                property.setStatusID(1);
                        
                Controller.getInstance().insertDomainObject(property);
                     
                javax.swing.JOptionPane.showMessageDialog(null, "You have successfully registered!");
                                                     
        }
        }catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Kindly check the values entered!");
           //Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Something is not right! Check Form Listerner.registerUser.java");
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void resetProperty(ActionEvent e) {
        frmList.getTxtPrice().setText(null);
        frmList.getTxtAddress().setText(null);
        frmList.getTxtArea().setText(null);
        frmList.getTxtAreaDescription().setText(null);
        frmList.getTxtNameOfProperty().setText(null);
        frmList.getComBoxCityArea().setSelectedItem(null);
        frmList.getComBoxPropertyType().setSelectedItem(null);
        frmList.getComBoxFurniture().setSelectedItem(null);
        frmList.getComBoxStructure().setSelectedItem(null);
    }
    
    public void cancelProperty(ActionEvent e){
        frmList.dispose();
    }
}
