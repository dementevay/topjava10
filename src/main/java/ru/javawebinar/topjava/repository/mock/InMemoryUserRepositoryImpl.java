package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.ArrayList;
import java.util.Comparator;
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

    /*//for test method getAll()
    public static void main(String[] args) {
        InMemoryUserRepositoryImpl users = new InMemoryUserRepositoryImpl();
        users.getAll().forEach(u -> System.out.println("Name = "+u.getName()+" ; email = " + u.getEmail()));
    }*/

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id) != null;
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
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> all = new ArrayList<>(repository.values());
        all.sort(NAME_EMAIL);
        return all;
    }

    private static Comparator<User> NAME_EMAIL = (o1, o2) -> {
        int compareName = o1.getName().compareTo(o2.getName());
        return compareName == 0 ? o1.getEmail().compareTo(o2.getEmail()) : compareName;
    };

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return repository.values().stream()
                .filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
