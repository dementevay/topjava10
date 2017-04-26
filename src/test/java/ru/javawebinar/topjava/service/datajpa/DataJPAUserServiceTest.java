package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by andrejdementev on 25.04.17.
 */
@ActiveProfiles({"datajpa", "jpa,datajpa"})
public class DataJPAUserServiceTest extends UserServiceTest {
}
