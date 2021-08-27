import java.io.*;
import java.net.*;

public class tax_server {

    static int value ;
    static boolean value_flag = true;
    static int weight;
    static boolean weight_flag = true;
    static int tax;

    public static void main(String[] args) {
        ServerSocket ss;
        Socket s ;
        final int port_num = 4545;




        try {
            ss = new ServerSocket(port_num);
            System.out.println("server online");
            s = ss.accept();
            System.out.println("client conected");
            InputStreamReader RD = new InputStreamReader(s.getInputStream());
            BufferedReader BFRD = new BufferedReader(RD);
            while(true) {

                String client_info = BFRD.readLine();
                System.out.println(client_info);
                if (value_flag){
                    value = Integer.parseInt(client_info);
                    value_flag = false;
                }else if(weight_flag){
                    weight = Integer.parseInt(client_info);
                    weight_flag = false;
                }else {
                    tax = Integer.parseInt(client_info);
                    System.out.println(calculate_tax(value,weight,tax));
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static int calculate_tax ( int value, int weight,int tax) {
        int price_tax = (value * tax)/ 100;
        double weight_tax = (weight * 0.15);
        return (int) Math.round(price_tax + weight_tax);
    }
}
