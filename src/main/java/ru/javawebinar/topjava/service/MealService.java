package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MealService {

    Collection<Meal> getAll();

    boolean delete(int id);

    //return Meal or null
    Meal get(int id);

    Meal save(Meal meal);

    List<Meal> getFilteredDateTime(LocalDateTime startDT, LocalDateTime endDT, int userId);

}