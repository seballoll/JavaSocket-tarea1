import java.io.*;
import java.net.*;

public class tax_server {
//flags para leer los valores en orden y variables globales para las operaciones
    static int value ;
    static boolean value_flag = true;
    static int weight;
    static boolean weight_flag = true;
    static int tax;
    static int tax_calculated;
    static boolean Ready_tax = false;
//variable global para ambos socket, permite usarlos en todos los metodos
    static ServerSocket ss;
    static Socket s ;


    public static void main(String[] args) throws IOException {
        //declaraciones para la coneccion del servidor y el cliente
        final int port_num = 4545;
        ss = new ServerSocket(port_num);
        System.out.println("server online");//notifica al activar el socket del servidor
        s = ss.accept();
        System.out.println("client conected");//notifica cuando un cliente se conecta

        //crea un lector de texto utilizando los canales del socket
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);


        try {
            //loop para obtener todos los datos del cliente
            while(true) {

                String client_info = BFRD.readLine();//el buffered reader lee el texto y lo guarda en la variable
                System.out.println(client_info);
                if (value_flag){
                    value = Integer.parseInt(client_info);//el precio queda en la variable "value"
                    value_flag = false;//se desactiva la flag para que avance al siguiente valor
                }else if(weight_flag){
                    weight = Integer.parseInt(client_info);//se guarda el peso en la variable "weight"
                    weight_flag = false;//se desactiva la flag para el ultimo dato
                }else {
                    tax = Integer.parseInt(client_info);//se guarda el porcentaje de impuesto en "tax"
                    tax_calculated = calculate_tax(value,weight,tax);//llama al metodo para calcular impuestos y lo guarda en una variable
                    Ready_tax = true;//flag para enviar el mensaje al cliente con el calculo hecho
                }
                if (Ready_tax){
                    send_tax(Integer.toString(tax_calculated));//llama al metodo para enviar el mensaje al cliente
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //metodo para calcular los impuestos del cliente segun el precio, peso y porcentaje
    public static int calculate_tax ( int value, int weight,int tax) {
        int price_tax = (value * tax)/ 100;
        double weight_tax = (weight * 0.15);
        return (int) Math.round(price_tax + weight_tax);//suma de todas las operaciones y se redondea
    }//se redondea para evitar posibles faltas de pago

    //metodo para devolver al cliente el impuesto calculado
    public static void send_tax(String cal_tax) throws IOException {
        System.out.println(cal_tax);
        PrintWriter PWRT = new PrintWriter(s.getOutputStream());//se crea un writer en el canal del socket
        PWRT.println(tax_calculated);//mensaje a enviar
        PWRT.flush();//envia el mensaje por el canal
    }
}
