package com.example.assignment1gc200583500;

public class Player {
    private String playerName;
    private String team;
    private String position;
    private String country;
    private int points;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    private int oRebounds;
    private int dRebounds;
    private int turnovers;
    private int fouls;
    private int minutes;

    public Player() {}

    public Player(String playerName, String team, String position, String country, int points, int rebounds,
                  int assists, int steals, int blocks, int oRebounds, int dRebounds, int turnovers, int fouls,
                  int minutes) {
        this.playerName = playerName;
        this.team = team;
        this.position = position;
        this.country = country;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.oRebounds = oRebounds;
        this.dRebounds = dRebounds;
        this.turnovers = turnovers;
        this.fouls = fouls;
        this.minutes = minutes;
    }

    // Getters and setters for each field
    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty() || value.length() < 3) {
            throw new IllegalArgumentException(fieldName + " must be at least 3 characters long.");
        }
    }

    private void validateNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        // Validate that playerName is not null or empty or should have at least 3 characters
        validateString(playerName, "Player name");
        this.playerName = playerName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        // Validate that team is not null or empty or should have at least 3 characters
        validateString(team, "Team name");
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        // Validate that position is not null or empty or should have at least 1 character
        if (position == null || position.trim().isEmpty() || position.isEmpty()) {
            throw new IllegalArgumentException("Position must be at least 1 character long.");
        }
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        // Validate that country is not null or empty or should have at least 3 characters
        validateString(country, "Country name");
        this.country = country;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        // Validate that points is not negative
        validateNonNegative(points, "Points");
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        validateNonNegative(rebounds, "Rebounds");
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        // Validate that assists is not negative
        validateNonNegative(assists, "Assists");
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        // Validate that steals is not negative
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        // Validate that blocks is not negative
        validateNonNegative(blocks, "Blocks");
        this.blocks = blocks;
    }

    public int getoRebounds() {
        return oRebounds;
    }

    public void setoRebounds(int oRebounds) {
        // Validate that offensive rebounds is not negative
        validateNonNegative(oRebounds, "Offensive rebounds");
        this.oRebounds = oRebounds;
    }

    public int getdRebounds() {
        return dRebounds;
    }

    public void setdRebounds(int dRebounds) {
        // Validate that defensive rebounds is not negative
        validateNonNegative(dRebounds, "Defensive rebounds");
        this.dRebounds = dRebounds;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        // Validate that turnovers are not negative
        validateNonNegative(turnovers, "Turnovers");
        this.turnovers = turnovers;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        // Validate that fouls are not negative
        validateNonNegative(fouls, "Fouls");
        this.fouls = fouls;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        // Validate that minutes is not negative
        validateNonNegative(minutes, "Minutes");
        this.minutes = minutes;
    }
}
