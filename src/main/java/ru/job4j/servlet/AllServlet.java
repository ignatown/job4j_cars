package ru.job4j.servlet;

import ru.job4j.model.Ad;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Ad> adList = HbnStore.instOf().findAllAds();
    req.setAttribute("ads", adList);
    req.getRequestDispatcher("all.jsp").forward(req, resp);
    }
}
