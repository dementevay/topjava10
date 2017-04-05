package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll(){
        return  MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
    }

    public boolean delete(int id) {
        return service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) {
        return service.get(id, AuthorizedUser.id());
    }

    public Meal save(Meal meal) {
        return service.save(meal);
    }

    public List<MealWithExceed> getWithFilter(LocalDateTime startDT, LocalDateTime endDT) {
        return service.getWithFilter(startDT, endDT, AuthorizedUser.id(), AuthorizedUser.getCaloriesPerDay());
    }

}