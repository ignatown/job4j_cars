package ru.job4j.servlet;

import ru.job4j.model.Ad;
import ru.job4j.model.Body;
import ru.job4j.model.Brand;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Body> bodyList = HbnStore.instOf().findAllBodies();
        List<Brand> brandList = HbnStore.instOf().findAllBrands();
        req.setAttribute("bodies", bodyList);
        req.setAttribute("brands", brandList);
        req.setAttribute("user", user);
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        HbnStore.instOf().addAd(
                Ad.of(
                        req.getParameter("descriptionAd"),
                        user,
                        false
                ),
                req.getParameterValues("bodies"),
                req.getParameterValues("brands")
        );
        resp.sendRedirect(req.getContextPath() + "/cabinet");
    }
}
