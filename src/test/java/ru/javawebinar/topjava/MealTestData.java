package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.util.MealsUtil.MEALS;

public class MealTestData {
    public static Map<Integer /*userId*/, Map<Integer /*id*/, Meal>> mealsMap = new HashMap<>();

    public static void fillTestData() {
        mealsMap = new HashMap<>();
        for (int i = 0; i < MEALS.size(); i++) {
            MEALS.get(i).setId(START_SEQ + i + 2);
        }

        mealsMap.put(USER_ID, new HashMap<>());
        MEALS.forEach(el -> mealsMap.get(USER_ID).put(el.getId(), el));


        mealsMap.put(ADMIN_ID, new HashMap<>());
        mealsMap.get(ADMIN_ID).put(100008, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 555));


    }


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> expected, Iterable<Meal> actual) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
