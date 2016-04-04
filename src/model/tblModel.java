/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import domain.*;

/**
 * 
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class tblModel extends AbstractTableModel {
    
    List<Property> tblContent;

    @Override
    public int getRowCount() {
        return tblContent.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Property ppty = tblContent.get(rowIndex);
        /*
        switch(columnIndex){
            case 0: return ppty.getName();
            case 1: return ppty.getStructure();
            case 2: return ppty.getType();
            case 3: return ppty.getPrice();
            case 4: return ppty.getAddress();
            case 5: return ppty.getArea();
            case 6: return ppty.getFurniture();
            case 7: return ppty.getDescription();
*/
       // }        
        return null;        
    }
}
