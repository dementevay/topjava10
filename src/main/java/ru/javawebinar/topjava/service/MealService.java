package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MealService {

    Collection<Meal> getAll(int userId);

    void delete(int id, int userId);

    //return Meal or null
    Meal get(int id, int userId);

    Meal save(Meal meal, int userId);

    List<MealWithExceed> getWithFilter(LocalDateTime startDT, LocalDateTime endDT, int userId, int calories);

}