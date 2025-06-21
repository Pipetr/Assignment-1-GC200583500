package com.example.assignment1gc200583500;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.*;


public class PlayerChartController {

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
        oreboundsCol.setCellValueFactory(new PropertyValueFactory<>("oRebounds"));
        dreboundsCol.setCellValueFactory(new PropertyValueFactory<>("dRebounds"));
        turnoversCol.setCellValueFactory(new PropertyValueFactory<>("turnovers"));
        foulsCol.setCellValueFactory(new PropertyValueFactory<>("fouls"));
        minutesCol.setCellValueFactory(new PropertyValueFactory<>("minutes"));

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
        }catch (SQLException e) {
            e.printStackTrace();
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
