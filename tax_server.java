import java.io.*;
import java.net.*;

public class tax_server {

    public static void main(String[] args) {
        ServerSocket ss;
        Socket s ;
        final int port_num = 4545;


        try {
            ss = new ServerSocket(port_num);
            System.out.println("server online");
            while(true) {
                s = ss.accept();
                System.out.println("client conected");
                InputStreamReader RD = new InputStreamReader(s.getInputStream());
                BufferedReader BFRD = new BufferedReader(RD);
                String client_info = BFRD.readLine();
                System.out.println(client_info);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static int calculate_tax (Integer [] args, int value, int weight,int tax) {
        int price_tax = (value * tax)/ 100;
        double weight_tax = (weight * 0.15);
        return (int) Math.round(price_tax + weight_tax);
    }
}
