import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {
    public final static String IP = "localhost";
    public final static int PORT = 8080;

    @SneakyThrows
    public void start() {
        Socket socket = new Socket(IP, PORT);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

        System.out.println("Enter your login: ");
        String login = consoleReader.readLine();
        System.out.println("Enter your password");
        String password = consoleReader.readLine();
        serverWriter.println("!@#REGISTRATION%^*!" + login + "::" + password);
        serverWriter.flush();
        String input;
        while ((input = consoleReader.readLine()) != null) {
            serverWriter.println(input);
            serverWriter.flush();
        }
    }
}
