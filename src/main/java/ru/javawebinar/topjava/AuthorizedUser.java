package ru.javawebinar.topjava;

import org.springframework.stereotype.Controller;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

@Controller
public class AuthorizedUser {

    static int id = 1;

    public static int id() {
        return id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}