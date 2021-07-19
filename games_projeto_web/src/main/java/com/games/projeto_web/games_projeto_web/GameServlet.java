package com.games.projeto_web.games_projeto_web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

@WebServlet("/api/v1/games/")
public class GameServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            GameDataBase db = (GameDataBase) req.getAttribute("db");
            ArrayList<JsonObject> games = db.readGame();
            res.getWriter().println(games);
        } catch (Exception e){
            System.err.println(e);
        }    
    } 

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            GameDataBase db = (GameDataBase) req.getAttribute("db");
            Game game = new Gson().fromJson(req.getReader(), Game.class);
            String response = db.createGame(game);
            res.getWriter().println(response);
        } catch (Exception e) {
            System.err.println(e);
        }
    } 

    public void doDelete(HttpServletRequest req, HttpServletResponse res) {
        try {
            GameDataBase db = (GameDataBase) req.getAttribute("db");
            Integer gameId = Integer.parseInt(req.getParameter("gameId"));
            String response = db.deleteGame(gameId);
            res.getWriter().println(response);
        } catch (Exception e) {
            System.err.println(e);
        }
    } 

    public void doPut(HttpServletRequest req, HttpServletResponse res) {
        try {
            GameDataBase db = (GameDataBase) req.getAttribute("db");
            Integer gameId = Integer.parseInt(req.getParameter("gameId"));
            Game game = new Gson().fromJson(req.getReader(), Game.class);
            String response = db.updateGame(gameId, game);
            res.getWriter().println(response);
        } catch (Exception e) {
            System.err.println(e);
        }
    } 
}