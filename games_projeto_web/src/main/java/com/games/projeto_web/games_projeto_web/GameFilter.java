package com.games.projeto_web.games_projeto_web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class GameFilter implements Filter{

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        try {
            GameDataBase db = new GameDataBase();
            req.setAttribute("db", db);
            res.setContentType("application/json;charset=UTF-8");
            chain.doFilter(req, res);
            db.closeConnection();
        } catch (Exception e){
            System.err.println(e);
        } 
    }
    
}
