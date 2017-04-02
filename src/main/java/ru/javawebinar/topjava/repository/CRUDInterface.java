package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by andrejdementev on 01.04.17.
 */
public interface CRUDInterface {
    Meal save(Meal meal);
    void delete(int id);
    Meal get(int id);
    Collection<Meal> getAll();

}
