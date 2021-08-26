import java.io.IOException;
import java.net.*;

public class client extends User_Interface {

    public static void main(String[] args) {


        User_Interface.launch();

    }

    public static void conect_socket() throws IOException {
        Socket s = new Socket("localhost", 4545);
    }

}
