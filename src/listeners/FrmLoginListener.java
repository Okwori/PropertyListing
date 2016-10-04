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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import model.PropertyTableModel;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class FrmLoginListener implements ActionListener {

    FrmLogin frmLogin;
    FrmList frmList;
    FrmHome frmHome;
    FrmCustomer frmCustomer;
    FrmIndentification frmIndentification;
    FrmDashboardAdmin frmDashboardAdmin;
    FrmClient frmClient;
    FrmUsers frmUsers;

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

    public FrmLoginListener(FrmCustomer frmCustomer) {
        this.frmCustomer = frmCustomer;
    }

    public FrmLoginListener(FrmIndentification frmIndentification) {
        this.frmIndentification = frmIndentification;
    }

    public FrmLoginListener(FrmDashboardAdmin frmDashboardAdmin) {
        this.frmDashboardAdmin = frmDashboardAdmin;
    }

    public FrmLoginListener(FrmClient frmClient) {
        this.frmClient = frmClient;
    }

    public FrmLoginListener(FrmUsers frmUsers) {
        this.frmUsers = frmUsers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void registerUsers(ActionEvent e) {
        try {
            if ("".equals(frmLogin.getTxtNewUsername().getText().trim()) | "".equals(frmLogin.getjPasswordField_enterPwd().getText().trim())) {
                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");
            } else if (frmLogin.getjPasswordField_enterPwd().getText().equals(frmLogin.getjPasswordField_confirmPwd().getText()) == false) {
                javax.swing.JOptionPane.showMessageDialog(null, "Password did not match! Try Again!");
            } else {
                Users user = Controller.getInstance().getUser();
                user.setUserName("'" + frmLogin.getTxtNewUsername().getText() + "'");
                user.setUserPassword("'" + frmLogin.getjPasswordField_enterPwd().getText() + "'");
                user.setUserGroup((UserGroup) frmLogin.getCombo_UserGroup().getSelectedItem());
                user.setStatusID(1);

                Controller.getInstance().insertDomainObject(user);

                javax.swing.JOptionPane.showMessageDialog(null, "Successfully registered " + frmLogin.getTxtNewUsername().getText().toUpperCase() + ". You can now Login with your details!");
                frmLogin.getTxtNewUsername().setText("");
                frmLogin.getjPasswordField_confirmPwd().setText("");
                frmLogin.getjPasswordField_confirmPwd().setText("");
                frmLogin.getjTabbedPane1().setSelectedIndex(0);
            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "User already exits");
            //Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Something is not right! Check Form Listerner.registerUser.java");
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
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
                        FrmDashboardAdmin frmDashboardAdmin = new FrmDashboardAdmin();
                        frmDashboardAdmin.setVisible(true);
                        //javax.swing.JOptionPane.showMessageDialog(null, "Perform Admin Tasks!");
                        break;
                    case 2:
                        FrmHome frmHome = new FrmHome();
                        frmHome.setVisible(true);
                        //javax.swing.JOptionPane.showMessageDialog(null, "Perform Help Desk Tasks");
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
                javax.swing.JOptionPane.showMessageDialog(null, "Username or Password is incorrect! Please Try again" );//+ listGdo.size());
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
                frmLogin.getCombo_UserGroup().addItem(((UserGroup) listGDO.get(i)));
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
                frmList.getComBoxStructure().addItem(listGdo_structure.get(i));
            }
            for (int i = 0; i < listGdo_cityArea.size(); i++) {
                frmList.getComBoxCityArea().addItem(listGdo_cityArea.get(i));
            }
            for (int i = 0; i < listGdo_furniture.size(); i++) {
                frmList.getComBoxFurniture().addItem(listGdo_furniture.get(i));
            }
            for (int i = 0; i < listGdo_ppty.size(); i++) {
                frmList.getComBoxPropertyType().addItem(listGdo_ppty.get(i));
            }
            for (int i = 0; i < listGDO_customers.size(); i++) {
                frmList.getComboCustomer().addItem(listGDO_customers.get(i));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillFrmListCombosFrmHome() {
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
                frmHome.getComBoxStructure().addItem(listGdo_structure.get(i));
            }
            for (int i = 0; i < listGdo_cityArea.size(); i++) {
                frmHome.getComBoxCityArea().addItem(listGdo_cityArea.get(i));
            }
            for (int i = 0; i < listGdo_furniture.size(); i++) {
                frmHome.getComBoxFurniture().addItem(listGdo_furniture.get(i));
            }
            for (int i = 0; i < listGdo_ppty.size(); i++) {
                frmHome.getComBoxPropertyType().addItem(listGdo_ppty.get(i));
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
                property.setFurnitureID((Furniture) frmList.getComBoxFurniture().getSelectedItem());
                property.setPropertyTypeID((PropertyType) frmList.getComBoxPropertyType().getSelectedItem());
                property.setCityAreaID((CityArea) frmList.getComBoxCityArea().getSelectedItem());
                property.setStructureID((Structure) frmList.getComBoxStructure().getSelectedItem());
                property.setDescription(frmList.getTxtAreaDescription().getText());
                property.setCustomerID((Customers) frmList.getComboCustomer().getSelectedItem());

                property.setStatusID(1);

                Controller.getInstance().insertDomainObject(property);

                javax.swing.JOptionPane.showMessageDialog(null, "You have successfully registered!");

            }
        } catch (SQLException ex) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Kindly check the values entered!");
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Something is not right! Check Form Listerner.registerUser.java");
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCustomer(ActionEvent e) {
        try {
            if ("".equals(frmCustomer.getTxtFirstName().getText().trim()) | "".equals(frmCustomer.getTxtMiddleName().getText().trim()) | "".equals(frmCustomer.getTxtLastName().getText().trim())
                    | "".equals(frmCustomer.getTxtPhoneNumber().getText().trim()) | "".equals(frmCustomer.getTxtEmail().getText().trim()) | frmCustomer.getComboCountry().getSelectedIndex() < 0 | frmCustomer.getComboIndentification().getSelectedIndex() < 0 | frmCustomer.getComboCity().getSelectedIndex() < 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");
            } else {
                Customers customers = Controller.getInstance().getCustomer();
                customers.setFirstName("'" + frmCustomer.getTxtFirstName().getText() + "'");
                customers.setLastName("'" + frmCustomer.getTxtLastName().getText() + "'");
                customers.setPhoneNumber("'" + frmCustomer.getTxtPhoneNumber().getText() + "'");
                customers.setAddress("'" + frmCustomer.getTxtArea_address().getText() + "'");
                customers.setEmail("'" + frmCustomer.getTxtEmail().getText() + "'");
                customers.setCity((CityArea) frmCustomer.getComboCity().getSelectedItem());
                customers.setCountry((Country) frmCustomer.getComboCountry().getSelectedItem());
                customers.setIndentification((Indentification) frmCustomer.getComboIndentification().getSelectedItem());
                customers.setiDNumber("'" + frmCustomer.getTxtIdNumber().getText() +"'");
                customers.setmName("'" + frmCustomer.getTxtMiddleName().getText() + "'");

                customers.setStatusID(1);
                customers.setUserID(2);//Controller.getInstance().getUser().getUserID());

                Controller.getInstance().insertDomainObject(customers);

                javax.swing.JOptionPane.showMessageDialog(null, "You have successfully registered!");
            }
        } catch (SQLException ex) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Kindly check the values entered!");
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
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

    public void resetCustomer(ActionEvent e) {
        frmCustomer.getTxtArea_address().setText(null);
        frmCustomer.getTxtEmail().setText(null);
        frmCustomer.getTxtFirstName().setText(null);
        frmCustomer.getTxtLastName().setText(null);
        frmCustomer.getTxtMiddleName().setText(null);
        frmCustomer.getTxtPhoneNumber().setText(null);
        frmCustomer.getComboCity().setSelectedItem(null);
        frmCustomer.getComboIndentification().setSelectedItem(null);
        frmCustomer.getComboCountry().setSelectedItem(null);
    }

    public void cancelProperty(ActionEvent e) {
        frmList.dispose();
    }
    
    public void registerAgent(){
        // TODO
    }
    
    public void insertClient(){
        // TODO
    }

    public void cancelCustomer(ActionEvent e) {
        frmCustomer.dispose();
    }

    public void fillPropertyTable() {
        try {
            PropertyTableModel listProperty = Controller.getInstance().getPropertyTableModel();
            List<GeneralDomainObject> ppty;
            ppty = Controller.getInstance().listCombos(listProperty);
            frmHome.getTblMain().setModel(new PropertyTableModel(ppty));
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillFrmCustomerCombos() {
        List<GeneralDomainObject> listGdo_identification, listGdo_city, listGdo_country;// listGdo_cityArea, listGDO_customers;
        Indentification indentification = Controller.getInstance().getIndentification();
        CityArea cityArea = Controller.getInstance().getCityArea();
        Country country = Controller.getInstance().getCountry();
        try {
            listGdo_identification = Controller.getInstance().listCombos(indentification);
            listGdo_city = Controller.getInstance().listCombos(cityArea);
            listGdo_country = Controller.getInstance().listCombos(country);
            for (int i = 0; i < listGdo_identification.size(); i++) {
                frmCustomer.getComboIndentification().addItem(listGdo_identification.get(i));
            }
            for (int i = 0; i < listGdo_city.size(); i++) {
                frmCustomer.getComboCity().addItem(listGdo_city.get(i));
            }
            for (int i = 0; i < listGdo_country.size(); i++) {
                frmCustomer.getComboCountry().addItem(listGdo_country.get(i));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillCountry() {
        List<GeneralDomainObject> listGdo_country;// listGdo_city, listGdo_country;// listGdo_cityArea, listGDO_customers;
        Country country = Controller.getInstance().getCountry();
        try {
            listGdo_country = Controller.getInstance().listCombos(country);
            for (int i = 0; i < listGdo_country.size(); i++) {
                frmIndentification.getComboCountry().addItem(listGdo_country.get(i));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetIndentification() {
        frmIndentification.getTxtName().setText(null);
        frmIndentification.getTxtNumber().setText(null);
        frmIndentification.getTxtExpiryDate().setText(null);
        frmIndentification.getComboCountry().setSelectedItem(null);
    }

    public void cancelAdminDashBoard(ActionEvent evt) {
        frmDashboardAdmin.dispose();
    }

    public void fillFrmDashBoardAdminLabels() {
        try {
            List<GeneralDomainObject> listCustomersActive, listCustomersInactive, listClientsActive, listClientsInactive, listAgentsActive, listagentsInactive, listHelpDeskActive, listHelpDeskInactive,
                    listAgentAdminActive, listAgentAdminInactive, listRentActive, listRentInactive, listSaleActive, listSaleInactive;
            Customers customers = Controller.getInstance().getCustomer();

            //Customers
            listCustomersActive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 4 and a.status_id = 1");
            frmDashboardAdmin.getLblActiveCustomers().setText(listCustomersActive.size() + "");

            listCustomersInactive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 4 and a.status_id = 2");
            frmDashboardAdmin.getLblInactiveCustomers().setText(listCustomersInactive.size() + "");

            //Client
            listClientsActive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 5 and a.status_id = 1");
            frmDashboardAdmin.getLblActiveClients().setText(listClientsActive.size() + "");

            listClientsInactive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 5 and a.status_id = 2");
            frmDashboardAdmin.getLblClientInactive().setText(listClientsInactive.size() + "");

            //Agent
            listAgentsActive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 6 and a.status_id = 1");
            frmDashboardAdmin.getLblAgentsActive().setText(listAgentsActive.size() + "");

            listagentsInactive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 6 and a.status_id = 2");
            frmDashboardAdmin.getLblAgentsInactive().setText(listagentsInactive.size() + "");

            //Help Desk 
            listHelpDeskActive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 2 and a.status_id = 1");
            frmDashboardAdmin.getLblHelpDeskActive().setText(listHelpDeskActive.size() + "");

            listHelpDeskInactive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 2 and a.status_id = 2");
            frmDashboardAdmin.getLblHelpDeskInactive().setText(listHelpDeskInactive.size() + "");

            //Agent Admin
            listAgentAdminActive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 3 and a.status_id = 1");
            frmDashboardAdmin.getLblAgentAdminActive().setText(listAgentAdminActive.size() + "");

            listAgentAdminInactive = Controller.getInstance().listDomainObjects(customers, " a inner join user b on a.user_id = b.user_id where b.group_id = 3 and a.status_id = 2");
            frmDashboardAdmin.getLblAgentAdminInactive().setText(listAgentAdminInactive.size() + "");

            //Admin
            Users users = Controller.getInstance().getUser();
            listAgentAdminInactive = Controller.getInstance().listDomainObjects(users, " where group_id = 1 and status_id = 1");
            frmDashboardAdmin.getLblAdminActive().setText(listAgentAdminInactive.size() + "");

            listAgentAdminInactive = Controller.getInstance().listDomainObjects(users, " where group_id = 1 and status_id = 2");
            frmDashboardAdmin.getLblAdminInactive().setText(listAgentAdminInactive.size() + "");

            //Rent
            Property property = Controller.getInstance().getProperty();
            listRentActive = Controller.getInstance().listDomainObjects(property, " a inner join status_property b on a.status_id = b.status_id where a.status_id in (1,2)");
            frmDashboardAdmin.getLblPropertyActive().setText(listRentActive.size() + "");

            listRentInactive = Controller.getInstance().listDomainObjects(property, " a inner join status_property b on a.status_id = b.status_id where a.status_id in (3,4)");
            frmDashboardAdmin.getLblPropertyInactive().setText(listRentInactive.size() + "");

            //Sale
            listSaleActive = Controller.getInstance().listDomainObjects(property, " a inner join status_property b on a.status_id = b.status_id where a.status_id = 5");
            frmDashboardAdmin.getLblPropertySaleActive().setText(listSaleActive.size() + "");

            listSaleInactive = Controller.getInstance().listDomainObjects(property, " a inner join status_property b on a.status_id = b.status_id where a.status_id in (6,7)");
            frmDashboardAdmin.getLblPropertiesSaleInactive().setText(listSaleInactive.size() + "");
        } catch (Exception e) {
            Logger.getLogger(FrmDashboardAdmin.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void showPropertyForm(ActionEvent evt) {
        new FrmList().setVisible(true);
    }

    public void showCustomerForm(ActionEvent evt) {
        new FrmCustomer().setVisible(true);
    }

    public void showClientsForm(ActionEvent evt) {
        new FrmClient().setVisible(true);
    }

    public void showFormList(ActionEvent evt) {
        new FrmHome().setVisible(true);
    }

    public void exitFrmHome(ActionEvent evt) {
        frmHome.dispose();
    }

    public void cancelClientsForm(ActionEvent evt) {
        frmClient.dispose();
    }

    public void resetClientsForm(ActionEvent evt) {
        //frmClient.getTxtName().setText(null);
        frmIndentification.getTxtNumber().setText(null);
        frmIndentification.getTxtExpiryDate().setText(null);
        frmIndentification.getComboCountry().setSelectedItem(null);
    }

    public void insertUser(ActionEvent evt) {
        try {
            if ("".equals(frmUsers.getTxtUserName().getText().trim()) | "".equals(frmUsers.getTxtPassword().getText().trim()) | "".equals(frmUsers.getTxtPasswordConfirm().getText().trim())) {
                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");
            } else if (!(frmUsers.getTxtPassword().getText().trim().equals(frmUsers.getTxtPasswordConfirm().getText().trim()))) {
                javax.swing.JOptionPane.showMessageDialog(null, "Password does not match!");
            } else {
                Users users = new Users();
                users.setUserName("'" + frmUsers.getTxtUserName().getText() + "'");
                users.setUserPassword("'" + frmUsers.getTxtPassword().getText() + "'");
                users.setUserGroup((UserGroup) frmUsers.getComboUserGroup().getSelectedItem());

                users.setStatusID(1);

                Controller.getInstance().insertDomainObject(users);

                javax.swing.JOptionPane.showMessageDialog(null, "You have successfully registered the user!");
            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "User already exit, change kindly change your password!");
            //Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //javax.swing.JOptionPane.showMessageDialog(null, "Something is not right! Check Form Listerner.registerUser.java");
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelUsers(ActionEvent evt) {
        frmUsers.dispose();
    }

    public void resetUser(ActionEvent evt) {
        frmUsers.getTxtUserName().setText(null);
        frmUsers.getTxtPassword().setText(null);
        frmUsers.getTxtPasswordConfirm().setText(null);
        frmUsers.getComboUserGroup().setSelectedItem(null);
    }

    public void fillFrmUsersCombos() {
        List<GeneralDomainObject> listUserGroups;
        UserGroup userGroup = Controller.getInstance().getUserGroup();
        try {
            listUserGroups = Controller.getInstance().listCombos(userGroup);
            for (int i = 1; i < listUserGroups.size(); i++) {
                frmUsers.getComboUserGroup().addItem(listUserGroups.get(i));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showFormUsers(ActionEvent evt) {
        new FrmUsers().setVisible(true);
    }
}
