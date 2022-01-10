package view;

import controller.Controller;
import controller.JsonController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Task;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class TaskPageForLeaderView implements Initializable {
    private final Task selectTask = Task.getSelectTask();
    public AnchorPane pane;
    public TextField deadlineFiled;
    public TextField descriptionField;
    public TextField PriorityField;
    public TextField taskTitleField;
    public Button btnTaskList;
    public Button btnEdit;
    public Label lblError;
    public Label lblDateOfCreation;
    public ListView membersList;
    public Button btnCreateTask;
    public ImageView exit;
    public Button DeleteMember;
    private int result;

    public void goToTaskList(ActionEvent actionEvent) throws IOException {
        Task.setSelectTask(null);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TaskListForLeader.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleField.setText(selectTask.getTitle());
        deadlineFiled.setText(selectTask.getDeadline().toString());
        PriorityField.setText(selectTask.getPriority());
        lblDateOfCreation.setText(selectTask.getDateOfCreation().toString());
        if (selectTask.getDescription().equals(""))
            descriptionField.setText("description is null!");
        else
            descriptionField.setText(selectTask.getDescription());
        for (User user : selectTask.getAssignedUser()) {
            membersList.getItems().add(user.getUserName());
        }

    }

    public void Edit(ActionEvent actionEvent) throws ParseException {
        if (!taskTitleField.getText().equals(selectTask.getTitle())) {
            Controller.controller.editTaskTitle(LoginView.LoginUser, selectTask, taskTitleField.getText());
        }
        if (!PriorityField.getText().equals(selectTask.getPriority()))
            Controller.controller.editTaskPriority(LoginView.LoginUser, selectTask, taskTitleField.getText());
        if (!descriptionField.getText().equals(selectTask.getDescription()))
            Controller.controller.editTaskDescription(LoginView.LoginUser, selectTask, taskTitleField.getText());
        if (!deadlineFiled.getText().equals(selectTask.getDeadline()))
            result = Controller.controller.editTaskDeadline(LoginView.LoginUser, selectTask, taskTitleField.getText());
        if (result == 1) {
            lblError.setText("New deadline is invalid!");
        } else
            lblError.setText("Task updated successfully!");
    }

    public void goToCreateTask(ActionEvent actionEvent) throws IOException {
        Task.setSelectTask(null);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateTaskPageForLeader.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }

    public void exit(MouseEvent mouseEvent) {
        JsonController.getInstance().updateJson();
        System.exit(0);
    }

    public void deleteMember(ActionEvent actionEvent) {
        String selectedItem = membersList.getSelectionModel().getSelectedItem().toString();
        result = Controller.controller.removeAssignedUsers(User.getUserByUsername("Amir"), selectTask, User.getUserByUsername(selectedItem));
        membersList = null;
        for (User user : selectTask.getAssignedUser()) {
            membersList.getItems().add(user.getUserName());
        }
    }
}