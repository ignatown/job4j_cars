package ru.job4j.servlet;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import ru.job4j.model.Ad;
import ru.job4j.model.User;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ad> adList = HbnStore.instOf().findAdsForUser(
                ((User) req.getSession().getAttribute("user")).getId());
        req.setAttribute("items", adList);
        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }
}
