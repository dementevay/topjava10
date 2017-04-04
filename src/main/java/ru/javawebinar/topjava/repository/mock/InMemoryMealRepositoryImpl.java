package ru.javawebinar.topjava.repository.mock;

import com.google.common.collect.Ordering;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
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
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id, repository.getOrDefault(id, null));
    }

    @Override
    public Meal get(int id) {
        return repository.getOrDefault(id, null);

    }

    @Override
    public Collection<Meal> getAll() {
        List<Meal> list = new ArrayList<>(repository.values());
        list.sort(DATE_ORDERING);
        return Collections.synchronizedCollection(list);
    }

    private static Ordering<Meal> DATE_ORDERING =
              Ordering.natural().reverse().onResultOf(Meal::getDateTime);

    public  List<Meal> getFilteredDateTime(LocalDateTime startDT, LocalDateTime endDT, int userId) {
        return getAll().stream()
                .filter(m -> m.getUserId() == userId)
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDateTime().toLocalDate(), startDT.toLocalDate(), endDT.toLocalDate()))
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), startDT.toLocalTime(), endDT.toLocalTime()))
                .collect(Collectors.toList());
    }
}

