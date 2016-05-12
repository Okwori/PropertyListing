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

/*
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class Users implements GeneralDomainObject {

    private int userID;
    private String userName;
    private UserGroup userGroup;
    private String userPassword;
    private int GroupID;
    private int statusID;

    public Users() {
        userName = null;
        userPassword = null;
        GroupID = 0;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public Users(String userName, String userPassword, UserGroup userGroup) {
        //this.userID = userID;
        this.userName = userName;
        this.userGroup = userGroup;
        this.userPassword = userPassword;
    }

    public Users(String userName, String password, int GroupID) {
        this.userName = userName;
        this.userPassword = password;
        this.GroupID = GroupID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getValuesForInsert() {
        return '('+ getUserName()+','+ getUserPassword() +',' + getUserGroup().getGroupID()+ ',' + getStatusID() + ')';
    }

    @Override
    public String getColumnsForInsert() {
        return "(user, user_password, group_id, status_id)";
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int GroupID) {
        this.GroupID = GroupID;
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
    
    public String loginWhereClause(String username, String password){
        String query = " where user="+username+" and user_password="+password;
        return query;
    }
}
