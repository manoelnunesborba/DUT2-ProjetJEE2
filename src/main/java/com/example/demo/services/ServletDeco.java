package com.example.demo.services;

import com.example.demo.persistance.MediathequeData;
import mediatek2022.Mediatheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Deco", value = "/deco")
public class ServletDeco extends HttpServlet {
    Mediatheque mdt = Mediatheque.getInstance();

    public ServletDeco() {
        try {
            Class.forName(MediathequeData.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getSession().setAttribute("user", null);
            resp.sendRedirect("index.jsp");
        }catch (NullPointerException e){
            resp.sendRedirect("index.jsp");
        }
    }
}
