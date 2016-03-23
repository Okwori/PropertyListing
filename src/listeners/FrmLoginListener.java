/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import db.DatabaseBroker;
import domain.Users;
import gui.FrmLogin;
import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Users user = new Users();
            if ("".equals(frmLogin.getTxtNewUsername().getText().trim()) | "".equals(frmLogin.getTxt_pwd().getText().trim())) {
                javax.swing.JOptionPane.showMessageDialog(null, "You cannot leave any field empty");
            } else if (frmLogin.getTxt_pwd().getText().equals(frmLogin.getTxt_pwdConfirm().getText()) == false) {
                javax.swing.JOptionPane.showMessageDialog(null, "Password did not match! Try Again!");
            } else {
                user.setUserName("'" + frmLogin.getTxtNewUsername().getText() + "'");
                user.setUserPassword("'" + frmLogin.getTxt_pwd().getText() + "'");
                user.setGroupID(1);
                //DatabaseBroker dbor = new DatabaseBroker();
                Controller.getInstance().insertUsers();
                //dbor.insertDomainObject(user);
                
                javax.swing.JOptionPane.showMessageDialog(null, "Successfully registered " + frmLogin.getTxtNewUsername().getText().toUpperCase() + ". Wait admin confirmation to continue to Login");
                frmLogin.getTxtNewUsername().setText(""); frmLogin.getTxt_pwdConfirm().setText(""); frmLogin.getTxt_pwdConfirm().setText("");
                frmLogin.getjTabbedPane1().setSelectedIndex(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void logginUser(ActionEvent e){
        
    }
}