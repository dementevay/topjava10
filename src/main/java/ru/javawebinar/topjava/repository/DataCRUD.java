package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by andrejdementev on 01.04.17.
 */
public class DataCRUD implements CRUDInterface {
    Map<Integer, Meal> repository = new ConcurrentHashMap<>();//<id, Meal>
    AtomicInteger counter = new AtomicInteger();

    {
        MealsUtil.getMealsDB().forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if(meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}
