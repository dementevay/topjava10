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
import javax.persistence.TypedQuery;
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
        User ref = em.getReference(User.class, userId);

        if (meal.isNew()) {
            meal.setUser(ref);
            em.persist(meal);
            return meal;
        } else {
            meal.setUser(ref);
            Meal meal1 = get(meal.getId(), userId);
            return meal1 != null ? em.merge(meal) : null;
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
        Meal meal = em.find(Meal.class, id);
        return userId == meal.getUser().getId() ?  meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL,Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        TypedQuery<Meal> query = em.createNamedQuery(Meal.BETWEEN, Meal.class);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}