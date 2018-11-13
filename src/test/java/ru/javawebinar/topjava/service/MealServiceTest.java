package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.util.MealsUtil.MEALS;

@ContextConfiguration({
        "classpath:spring/spring-test-jdbc-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Before
    public void Before() {
        MealTestData.fillTestData();
    }

    @Test
    public void get() {
        int mealId = 100003;
        MealTestData.fillTestData();
        Meal meal = MealTestData.mealsMap.get(USER_ID).get(mealId);
        MealTestData.assertMatch(service.get(mealId, USER_ID), meal);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(100100, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFoundNotOwner() {
        service.get(100001, ADMIN_ID);
    }


    @Test
    public void delete() {
        int mealId = 100002;
        MealTestData.mealsMap.get(USER_ID).remove(mealId);
        List<Meal> list = new ArrayList<>(MealTestData.mealsMap.get(USER_ID).values());
        list.sort((el1, el2) -> el2.getDateTime().compareTo(el1.getDateTime()));
        service.delete(mealId, USER_ID);
        MealTestData.assertMatch(list,
                service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(100100, USER_ID);
    }


    @Test
    public void getBetweenDateTimes() {
    }

    @Test
    public void getAll() {
        List<Meal> list = new ArrayList<>(MealTestData.mealsMap.get(USER_ID).values());
        list.sort((el1, el2) -> el2.getDateTime().compareTo(el1.getDateTime()));
        MealTestData.assertMatch(list,
                service.getAll(USER_ID));
    }

    @Test
    public void update() {
        int mealId = 100002;
        Meal expected = MealTestData.mealsMap.get(USER_ID).get(mealId);
        expected.setCalories(1000);
        service.update(expected, USER_ID);
        Meal actual = service.get(mealId, USER_ID);
        MealTestData.assertMatch(expected, actual);
    }


    @Test(expected = NotFoundException.class)
    public void updateAnotherUsersMeal() {
        Meal meal = new Meal(111000, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        service.update(meal, USER_ID);
    }

    @Test
    public void create() {
        Meal meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 10), "Завтрак", 500);
        service.create(meal, USER_ID);
        meal.setId(100009);
        MealTestData.assertMatch(meal, service.get(100009, USER_ID));
    }
}