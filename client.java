import java.io.*;
import java.net.*;

public class client extends User_Interface {

    public static void main(String[] args) {

        User_Interface.launch();

    }

    public static void conect_socket( String value, String weight,String tax) throws IOException {
        Socket s = new Socket("localhost", 4545);
        DataInputStream din;
        DataOutputStream dout;
        System.out.println(value);

        //+"/" + weight + "/" + tax
        PrintWriter WRT = new PrintWriter(s.getOutputStream());
        for (int i = 0;i <= 2; i++) {
            if (i==0){
                WRT.println(value);
                WRT.flush();
            }else if (i == 1){
                WRT.println(weight);
                WRT.flush();

            } else {
                WRT.println(tax);
                WRT.flush();
            }

        }





    }
    public static int calculate_tax (Integer [] args, int value, int weight,int tax) {
        int price_tax = (value * tax)/ 100;
        double weight_tax = (weight * 0.15);
        return (int) Math.round(price_tax + weight_tax);
    }
}
