package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrejdementev on 03.04.17.
 */
public class UsersUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "Уася", "vasil2@gmail.com", "admin", Role.ROLE_USER),
            new User(2, "Дормидонт", "macho2005@gmail.com", "root", Role.ROLE_USER),
            new User(3, "Петюня", "petruha@gmail.com", "123456", Role.ROLE_ADMIN),
            new User(4, "Уася", "vasil1@gmail.com", "123456", Role.ROLE_USER),
            new User(5, "Уася", "vasil6@gmail.com", "123456", Role.ROLE_USER),
            new User(6, "Уася", "vasil0@gmail.com", "123456", Role.ROLE_USER)
    );

    public static List<User> getUSERS() {
        return USERS;
    }
}
