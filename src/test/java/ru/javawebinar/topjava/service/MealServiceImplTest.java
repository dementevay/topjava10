package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;

import static org.junit.Assert.*;

/**
 * Created by andrejdementev on 12.04.17.
 */
@ContextConfiguration("classpath:spring/spring-app-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceImplTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService repository;

    @Test
    public void testGet() throws Exception {
        Meal meal = repository.get(1,USER_ID);
        MATCHER.assertEquals(meal, USER_MEAL_1);

        Meal meal2 = repository.get(7,ADMIN_ID);
        MATCHER.assertEquals(meal2, ADMIN_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetException() throws Exception {
        Meal meal = repository.get(1,ADMIN_ID);
        MATCHER.assertEquals(meal, USER_MEAL_1);

        Meal meal2 = repository.get(7,USER_ID);
        MATCHER.assertEquals(meal2, ADMIN_MEAL_1);
    }

    @Test
    public void delete() throws Exception {
        repository.delete(1, USER_ID);
        MATCHER.assertCollectionEquals(repository.getAll(USER_ID), MEALS_USER_DELETE_1);

        repository.delete(7, ADMIN_ID);
        MATCHER.assertCollectionEquals(repository.getAll(ADMIN_ID), MEALS_ADMIN_DELETE_1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteExeption() throws Exception {
        repository.delete(1, ADMIN_ID);
        MATCHER.assertCollectionEquals(repository.getAll(USER_ID), MEALS_USER_DELETE_1);

        repository.delete(7, USER_ID);
        MATCHER.assertCollectionEquals(repository.getAll(ADMIN_ID), MEALS_ADMIN_DELETE_1);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void update() throws Exception {
        repository.update(USER_MEAL_1_UP, USER_ID);
        MATCHER.assertCollectionEquals(repository.getAll(USER_ID), MEALS_USER_1_UP);

        repository.update(ADMIN_MEAL_1_UP, ADMIN_ID);
        MATCHER.assertCollectionEquals(repository.getAll(ADMIN_ID), MEALS_ADMIN_1_UP);
    }

    @Test(expected = NotFoundException.class)
    public void updateExeption() throws Exception {
        repository.update(USER_MEAL_1_UP, ADMIN_ID);
        MATCHER.assertCollectionEquals(repository.getAll(USER_ID), MEALS_USER_1_UP);

        repository.update(ADMIN_MEAL_1_UP, USER_ID);
        MATCHER.assertCollectionEquals(repository.getAll(ADMIN_ID), MEALS_ADMIN_1_UP);
    }

    @Test
    public void save() throws Exception {
    }

}