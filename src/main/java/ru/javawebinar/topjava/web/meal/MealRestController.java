package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll(){
        List<Meal> meals = service.getAll().stream().filter(m -> m.getUserId() == AuthorizedUser.id()).collect(Collectors.toList());
        return  MealsUtil.getWithExceeded(meals, AuthorizedUser.getCaloriesPerDay());
    }

    public void delete(int id) {
        if (get(id).getUserId() == AuthorizedUser.id()) {
            if(!service.delete(id)) throw new NotFoundException("Can't delete this meal.");
        }
    }

    public Meal get(int id) {
        Meal meal = service.get(id);
        if (meal != null && meal.getUserId() == AuthorizedUser.id()) {
            return meal;
        } else throw new NotFoundException("Can't get this meal.");
    }

    public Meal save(Meal meal) {
        if(meal.getUserId() == AuthorizedUser.id() || meal.isNew()){
            service.save(meal);
        } else throw new NotFoundException("Can't save this meal.");
        return meal;
    }

    public List<MealWithExceed> getWithFilter(LocalDateTime startDT, LocalDateTime endDT) {
        if (startDT == null) startDT = LocalDateTime.MIN;
        if (endDT == null) endDT = LocalDateTime.MAX;
        Collection<Meal> meals = service.getFilteredDateTime(startDT, endDT, AuthorizedUser.id());
        List<MealWithExceed> list =  MealsUtil.getWithExceeded(meals, AuthorizedUser.getCaloriesPerDay());
        return list != null ?  list : Collections.emptyList();
    }

}