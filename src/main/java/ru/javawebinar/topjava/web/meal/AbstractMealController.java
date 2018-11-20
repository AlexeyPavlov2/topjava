package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.Util.orElse;

@Controller
public class AbstractMealController {
    @Autowired
    protected MealService mealService;

    public Meal create(Meal meal) {
        return mealService.create(meal, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return mealService.get(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal) {
        mealService.update(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        mealService.delete(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        return MealsUtil.getWithExcess(mealService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal getWithUser(int id) {
        return mealService.getWithUser(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        return MealsUtil.getWithExcess(mealService.getBetweenDates(startDate, endDate, SecurityUtil.authUserId()), SecurityUtil.authUserId());
    }

    public List<MealTo> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return MealsUtil.getWithExcess(mealService.getBetweenDateTimes(startDateTime, endDateTime, SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        List<Meal> mealList = mealService.getBetweenDates(orElse(startDate, DateTimeUtil.MIN_DATE),
                orElse(endDate, DateTimeUtil.MAX_DATE), SecurityUtil.authUserId());
        return MealsUtil.getFilteredWithExcess(mealList, SecurityUtil.authUserCaloriesPerDay(),
                orElse(startTime, LocalTime.MIN), orElse(endTime, LocalTime.MAX)
        );
    }

}
