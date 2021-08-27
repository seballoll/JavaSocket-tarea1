
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

static CharSequence price_g;
static CharSequence weight_g;
static CharSequence tax_g;


    @Override
    public void start(Stage stage) {
        Label price_label = new Label("inserte el precio");
        TextField price = new TextField("precio");
        Label weight_label = new Label("inserte el peso");
        TextField weight = new TextField("peso");
        Label tax_label = new Label("inserte el % de impuesto");
        TextField tax = new TextField("% impuesto");

        Button send_button = new Button("enviar");
        send_button.setOnAction(actionEvent -> {
            try {
                send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        GridPane layout = new GridPane();


        layout.add(send_button,2,4);
        layout.add(price_label,1,0);
        layout.add(price,1,2);
        layout.add(weight_label,2,0);
        layout.add(weight,2,2);
        layout.add(tax_label,3,0);
        layout.add(tax,3,2);

        Scene screen = new Scene(layout, 500, 250);

        stage.setScene(screen);
        stage.show();
        price_g =  price.getCharacters();
        weight_g = weight.getCharacters();
        tax_g   = tax.getCharacters();


    }

    public static void main(String[] args)  {
        launch();

    }
    public static void send() throws IOException  {


        client.conect_socket(price_g.toString(),weight_g,tax_g);

        System.out.println("button pressed");
    }

}
