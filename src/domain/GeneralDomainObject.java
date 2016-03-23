/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author consc
 */
public interface GeneralDomainObject {
    
    String getTableName();
    
    String getValuesForInsert();
    
    String getColumnsForInsert();
    
    List<?> getObjectList(ResultSet rs) throws SQLException;
    
}
