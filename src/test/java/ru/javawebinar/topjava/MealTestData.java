package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static  Meal USER_MEAL_1 = new Meal(1,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static  Meal USER_MEAL_1_UP = new Meal(1,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 900);
    public static  Meal ADMIN_MEAL_1 = new Meal(7,LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static  Meal ADMIN_MEAL_1_UP = new Meal(7,LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 610);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();

    public static final List<Meal> MEALS_USER_DELETE_1 = Arrays.asList(
            new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
            new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(2, LocalDateTime.of( 2015, Month.MAY, 30, 13, 0), "Обед", 1000)
            //new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
    );
    public static final List<Meal> MEALS_USER_1_UP = Arrays.asList(
            new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
            new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 900)
    );
    public static final List<Meal> MEALS_ADMIN_DELETE_1 = Arrays.asList(
            new Meal(8, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500)
            //new Meal(7, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510),
    );
    public static final List<Meal> MEALS_ADMIN_1_UP = Arrays.asList(
            new Meal(8, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500),
            new Meal(7, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 610)
    );


}



