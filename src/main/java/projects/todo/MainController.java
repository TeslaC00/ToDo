package projects.todo;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    public TextField newTaskTF;
    public Button addTaskBtn;
    public ListView<CheckBox> completeTaskLV;
    public ListView<CheckBox> currentTaskLV;
    private final TextField colorTF = new TextField(); // initialized colorTF
    private final ColorPicker colorPicker = new ColorPicker(); // initialized colorPicker

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTaskBtn.setOnAction(event -> addNewTask());

        // Set initial color
        colorPicker.setValue(Color.WHITE);

        colorPicker.setOnAction(e -> {
            // Get Color Hex string and set it to text field
            Color colorPicked = colorPicker.getValue();
            String colorHex = "#" + colorPicked.toString().substring(2, 8);
            colorTF.setText(colorHex);
        });

        // Set action to change button color
        colorTF.setOnAction(event -> changeButtonColor());
    }

    private void addNewTask() {
        String task = newTaskTF.getText();
        if (task.isEmpty()) {
            showWarnAlert("Null Task Warning", "You have not entered any task.\nPlease enter a task in the box given.");
            return;
        }
        newTaskTF.clear();
        newTaskTF.setPromptText("New Task");
        CheckBox checkBox = new CheckBox(task);
        addListener(checkBox);
        currentTaskLV.getItems().add(checkBox);
    }

    private void addListener(CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                currentTaskLV.getItems().add(checkBox);
                completeTaskLV.getItems().remove(checkBox);
            } else if (!oldValue && newValue) {
                completeTaskLV.getItems().add(checkBox);
                currentTaskLV.getItems().remove(checkBox);
            }
        });
    }

    private void showWarnAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING, content);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public void start(Stage primaryStage) {
        VBox vb = new VBox(colorPicker, colorTF);
        Scene scene = new Scene(vb, 200, 150);

        primaryStage.setTitle("JavaFX Color Picker Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeButtonColor() {
        if (colorTF == null) {
            // handle the null text field case
            return;
        }

        String color = colorTF.getText();

        if (color == null || color.isEmpty()) {
            showWarnAlert("Empty Color Warning", "You have not entered any color.\nPlease enter a hex color.");
            return;
        }

        if (!color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")) {
            showWarnAlert("Invalid Color", "Please enter a valid hex color. (ex: #FFFFFF)");
            return;
        }

        Platform.runLater(() -> colorTF.setStyle("-fx-background-color: " + color + ";"));
    }
}