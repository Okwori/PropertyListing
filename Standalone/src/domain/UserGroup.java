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

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class UserGroup implements GeneralDomainObject{
    
    private int groupID;
    private String groupName;

    public UserGroup() {
    }
    

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }  

    @Override
    public String getTableName() {
        return "user_group";
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
        while(rs.next()){
            UserGroup group = new UserGroup();
            group.setGroupID(rs.getInt("group_id"));
            group.setGroupName(rs.getString("group"));   
            obj.add(group);
        }
        return obj;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }   
    
    @Override
    public  String toString(){
        return getGroupName();
    }

    @Override
    public String getTableNameWithJoins() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFieldList() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return "*";
    }
}

