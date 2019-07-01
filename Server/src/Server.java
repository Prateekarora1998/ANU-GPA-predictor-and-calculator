import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.*;


/**@author Kalai (u6555407)*/
public class Server {
    public static void main(String[] args) throws Exception {

        System.out.println("Server has initiated");
        ServerSocket listeningSocket = new ServerSocket(5005);

      /* making sure server runs until developer stops it.*/
        while (true) {

            System.out.println("Server is waiting for client request");
            Socket connectedSocket = listeningSocket.accept();
            System.out.println("Server has connected with a client");

            /* Reading stream of characters from the client*/
            InputStreamReader streamReader = new InputStreamReader(connectedSocket.getInputStream());
            BufferedReader br = new BufferedReader(streamReader);

            /*Initialisations*/
            String time = LocalTime.now().toString().substring(0, 5);
            String connectedSocketData = "Feedback received on " + LocalDate.now() + " at " + time + "\n";
            String temp = "";
            try {
                connectedSocketData+= "Ratings is " + br.readLine() + "\n" + "Feedback is " + "\n";
                while (true) {
                    temp = br.readLine();
                    if (temp != null) {
                        connectedSocketData += temp + " ";
                    } else {

                        break;
                    }
                }
            } catch (IOException i) {
            }


            /*When done reading feedback add a newline to differentiate different feedbacks*/
            connectedSocketData += "\n ======================================================= \n";
            System.out.println(connectedSocketData);
            System.out.println("Server has received : " + connectedSocketData);
            Writer fileWriter = new FileWriter("Feedbacks.txt", true);
            try {
                fileWriter.write(connectedSocketData);
            } catch (IOException e) {

            }
            fileWriter.close();

        }
    }
}
