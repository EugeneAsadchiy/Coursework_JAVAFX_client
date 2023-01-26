package com.example.client;
import java.io.*;
import java.net.Socket;
public class Client {
    private Socket socket;
    public BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void sendMessageToServer(String messageToServer) throws IOException {
        bufferedWriter.write(messageToServer);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
//    public void ConnectServer()
//    try {
//        client = new Client(new Socket("localhost", 1234));
//        System.out.println("Connected to server!");
//
//    } catch (IOException e){
//        e.printStackTrace();
//    }
    public Client (Socket socket){

        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            System.out.println("Error creating client!");
            e.printStackTrace();
            //closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
}
