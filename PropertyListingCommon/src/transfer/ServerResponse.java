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
public class ServerResponse implements Serializable{

    int signal;
    
    Object resultOfOperation;
    
    Exception ex;

    public ServerResponse() {
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public Object getResultOfOperation() {
        return resultOfOperation;
    }

    public void setResultOfOperation(Object resultOfOperation) {
        this.resultOfOperation = resultOfOperation;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }    
}
