package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by andrejdementev on 25.04.17.
 */
@ActiveProfiles({"jpa", "jpa,datajpa"})
public class JPAMealServiceTest extends MealServiceTest {
}
