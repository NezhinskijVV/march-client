import domain.Client;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@AllArgsConstructor
public class MessageReceiverRunnable implements Runnable {
    private final Socket socket;
    private final Client user;

    @SneakyThrows
    @Override
    public void run() {
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String serverMessage;

        while ((serverMessage = socketReader.readLine()) != null) {
            if (serverMessage.startsWith(user.getLogin())) {
                serverMessage = "I:" + serverMessage.split(":")[1];
            }
            System.out.println(serverMessage);
        }
    }
}

//0. JUnit
//1. Logger (@Log4j)
//2. Сохранение переписки в файл
//3. Подгрузка переписки
//4. Интернационализация приложения (java.util.Locale)
//5. Личные сообщения
//6. Json Gson
//7. Swing Java FX прикрутить интерфейс
//8. Security