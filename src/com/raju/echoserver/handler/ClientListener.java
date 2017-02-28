/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raju.echoserver.handler;

import com.raju.echoserver.entity.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raju
 */
public class ClientListener extends Thread{
    
    private Socket client;
    private ClientHandler chandler;
    
    public ClientListener(Socket client,ClientHandler chandler){
        this.client=client;
        this.chandler=chandler;
        
    }
    
    private void broadcastMessage(String message){
        chandler.getClients().forEach(c->{
            if(c.getSocket()!=client){
                try {
                    PrintStream ps=new PrintStream(c.getSocket().getOutputStream());
                    Client cli=chandler.getClientBySocket(client);
                    if(cli!=null)
                    {
                       ps.println(cli.getUserName() + " says>" + message);
                       ps.flush();
                    }
                    
                } catch (IOException ex) {
                   
                }
            }
        });
    }

    @Override
    public void run() {
    
        try{
            String ip=client.getInetAddress().getHostAddress();
            System.out.println("Connection Request from "+ ip);
            
            PrintStream ps=new PrintStream(client.getOutputStream());
            Calendar calendar=Calendar.getInstance();
            ps.println("Current time at server is " + calendar.getTime());
            
            ps.println("Enter your Name:");
            BufferedReader input=new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String username=input.readLine();
            chandler.add(new Client(username, client));
            while(!isInterrupted())
            {  ps.print("Enter Your Message:");
                String line=input.readLine();
                
                if(line.equalsIgnoreCase("paint")){
                    Process p=new ProcessBuilder("mspaint").start();
                }
                System.out.println(username + "> " +line);
                broadcastMessage(line);
            }
            ps.flush();
        }catch(IOException ioe){
            
        }
    }
    
    
    
}
