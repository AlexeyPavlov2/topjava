package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
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

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.Util.orElse;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class AbstractMealController {
    private static final Logger log = getLogger(AbstractMealController.class);

    @Autowired
    protected MealService mealService;

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        checkNew(meal);
        log.info("create {} for user {}", meal, userId);
        return mealService.create(meal, userId);
    }

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get meal {} for user {}", id, userId);
        return mealService.get(id, userId);
    }

    public void update(Meal meal) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(meal, meal.getId());
        log.info("update {} for user {}", meal, userId);
        mealService.update(meal, userId);


        mealService.update(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        mealService.delete(id, userId);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return MealsUtil.getWithExcess(mealService.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
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
