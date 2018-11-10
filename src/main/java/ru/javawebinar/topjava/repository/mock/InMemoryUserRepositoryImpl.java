package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.add(new User(counter.incrementAndGet(), "admin", "admin@admin.com", "1234", 2000,
                        true, new HashSet<Role>() {{add(Role.ROLE_ADMIN);}}));
        UsersUtil.USERS.add(new User(counter.incrementAndGet(), "user", "user@user.com", "1234", 2000,
                true, new HashSet<Role>() {{add(Role.ROLE_USER);}}));
    }



    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.values().removeIf(el -> el.equals(id));
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        Optional<User> user = Optional.of(repository.get(id));
        return user.orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().isEmpty() ? Collections.emptyList() : new ArrayList(repository.values());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        Optional<User> first = repository.values()
                .stream()
                .filter(el -> el.getEmail().equals(email))
                .findFirst();
        return first.orElse(null);
    }
}
