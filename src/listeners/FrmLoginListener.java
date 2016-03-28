/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import db.DatabaseBroker;
import domain.Users;
import domain.UserGroup;
import gui.FrmLogin;
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
    Controller controller;
    
    public FrmLoginListener(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void registerUsers(ActionEvent e) {
        try {
            //Users user = new Users();
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

                Controller.getInstance().insertUsers();

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
//        try {
//            Users user = new Users();
//            if ("".equals(frmLogin.getTxt_username().getText().trim()) | "".equals(frmLogin.getTxt_password().getText().trim())) {
//                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");            
//            } else {
//                if(frmLogin.getTxt_username().getText().trim().equals(""){
//                    
//                    FrmLogin.setIsLogin(1);
//                }
//                
//                javax.swing.JOptionPane.showMessageDialog(null, "Successfully registered " + frmLogin.getTxtNewUsername().getText().toUpperCase() + ". Wait admin confirmation to continue to Login");
//                frmLogin.getTxtNewUsername().setText(""); frmLogin.getTxt_pwdConfirm().setText(""); frmLogin.getTxt_pwdConfirm().setText("");
//                frmLogin.getjTabbedPane1().setSelectedIndex(0);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void fillUserGroupCombo() {        
        List<GeneralDomainObject> listGDO;
        try {
            listGDO = Controller.getInstance().listUserGroupCombo();
            for (int i = 0; i < listGDO.size(); i++) {
                UserGroup ugp = (UserGroup) listGDO.get(i);
                frmLogin.getCombo_UserGroup().addItem(ugp.getGroupName());
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmLoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
