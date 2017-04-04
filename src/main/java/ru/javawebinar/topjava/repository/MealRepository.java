package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal);

    boolean delete(int id);

    Meal get(int id);

    Collection<Meal> getAll();

    List<Meal> getFilteredDateTime(LocalDateTime startDT, LocalDateTime endDT, int userId);

}
