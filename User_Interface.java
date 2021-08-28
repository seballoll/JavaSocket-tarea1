
import javafx.application.Application;
        import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class User_Interface extends Application {

    /**
     * variables globales para obtener y cambiar los valores en la interfaz
     */
    static CharSequence price_g;
static CharSequence weight_g;
static CharSequence tax_g;
static CharSequence tax_return = "" ;

    /**
     * aqui se declaran todos los objetos que van en pantalla
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        //declaracion de los objetos en pantalla
        Label price_label = new Label("inserte el precio");
        TextField price = new TextField("colones");
        Label weight_label = new Label("inserte el peso");
        TextField weight = new TextField("kilos");
        Label tax_label = new Label("inserte el % de impuesto");
        TextField tax = new TextField("%impuesto");
        Label tax_return_L = new Label("");

        //creacion de botones con sus respectivas acciones
        Button send_button = new Button("enviar");
        send_button.setOnAction(actionEvent -> {
            try {
                //al presionar el boton llama al metodo send
                send();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Button refresh_button = new Button("actualizar");
        refresh_button.setOnAction(actionEvent ->{
            //al presionar el boton cambia el valor de el impuesto a string
            String tax_r_s = tax_return.toString();
            //cambia el valor del label por el nuevo valor
            tax_return_L.setText(tax_r_s);
        } );
        //forma en la que se agregan objetos a la pantalla
        GridPane layout = new GridPane();
        /**
         * agregando objetos a la pantalla con sus respectivas coordenadas en una cuadricula
         */
        layout.add(send_button,2,3);
        layout.add(refresh_button,3,3);
        layout.add(tax_return_L,2,4);
        layout.add(price_label,1,0);
        layout.add(price,1,2);
        layout.add(weight_label,2,0);
        layout.add(weight,2,2);
        layout.add(tax_label,3,0);
        layout.add(tax,3,2);
        //crea la pantalla de la interfaz con la cuadricula
        Scene screen = new Scene(layout, 500, 250);
        stage.setScene(screen);
        stage.show();
        /**
         * obtiene los valores de las cajas de texto al llamar la variable
         */
        price_g =  price.getCharacters();
        weight_g = weight.getCharacters();
        tax_g   = tax.getCharacters();

    }

    public static void main(String[] args) {
        launch();

    }

    /**
     * metodo para enviar los datos de las cajas de texto a la clase cliente
     * @throws IOException
     * @throws InterruptedException
     */
    public static void send() throws IOException, InterruptedException {

        System.out.println("datos enviados al servidor");
        /**
         * llama al metodo conect_socket con la informacion que introdujo el usuario
         */
        client.conect_socket(price_g.toString(),weight_g.toString(),tax_g.toString());

    }

    /**
     * metodo para cambiar el valor de un label invisible con el valor del impuesto
     * @param tax impuesto ya calculado por el servidor
     */
    public static void returned_tax(String tax){

        System.out.println(tax);
        //cambio de valor de la variable global
        tax_return = "el impuesto es de " +tax + " colones";
        //revision del impuesto en la consola
        System.out.println("server : " + tax_return);


    }
}
