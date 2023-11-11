package projects.todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ToDo!");
        stage.setScene(scene);
        stage.show();
    }
}