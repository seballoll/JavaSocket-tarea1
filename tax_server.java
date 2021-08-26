import java.io.IOException;
import java.net.*;

public class tax_server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4545);
        Socket s = ss.accept();
        System.out.println("client conected");



    }
    public static void start_server () throws IOException {

    }
}
