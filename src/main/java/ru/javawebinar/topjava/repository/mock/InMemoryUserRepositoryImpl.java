package ru.javawebinar.topjava.repository.mock;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.getUSERS().forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id, repository.getOrDefault(id, null));
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()) user.setId(counter.incrementAndGet());
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.getOrDefault(id, null);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
            List<User> all = new ArrayList<>(repository.values());
            all.sort(NAME_EMAIL_ORDERING);
            return all;


    }
    private static Ordering<User> NAME_ORDERING = Ordering.natural().onResultOf(User::getName);
    private static Ordering<User> MAIL_ORDERING = Ordering.natural().onResultOf(User::getEmail);
    private static Ordering<User> NAME_EMAIL_ORDERING = Ordering.compound(Lists.newArrayList(NAME_ORDERING,MAIL_ORDERING));

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return (User)repository.entrySet().stream()
                .filter(us -> us.getValue().getEmail().equals(email))
                .map(Map.Entry::getValue);
    }
}
