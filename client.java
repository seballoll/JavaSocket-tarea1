import java.io.*;
import java.net.*;

public class client extends User_Interface {

    /**
     * variable global para usar en todos los metodos
     */
    static Socket s;

    /**
     * metodo para iniciar la interfaz grafica desde el cliente
     */
    public static void main(String[] args) {

        User_Interface.launch();

    }

    /**
     * metodo para conectar el socket al servidor, ademas recibe los valores que introduce el usuario
     * @param value es el precio del objeto
     * @param weight es el peso del objeto
     * @param tax el porcentaje de impuesto actual
     * @throws IOException
     */
    public static void conect_socket( String value, String weight,String tax) throws IOException {
        s = new Socket("localhost", 4545);
        //revision de valores escritos por el cliente en la consola
        System.out.println(value);
        System.out.println(weight);
        System.out.println(tax);
        //llama al metodo "Write" con los valores del usuario
        Write(value,weight,tax);
        }

    /**
     * metodo para enviar los datos en orden hasta el servidor
     * @param value precio
     * @param weight peso
     * @param tax porcentaje de impuesto
     * @throws IOException
     */
    public static void Write(String value, String weight,String tax) throws IOException {
        //crea un writer que envia los valores como string a traves del canal del socket
        PrintWriter WRT = new PrintWriter(s.getOutputStream());
        //loop para enviar los tres datos en orden una unica vez
        for (int i = 0;i <= 2; i++) {
            if (i==0){
                WRT.println(value);//crea el mensaje a enviar
                WRT.flush();//envia el mensaje por el canal
            }else if (i == 1){
                WRT.println(weight);
                WRT.flush();
            } else {
                WRT.println(tax);
                WRT.flush();
                //llama al metodo "Read"
                Read();
            }

    }
    }

    /**
     * metodo para leer el mensaje del servido que trae el impuesto calculado
     * @throws IOException
     */
    public static void Read() throws IOException {
        //se crea un reader conectado al canal del socket
        InputStreamReader RD = new InputStreamReader(s.getInputStream());
        BufferedReader BFRD = new BufferedReader(RD);
        //espera hasta recibir informacion del servidorluego la guarda en una variable
        String tax_calc = BFRD.readLine();
        //llama a un metodo de la interfaz para cambiar el valor de una variable
        User_Interface.returned_tax(tax_calc);
    }
}
