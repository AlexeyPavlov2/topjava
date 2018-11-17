package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        User user = crudUserRepository.getOne(userId);
        if (meal.isNew()) {
            meal.setUser(user);
        } else if (get(meal.getId(), userId) == null) {
            return null;
        };
        System.out.println("DATAJPA ---");
        meal.setUser(user);
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        /*var meal = crudMealRepository.findById(id).orElse(null);
        if (meal == null || meal.getUser().getId() != userId) {
                return null;
        }
        return meal;*/
        return crudMealRepository.getByIdAndUserId(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.findAll().stream()
                .filter(el -> el.getUser().getId() == userId)
                .sorted((el1, el2) -> el2.getDateTime().compareTo(el1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudMealRepository.findAll().stream()
                .filter(el -> el.getUser().getId() == userId &&
                el.getDateTime().compareTo(startDate) >= 0 &&
                    el.getDateTime().compareTo(endDate) <= 0)
                .sorted((el1, el2) -> el2.getDateTime().compareTo(el1.getDateTime()))
                .collect(Collectors.toList());
    }
}
