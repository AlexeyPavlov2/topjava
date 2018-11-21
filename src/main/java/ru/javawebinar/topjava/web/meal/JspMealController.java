package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {
    @GetMapping
    public String root(HttpServletRequest request) {
        request.setAttribute("meals", super.getAll());
        return "meals";
    }

    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "/meals";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        delete(Integer.valueOf(request.getParameter("id")));
        return "redirect:/meals";

    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "Новая еда", 500));
        return "/mealForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("meal", super.get(Integer.valueOf(request.getParameter("id"))));
        return "/mealForm";
    }

    @PostMapping
    public String saveMeal(HttpServletRequest request) {
        LocalDateTime mealDateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description").isEmpty() ? "Без названия" : request.getParameter("description");
        int calories = Integer.valueOf(request.getParameter("calories"));
        Meal meal = new Meal(mealDateTime, description, calories);
        if (request.getParameter("id").isEmpty()) {
            create(meal);
        } else {
            meal.setId(Integer.valueOf(request.getParameter("id")));
            update(meal);
        }
        return "redirect:/meals";
    }

}
