/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threads;

import constants.Operations;
import db.DatabaseBroker;
import domain.GeneralDomainObject;
import gui.FrmStartServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class ThreadProcessClientRequests extends Thread {

    ObjectInputStream inFromClient ;
    ObjectOutputStream outToClient ;
    DatabaseBroker dbbr = new DatabaseBroker();

    public ThreadProcessClientRequests(ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
    }

    @Override
    public void run() {
        
        try{       
            while (true) {
                ClientRequest request = (ClientRequest) inFromClient.readObject();
                ServerResponse response = new ServerResponse();
                switch (request.getOpertion()) {

                    case Operations.INSERT_DOMAIN_OBJECT: 
                        try {
                            dbbr.insertDomainObject((GeneralDomainObject) request.getObjectOfOperation());
                        } catch (Exception ex) {
                            Logger.getLogger(FrmStartServer.class.getName()).log(Level.SEVERE, null, ex);
                            response.setEx(ex);
                        }
                        break;
                        
                        case Operations.LIST_DOMAIN_OBJECT: 
                        try{
                            response.setResultOfOperation(dbbr.ListDomainObject((GeneralDomainObject)request.getObjectOfOperation(),(String)request.getAnotherObject()));
                        } catch(Exception ex){
                            response.setEx(ex);
                        }
                        break;                    
                    }                
                     outToClient.writeObject(response);
                }                
        } catch (IOException ex) {
            Logger.getLogger(FrmStartServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmStartServer.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }   
}
