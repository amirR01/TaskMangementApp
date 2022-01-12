package view.BoardMenu;

import controller.Controller;
import controller.LoggedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Board;
import model.Category;
import model.Task;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class BoardMenuSecondPageForLeaderView {

    public AnchorPane pane;
    public Label response;
    public HBox hBox;
    public Label condition;
    public TextField categoryName;
    private User user;
    private Board board;
    private TableView<Task> tableView;

    public void initialize() {
        user = LoggedController.getInstance().getLoggedInUser();
        board = LoggedController.getInstance().getSelectedBoard();
        updateHBOX();
        makeDoneColumn();
    }

    public void updateHBOX() {
        if(board.isCreated())condition.setText("board construction is done");
        hBox.getChildren().clear();
        ArrayList<Category> categories  = board.getAllCategories();
        Node[] nodes = new Node[categories.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CategoryItem.fxml"));
                nodes[i] = loader.load();
                hBox.getChildren().add(nodes[i]);
                CategoryItemView categoryItemView = loader.getController();
                categoryItemView.setCategory(categories.get(i),i);
                categoryItemView.updateCategory();
                categoryItemView.setParentController(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void makeDoneColumn() {
        if(tableView!=null)pane.getChildren().remove(tableView);
        ObservableList<Task> list = FXCollections.observableArrayList(board.getDone());
        tableView = new TableView<>();
        tableView.setLayoutX(286);
        tableView.setLayoutY(52);
        TableColumn<Task, String> taskName = new TableColumn<>("done");
        taskName.setPrefWidth(76);
        tableView.setPrefWidth(76);
        tableView.setPrefHeight(250);
        taskName.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableView.getColumns().addAll(taskName);
        tableView.setItems(list);
        tableView.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/Table.css")).toExternalForm());
        pane.getChildren().add(tableView);
    }

    public void removeBoard(javafx.event.ActionEvent actionEvent) throws IOException {
        boolean confirmation = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setContentText("DO YOU WANT TO CONTINUE ? (there would be no way to bring removed board back");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) confirmation = true;
        if (confirmation) {
            int controllerResponse = Controller.controller.removeBoard(user, board.getTeam(), board.getBoardName());
            response.setText("board removed successfully");
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                    ("/fxml/BoardMenuFirstPage.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    public void addCategory(ActionEvent actionEvent) {
        String categoryNameText = categoryName.getText();
        int response = Controller.controller.addCategory(user,board.getTeam(),board.getBoardName(),categoryNameText);
        if(response==2) this.response.setText("already exist a category with this name");
        if(response==3) this.response.setText("category successfully added");
        Controller.controller.boardDone(user,board.getTeam(),board.getBoardName());
        updateHBOX();
    }
    public void addTask(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BoardMenuAddTaskPage.fxml"));
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(root));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BoardMenuFirstPage.fxml"));
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(root));
    }



}
