package ru.job4j.servlet;

import ru.job4j.model.Ad;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = HbnStore.instOf().findUserByEmail(email);
        if (user == null) {
            HbnStore.instOf().addUser(User.of(
                    req.getParameter("name"),
                    email,
                    req.getParameter("password")));
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
