package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by andrejdementev on 25.04.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, "jpa"})
public class JPAMealServiceTest extends MealServiceTest {
}
