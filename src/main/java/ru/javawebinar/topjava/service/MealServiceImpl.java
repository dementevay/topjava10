package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId),id);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public List<MealWithExceed> getWithFilter(LocalDateTime startDT, LocalDateTime endDT, int userId, int calories) {
        if (startDT == null) startDT = LocalDateTime.MIN;
        if (endDT == null) endDT = LocalDateTime.MAX;
        LocalTime lt1 = startDT.toLocalTime();
        LocalTime lt2 = endDT.toLocalTime();

        Collection<Meal> filteredUserDate = repository.getFilteredDate(startDT, endDT, userId);
        List<MealWithExceed> withExceed =  MealsUtil.getWithExceeded(filteredUserDate, calories);
        List<MealWithExceed> filteredTime = withExceed.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), lt1, lt2)).collect(Collectors.toList());
        return filteredTime != null ?  filteredTime : Collections.emptyList();
    }
}