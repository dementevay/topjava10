package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, AuthorizedUser.id()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (userId == meal.getUserId()){
            if (meal.isNew()){
                meal.setId(counter.incrementAndGet());
            }
            repository.put(meal.getId(), meal);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if(get(id, userId) != null) return repository.remove(id) != null;
        else return false;

    }

    @Override
    public Meal get(int id, int userId) {
        if(repository.get(id).getUserId() == userId) return repository.get(id);
        else return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> list = repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public  List<Meal> getFilteredDate (LocalDateTime startDT, LocalDateTime endDT, int userId) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDateTime().toLocalDate(), startDT.toLocalDate(), endDT.toLocalDate()))
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }
}

