package com.games.projeto_web.games_projeto_web;

import java.sql.*;
import java.util.ArrayList;
import com.google.gson.JsonObject;

public class GameDataBase {
    private Connection connection;

    public GameDataBase() throws ClassNotFoundException, SQLException{
        createConnection();
    }

    private void createConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/games?user=postgres&password=Vasconcelos@";
        connection = DriverManager.getConnection(url);
    }

    public String createGame(Game game) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO games (gameName, gameGender) VALUES (?, ?)");
        preparedStatement.setString(1, game.getName());
        preparedStatement.setString(2, game.getGender());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return "Jogo criado com sucesso.";
    }

    public ArrayList<JsonObject> readGame() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM games");
        ArrayList<JsonObject> games = new ArrayList<JsonObject>();
        while(result.next()){
            JsonObject game = new JsonObject();
            game.addProperty("id", result.getInt("gameId"));
            game.addProperty("nome", result.getString("gameName"));
            game.addProperty("gênero", result.getString("gameGender"));
            games.add(game);
        }
        statement.close();
        return games;
    }

    public String deleteGame(Integer gameId) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM games WHERE gameId = ?");
        preparedStatement.setInt(1, gameId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return "Jogo deletado com sucesso.";
    }

    public String updateGame(Integer gameId, Game game) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE games SET gameName = ?, gameGender = ? WHERE gameId = ?");
        preparedStatement.setString(1, game.getName());
        preparedStatement.setString(2, game.getGender());
        preparedStatement.setInt(3, gameId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return "Informações do jogo atualizadas com sucesso.";
    }

    public void closeConnection() throws ClassNotFoundException, SQLException{
        if (connection != null){
            connection.close();
        }
    }
}
