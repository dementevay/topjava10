package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.CRUDInterface;
import ru.javawebinar.topjava.repository.DataCRUD;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrejdementev on 31.03.17.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private List<MealWithExceed> mealWithExceeds = new ArrayList<>();

    private CRUDInterface dataCRUD;

    @Override
    public void init() throws ServletException { //инициализация сервлетов
        super.init();
        dataCRUD = new DataCRUD();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //doGet — если мы хотим, чтобы сервлет реагировал на GET запрос
        //в свиче делаем операции delete, create update, readAll
        String action = req.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete" : {
                LOG.debug("delete");
                dataCRUD.delete(getId(req));
                resp.sendRedirect("meals");
                break;
            }
            case "create" : {
                LOG.debug("create");
            }
            case "update" : {
                LOG.debug("update");
                final Meal meal = action.equals("create") ?
                        new Meal(null, LocalDateTime.now(), "", 1000) : //create
                        dataCRUD.get(getId(req));   //update
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/meal.jsp").forward(req, resp);
                break;
            }
            case "all" :
                default:
                    req.setAttribute("meals", MealsUtil.getFilteredWithExceeded(dataCRUD.getAll(), LocalTime.of(0,0), LocalTime.of(23,59), 2000));
                    req.getRequestDispatcher("meals.jsp").forward(req, resp);
         }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //doPost — если мы хотим, чтобы сервлет реагировал на POST запрос
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        final Meal meal = new Meal(
                id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories")));

        LOG.info(meal.isNew() ? "Create" : "Update", meal);
        dataCRUD.save(meal);
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        return Integer.valueOf(Objects.requireNonNull(request.getParameter("id")));
    }
}
