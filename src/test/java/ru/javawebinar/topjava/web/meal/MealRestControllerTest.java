package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.TestUtil.readFromJson;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    protected MealService mealService;

    @Test
    void testCreate() throws Exception {
        Meal meal = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(meal)));

        Meal returned = readFromJson(action, Meal.class);
        meal.setId(returned.getId());
        assertMatch(returned, meal);
        assertMatch(mealService.getAll(USER_ID), meal, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(MealsUtil.getWithExcess(MealTestData.MEALS, UserTestData.USER.getCaloriesPerDay())));

    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MealTestData.MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(MealTestData.MEAL1));
    }

    @Test
    void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between?startDate=2015-05-30&endDate=2015-05-30&endTime=13:00:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJsonArray(
                        MealsUtil.createWithExcess(MEAL2, false),
                        MealsUtil.createWithExcess(MEAL1, false)));
    }


    @Test
    void testUpdate() throws Exception {
        Meal meal = MealTestData.getUpdated();
        mockMvc.perform(put(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(meal)))
                .andExpect(status().isNoContent());
        assertMatch(mealService.get(MEAL1_ID, USER_ID), meal);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(mealService.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }


}
