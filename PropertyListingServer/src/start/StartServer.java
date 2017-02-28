/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import gui.FrmStartServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ThreadProcessClientRequests;

/**
 *
 * @author Simon Okwori - University of Belgrade. Msc Software Engineering
 */
public class StartServer extends Thread {

    @Override
    public void run() {

        try {
            // TODO add your handling code here:

            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Server is UP - and waiting for clients to connect");

            while (true) {
                Socket conectionToClient = serverSocket.accept();

                ObjectInputStream inFromClient = new ObjectInputStream(conectionToClient.getInputStream());
                ObjectOutputStream outToClient = new ObjectOutputStream(conectionToClient.getOutputStream());

                ThreadProcessClientRequests threadForClient = new ThreadProcessClientRequests(inFromClient, outToClient);
                threadForClient.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmStartServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
