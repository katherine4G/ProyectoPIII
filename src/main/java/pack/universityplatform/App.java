package pack.universityplatform;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage; 

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;  
        scene = new Scene(loadFXML("main"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
         launch();
    }

}

//package pack.universityplatform;
//
//import APIs.TelegramBot;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//
//import java.io.IOException;
//
//public class App extends Application {
//
//    private static Scene scene;
//   // private static TelegramBot bot;
//
//    public static void main(String[] args) {
//        // Inicializa el bot de Telegram
////        bot = new TelegramBot();
////        bot.initializeBot();
//
//      
//        launch();
//    }
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("main"));
//        stage.setScene(scene);
//        stage.show();
////        VBox root = new VBox();
////        Button sendButton = new Button("Enviar mensaje de prueba");
////        sendButton.setOnAction(e -> enviarMensaje("Katherine0_4", " Este es un mensaje de prueba."));
////        root.getChildren().add(sendButton);
////
////        scene = new Scene(root, 300, 200);
////        stage.setScene(scene);
////        stage.show();
//    }
//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    public static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
//
////    public static void enviarMensaje(String userName, String mensaje) {
////        if (bot != null) {
////            bot.sendMessageToChat(userName, mensaje);
////        } else {
////            System.out.println("El bot no está inicializado.");
////        }
////    }
//}
