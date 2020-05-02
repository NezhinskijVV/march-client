import domain.Client;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {
    public final static String IP = "localhost";
    public final static int PORT = 8080;
    public final Client user = new Client();

    @SneakyThrows
    public void start() {
        Socket socket = new Socket(IP, PORT);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

        registration(consoleReader, serverWriter);
        new Thread(new MessageReceiverRunnable(socket, user)).start();
        sendMessage(consoleReader, serverWriter);
    }

    @SneakyThrows
    private void sendMessage(BufferedReader consoleReader, PrintWriter serverWriter) {
        String input;
        while ((input = consoleReader.readLine()) != null) {
            print(serverWriter, user.getLogin() + ":" + input);
        }
    }

    @SneakyThrows
    private void registration(BufferedReader consoleReader, PrintWriter serverWriter){
        System.out.println("Enter your login: ");
        String login = consoleReader.readLine();
        System.out.println("Enter your password");
        String password = consoleReader.readLine();

        user.setLogin(login);
        user.setPassword(password);

        print(serverWriter, "!@#REGISTRATION%^*!" + login + "::" + password);
    }

    private void print(PrintWriter printWriter, String message) {
        printWriter.println(message);
        printWriter.flush();
    }
}
