/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transfer;

import java.io.Serializable;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class ClientRequest implements Serializable {

    int opertion;
    
    Object objectOfOperation;
    Object anotherObject;

    public ClientRequest() {
    }

    public int getOpertion() {
        return opertion;
    }

    public void setOpertion(int opertion) {
        this.opertion = opertion;
    }

    public Object getObjectOfOperation() {
        return objectOfOperation;
    }

    public void setObjectOfOperation(Object objectOfOperation) {
        this.objectOfOperation = objectOfOperation;
    }

    public Object getAnotherObject() {
        return anotherObject;
    }

    public void setAnotherObject(Object anotherObject) {
        this.anotherObject = anotherObject;
    }    
}