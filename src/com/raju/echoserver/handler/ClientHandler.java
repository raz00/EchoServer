/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raju.echoserver.handler;

import com.raju.echoserver.entity.Client;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raju
 */
public class ClientHandler {
    private List<Client> clientList;
    
    public ClientHandler(){
        clientList=new ArrayList<>();
    } 
    
    public void add(Client c){
        clientList.add(c);
    }
    
    public Client getClientBySocket(Socket socket){
        for(Client s : clientList){
            if(s.getSocket()==socket){
                return s;
            }
        }
        return null;
    }
    
    public List<Client> getClients(){
        return clientList;
    }
    
    
    
    
}
