package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by andrejdementev on 25.04.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, "jdbc"})
public class JDBCMealServiceTest extends MealServiceTest {
}
