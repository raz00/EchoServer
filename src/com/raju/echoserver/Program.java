/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raju.echoserver;

import com.raju.echoserver.handler.ClientHandler;
import com.raju.echoserver.handler.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Raju
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port=3000;
        try{
            System.out.println("Server is running at "+ port);
            ServerSocket server=new ServerSocket(port);
            ClientHandler cHandler=new ClientHandler();
            while(true){
                Socket client=server.accept();
                ClientListener clientListener=new ClientListener(client,cHandler);
                clientListener.start();
            }
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    
}
/**
 * cmd telnet localhost port no.
 * ipaddress portno.
   */