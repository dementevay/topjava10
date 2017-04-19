package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User ref = em.getReference(User.class, userId);
            meal.setUser(ref);
            em.persist(meal);
            return meal;
        } else {
            Query query = em.createNamedQuery(Meal.UPDATE);
            query.setParameter("description", meal.getDescription());
            query.setParameter("calories",meal.getCalories());
            query.setParameter("dateTime", meal.getDateTime());
            query.setParameter("id", meal.getId());
            query.setParameter("userId", userId);
            return query.executeUpdate() != 0 ? meal : null;
            /*//с методом merge не могу проверить еду на принадлежность к юзеру
            User ref = em.getReference(User.class, userId);
            meal.setUser(ref);
            return em.merge(meal);*/
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query =  em.createNamedQuery(Meal.DELETE);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        return query.executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Query query =  em.createNamedQuery(Meal.GET,Meal.class);
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        List<Meal> meals = query.getResultList();
        //em.find(Meal.class, id);//и опять нулевой юзер не даёт мне проверить хозяина еды
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL,Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Query query = em.createNamedQuery(Meal.BETWEEN);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}