package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExceeded.forEach(System.out::println);

        //System.out.println(getFilteredWithExceededByCycle(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        //////////////////////// STREAM API ///////////
        Map<LocalDate, Integer> mapDaysCalories = meals.stream()
                .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(Meal::getCalories)));

        return meals.stream().unordered()
                .filter(ml -> TimeUtil.isBetween(ml.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> getUMWithExceed( m, mapDaysCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

        ///////////////////// COLLECTIONS  METHOD //////////
        /*Map<LocalDate, Integer> mapDaysCalories = new HashMap<>();
        mealList.forEach(item -> mapDaysCalories.merge(item.getDateTime().toLocalDate(),item.getCalories(), (v1, v2) -> v1+v2));

        List<UserMealWithExceed> result = new ArrayList<>();
        for ( UserMeal userMeal : mealList ) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(getUMWithExceed(userMeal,mapDaysCalories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        return result;*/
    }

    private static MealWithExceed getUMWithExceed(Meal userMeal, boolean exceed) {
        return new MealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceed);
    }
}