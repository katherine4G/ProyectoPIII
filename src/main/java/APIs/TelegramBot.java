package APIs;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botToken = "7039364186:AAFN7yoSkdVeuyXJnC2GgPAHSwYQXsTiZoc";
    private final String botUsername = "@UniversityPlatform_bot";
    private Map<String, String> userChatIds = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String userName = message.getFrom().getUserName();
            if (userName == null || userName.isEmpty()) {
                userName = message.getFrom().getId().toString(); // Usa el ID del usuario si no hay nombre de usuario
            }
            System.out.println("Almacenando chatId: " + chatId + " para el usuario: " + userName);
            userChatIds.put(userName, chatId); // Almacena el chatId con el nombre de usuario o ID como clave

            String responseText = "Hola, " + message.getFrom().getFirstName() + "! Gracias por tu mensaje: " + message.getText();

            System.out.println("Mensaje recibido de " + message.getFrom().getFirstName() + ": " + message.getText());

            SendMessage response = new SendMessage();
            response.setChatId(chatId);
            response.setText(responseText);

            try {
                execute(response);
                System.out.println("Mensaje enviado a " + chatId);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se recibió un mensaje de texto.");
        }
    }

    public void sendMessageToChat(String userName, String text) {
        String chatId = userChatIds.get(userName);
        if (chatId != null) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(text);

            try {
                execute(message);
                System.out.println("Mensaje enviado a " + chatId);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontró el chatId para el usuario: " + userName);
            System.out.println("Usuarios almacenados: " + userChatIds.keySet());
        }
    }

    public void initializeBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
            System.out.println("Bot registrado exitosamente!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

//package APIs;
//
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//
//public class TelegramBot extends TelegramLongPollingBot {
//
//    private final String botToken = "7039364186:AAFN7yoSkdVeuyXJnC2GgPAHSwYQXsTiZoc";
//    private final String botUsername = "@UniversityPlatform_bot";
//    private final Map<String, String> userChatIds = new HashMap<>();
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            Message message = update.getMessage();
//            String chatId = message.getChatId().toString();
//            String userName = message.getFrom().getUserName();
//            
//            if (userName == null || userName.isEmpty()) {
//                userName = message.getFrom().getId().toString(); // Usa el ID del usuario si no hay nombre de usuario
//            }
//            System.out.println("Almacenando chatId: " + chatId + " para el usuario: " + userName);
//            userChatIds.put(userName, chatId); // Almacena el chatId con el nombre de usuario o ID como clave
//
//            String responseText = "Hola, " + message.getFrom().getFirstName() + "! Gracias por tu mensaje: " + message.getText();
//
//            System.out.println("Mensaje recibido de " + message.getFrom().getFirstName() + ": " + message.getText());
//
//            SendMessage response = new SendMessage();
//            response.setChatId(chatId);
//            response.setText(responseText);
//
//            try {
//                execute(response);
//                System.out.println("Mensaje enviado a " + chatId);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("No se recibió un mensaje de texto.");
//        }
//    }
//
//    public void sendMessageToChat(String userName, String text) {
//        String chatId = userChatIds.get(userName);
//        if (chatId != null) {
//            SendMessage message = new SendMessage();
//            message.setChatId(chatId);
//            message.setText(text);
//
//            try {
//                execute(message);
//                System.out.println("Mensaje enviado a " + chatId);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("No se encontró el chatId para el usuario: " + userName);
//            System.out.println("Usuarios almacenados: " + userChatIds.keySet());
//        }
//    }
//
//    public void initializeBot() {
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(this);
//            System.out.println("Bot registrado exitosamente!");
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String getBotUsername() {
//        return botUsername;
//    }
//
//    @Override
//    public String getBotToken() {
//        return botToken;
//    }
//}
