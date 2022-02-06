package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Team;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowTeamsForLeaderView implements Initializable {
    public AnchorPane pane;
    public ImageView exit;
    public VBox vTeamItem;
    public Button createTeam;
    public Button leave;

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }


    public void goCreateTeam(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SpecialCommandsForLeader.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstInitialize();
    }

    private void firstInitialize() {
        vTeamItem.getChildren().clear();
        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : Team.getAllTeams()) {
            if (team.getTeamLeader().getUserName().equals(Controller.controller.getLoggedInUser().getUserName()))
                teams.add(team);
        }
        Node[] nodes = new Node[teams.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamItem.fxml"));
                TeamItemView controller = new TeamItemView();
                loader.setController(controller);
                nodes[i] = loader.load();
                vTeamItem.getChildren().add(nodes[i]);
                controller.setTeam(teams.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void leave(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LeaderMenu.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }
}