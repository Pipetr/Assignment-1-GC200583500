package com.example.assignment1gc200583500;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class PlayerChartController {

    @FXML
    private Button btnTableview;

    @FXML
    private ComboBox<String> cbTeams;

    @FXML
    private BarChart<String, Number> chartStats;

    @FXML
    private RadioButton rbCanada;

    @FXML
    private RadioButton rbRestWorld;

    @FXML
    private RadioButton rbUSA;

    @FXML
    void applyFilter(ActionEvent event) throws SQLException {
        Connection con = connector();
        if (con != null) {
            Statement st = con.createStatement();
            String query = "SELECT * FROM player_stats";
            // get the country
            StringBuilder whereClause = new StringBuilder(" WHERE 1=1");
            if (rbCanada.isSelected()) {
                whereClause.append(" AND playercountry = 'Canada'");
            }
            if (rbUSA.isSelected()) {
                whereClause.append(" AND playercountry = 'United States'");
            }
            if (rbRestWorld.isSelected()) {
                whereClause.append(" AND playercountry NOT IN ('Canada', 'United States')");
            }
            if (cbTeams.getValue() != null && !cbTeams.getValue().equals("All")) {
                whereClause.append(" AND playerteam = '").append(cbTeams.getValue()).append("'");
            }
            query += whereClause.toString();
            ResultSet resultSet = st.executeQuery(query);
            // Clear existing data in the chart
            chartStats.getData().clear();
            while (resultSet.next()) {
                String playerName = resultSet.getString("playerName");
                int points = resultSet.getInt("points");
                String team = resultSet.getString("playerteam");

                // Create a Player object and add it to the chart
                chartBuilder(playerName, points, team);
            }
        }

    }

    private void chartBuilder(String playerName, int points, String team) {
        Player player = new Player(playerName, team, points);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(playerName);
        series.getData().add(new XYChart.Data<>(playerName, points));
        chartStats.getData().add(series);
    }

    @FXML
    void showTableView(ActionEvent event) {
        // Logic to switch to the Player Table View
        // This could involve changing the scene or updating the current view
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player-table.fxml"));
            // Defined the table view as child of the current stage
            Stage currentStage = (Stage) btnTableview.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1080, 700);
            stage.setTitle("Player Statistics Table");
            stage.setScene(scene);
            if (currentStage != null) {
                currentStage.close();
            }
            stage.show();
        }catch(IOException e){
            System.out.println("Error loading TableView: " + e.getMessage());
        }
    }

    @FXML
    void initialize() {
        // Initialize the chart and other UI components if necessary
        // For example, you might want to set up the chart axes, load data, etc.
        // The char will show players' points
        btnTableview.setOnAction((event) -> {
            try{
                showTableView(event);
            } catch (Exception e) {
                System.out.println("Error showing table view: " + e.getMessage());
            }
        });


        try {
            Connection conector = connector();
            // Initialize the ComboBox with "All" option
            cbTeams.getItems().add("All");

            if (conector != null) {
                Statement statement = conector.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM player_stats");
                // Clear existing data in the chart
                chartStats.getData().clear();

                while (resultSet.next()) {
                    String playerName = resultSet.getString("playerName");
                    int points = resultSet.getInt("points");
                    String team = resultSet.getString("playerteam");
                    // Set up the ComboBox with team options, the combo box will be populated with team names obtained from the database
                    if (!cbTeams.getItems().contains(team)) {
                        cbTeams.getItems().add(team);
                    }

                    // Create a Player object and add it to the chart
                    chartBuilder(playerName, points, team);
                }

            }
        } catch (Exception e) {
            System.out.println("Error initializing PlayerChartController: " + e.getMessage());

        }






    }

    // Additional methods to handle chart data, filtering, etc. can be added here
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
