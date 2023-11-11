module projects.todo {
    requires javafx.controls;
    requires javafx.fxml;


    opens projects.todo to javafx.fxml;
    exports projects.todo;
}