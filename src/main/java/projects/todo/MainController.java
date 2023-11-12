package projects.todo;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TextField newTaskTF;
    public Button addTaskBtn;
    public ListView<CheckBox> completeTaskLV;
    public ListView<CheckBox> currentTaskLV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTaskBtn.setOnAction(event -> addNewTask());
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
            if (oldValue) {
                currentTaskLV.getItems().add(checkBox);
                completeTaskLV.getItems().remove(checkBox);
            } else if (newValue) {
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




}
    // needs to be implemented
    //
    /*public void changeButtonColor() {
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

        Platform.runLater(() -> colorButton.setStyle("-fx-background-color: " + color + ";"));
    }


     */
