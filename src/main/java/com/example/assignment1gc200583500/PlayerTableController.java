package com.example.assignment1gc200583500;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;


public class PlayerTableController {

    @FXML
    private TableColumn<Player, Integer> assistsCol;

    @FXML
    private TableColumn<Player, Integer> blocksCol;

    @FXML
    private Button btnChart;

    @FXML
    private TableColumn<Player, String> countryCol;

    @FXML
    private TableColumn<Player, Integer> dreboundsCol;

    @FXML
    private TableColumn<Player, Integer> foulsCol;

    @FXML
    private TableColumn<Player, Integer> minutesCol;

    @FXML
    private TableColumn<Player, Integer> oreboundsCol;

    @FXML
    private TableColumn<Player, String> payerNameCol;

    @FXML
    private TableColumn<Player, Integer> pointsCol;

    @FXML
    private TableColumn<Player, String> positionCol;

    @FXML
    private TableColumn<Player, Integer> reboundsCol;

    @FXML
    private TableColumn<Player, Integer> stealsCol;

    @FXML
    private TableView<Player> tableView;

    @FXML
    private TableColumn<Player, String> teamCol;

    @FXML
    private TableColumn<Player, Integer> turnoversCol;

    @FXML
    void chartView(ActionEvent event) {
        try{
            // Get the current stage from the button
            Stage stage = (Stage) btnChart.getScene().getWindow();
            // Load the chart view FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("players-chart.fxml"));
            Parent root = loader.load();
            // Set the scene with the loaded chart view
            Scene scene = new Scene(root, 1080, 700);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        // Initialize the table columns with appropriate properties
        payerNameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        teamCol.setCellValueFactory(new PropertyValueFactory<>("team"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        reboundsCol.setCellValueFactory(new PropertyValueFactory<>("rebounds"));
        assistsCol.setCellValueFactory(new PropertyValueFactory<>("assists"));
        stealsCol.setCellValueFactory(new PropertyValueFactory<>("steals"));
        blocksCol.setCellValueFactory(new PropertyValueFactory<>("blocks"));
        oreboundsCol.setCellValueFactory(new PropertyValueFactory<>("offRebounds"));
        dreboundsCol.setCellValueFactory(new PropertyValueFactory<>("defRebounds"));
        turnoversCol.setCellValueFactory(new PropertyValueFactory<>("turnovers"));
        foulsCol.setCellValueFactory(new PropertyValueFactory<>("fouls"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<>("minutes"));

        btnChart.setOnAction((event) -> {;
            try {
                chartView(event);
            } catch (Exception e) {
                System.out.println("Error showing chart view: " + e.getMessage());
            }
        });

        try {
            Connection con = connector();
            if (con != null) {
                // Create a statement to execute the query
                Statement stmt = con.createStatement();
                // Execute the query to get player data
                ResultSet rs = stmt.executeQuery("SELECT * FROM player_stats");

                // Clear existing items in the table view
                tableView.getItems().clear();

                // Loop through the result set and add each player to the table view
                while (rs.next()) {
                    Player player = new Player(
                            rs.getString("playerName"),
                            rs.getString("playerteam"),
                            rs.getString("playerposition"),
                            rs.getString("playercountry"),
                            rs.getInt("points"),
                            rs.getInt("rebs"),
                            rs.getInt("assists"),
                            rs.getInt("steals"),
                            rs.getInt("blocks"),
                            rs.getInt("offRebs"),
                            rs.getInt("defRebs"),
                            rs.getInt("turnovers"),
                            rs.getInt("fouls"),
                            rs.getInt("mins")
                    );
                    tableView.getItems().add(player);
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // method to return the connection object
    private Connection connector(){
        try {
            return DriverManager.getConnection("jdbc:mysql://172.31.22.43:3306/Felipe200583500",
                    "Felipe200583500", "scYWjao9tY");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
