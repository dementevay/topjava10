package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;


    public Collection<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public List<Meal> getFilteredDateTime(LocalDateTime startDT, LocalDateTime endDT, int userId) {
        return repository.getFilteredDateTime(startDT, endDT, userId);
    }
}