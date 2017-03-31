package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrejdementev on 31.03.17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private List<MealWithExceed> mealWithExceeds = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        mealWithExceeds = MealsUtil.getFilteredWithExceeded(MealsUtil.getMealsDB(), LocalTime.of(0,0), LocalTime.of(0,0), 2000);
        req.setAttribute("mealsWE", mealWithExceeds);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
