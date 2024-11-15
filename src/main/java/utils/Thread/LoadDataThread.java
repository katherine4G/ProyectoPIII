package utils.Thread;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import utils.MessagesToUser;

/**
 *
 * @param <T>
 */
public class LoadDataThread<T> extends Thread {
    private TableView<T> tableView;
    private BaseAPI<T> api;
    private MessagesToUser message;
    private int page;
    private int pageSize;

    public LoadDataThread(TableView<T> tableView, BaseAPI<T> api, MessagesToUser message, int page, int pageSize) {
        this.tableView = tableView;
        this.api = api;
        this.message = message;
        this.page = page;
        this.pageSize = pageSize;
    }
    
    public LoadDataThread(TableView<T> tableView, BaseAPI<T> api, MessagesToUser message) {
        this.tableView = tableView;
        this.api = api;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            List<T> items;
            if (page > 0 && pageSize > 0) {
                items = api.getLoadData(page, pageSize);  // Carga con paginación si están definidos.
            } else {
                items = api.getLoadData();  // Carga completa sin paginación.
            }
            Platform.runLater(() -> {
                ObservableList<T> itemList = FXCollections.observableArrayList(items);
                tableView.setItems(itemList);
            });
        } catch (IOException e) {
            Platform.runLater(() -> message.showErrorMessage("Error", "No se pudieron cargar los datos."));
        }
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

//public class LoadDataThread<T> extends Thread {
//    private TableView<T> tableView;
//    private BaseAPI<T> api;
//    private MessagesToUser message;
//    private int page;
//    private int pageSize;
//
//    public LoadDataThread(TableView<T> tableView, BaseAPI<T> api, MessagesToUser message, int page, int pageSize) {
//        this.tableView = tableView;
//        this.api = api;
//        this.message = message;
//        this.page = page;
//        this.pageSize = pageSize;
//    }
//    
//    public LoadDataThread(TableView<T> tableView, BaseAPI<T> api, MessagesToUser message) {
//        this.tableView = tableView;
//        this.api = api;
//        this.message = message;
//    }
//
//
//    @Override
//    public void run() {
//        try {
//            List<T> items;
//            if (page > 0 && pageSize > 0) {
//                items = api.getLoadData(page, pageSize);  // Carga con paginación si están definidos.
//            } else {
//                items = api.getLoadData();  // Carga completa sin paginación.
//            }
//            Platform.runLater(() -> {
//                ObservableList<T> itemList = FXCollections.observableArrayList(items);
//                tableView.setItems(itemList);
//            });
//        } catch (IOException e) {
//            Platform.runLater(() -> message.showErrorMessage("Error", "No se pudieron cargar los datos."));
//        }
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//}

//

//
///**
// *
// * @author kathe
// * @param <T>
// */
//public class LoadDataThread<T> extends Thread {
//    private TableView<T> tableView; 
//    private BaseAPI<T> api;
//    private MessagesToUser message;
//
//    public LoadDataThread(TableView<T> tableView, BaseAPI<T> api, MessagesToUser message) {
//        this.tableView = tableView;
//        this.api = api;
//        this.message = message;
//    }
//
//
//    @Override
//    public void run() {
//        try {
//            List<T> items = api.getLoadData();  
//              Platform.runLater(() -> {
//                ObservableList<T> itemList = FXCollections.observableArrayList(items);
//                tableView.setItems(itemList); 
//            });
//        } catch (IOException e) {
//            Platform.runLater(() -> message.showErrorMessage("Error", "No se pudieron cargar los datos."));
//        }
//    }
//}
