package ru.job4j.servlet;

import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HbnStore.instOf().deleteAd(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/cabinet.jsp");
    }
}
