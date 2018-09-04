package com.ch.essen.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ch.essen.model.Meal;
import com.ch.essen.repository.IMealRepository;
import com.ch.essen.repository.mock.InMemoryMealRepositoryImpl;
import com.ch.essen.util.MealsUtil;

@WebServlet("/Meals")
public class MealServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
	private static IMealRepository repository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		repository = new InMemoryMealRepositoryImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		LOG.info("LOG.info: getAll meals  ");
//		request.setAttribute("meals", MealsUtil.getWithExceeded(MealsUtil.MEALS, MealsUtil.DEFAULT_CALORIES_PER_DAY));
		request.setAttribute("meals",
				MealsUtil.getWithExceeded(repository.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
		int maxCalory = 1000;

		String myMealsList = "/mealsList.jsp";

		// get data and add edit and delete

		String action = request.getParameter("action");

		if (action == null) {
			// if no action show list
			request.getRequestDispatcher(myMealsList).forward(request, response);
		} else if (action.equals("delete")) {
			// get id, with repository delete and stay on same page redirect
			String id = request.getParameter("id");
			Integer valueOfID = null;
			if (id.isEmpty()) {
				id = null;
			} else {
				valueOfID = Integer.valueOf(id);
			}
			repository.delete(valueOfID);
			// <input type="hidden" name="id" value="${meal.id }">
			// if in method Post id = null, its mean create new Meal
		} else {

			final Meal mealCreate;
			if (action.equals("create")) {
				mealCreate = new Meal(LocalDateTime.now(), "", maxCalory);
			} else {
				mealCreate = repository.get(getID(request));

			}
			// go to check in mealEdit.jsp id == null ? or not
			// -----------------------
			request.setAttribute("meal", mealCreate);
			String url = "/mealEdit.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

	/**
	 * @param request
	 * @return getParameter of id
	 */
	private int getID(HttpServletRequest request) {
		String paramId = Objects.requireNonNull(request.getParameter("id"));
		return Integer.valueOf(paramId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// <input type="hidden" name="id" value="${meal.id }">
		String id = request.getParameter("id");
		Integer valueOfID = null;
		if (id.isEmpty()) {
			id = null;
		} else {
			valueOfID = Integer.valueOf(id);
		}
		int valCalories = 0;
		try {
			valCalories = Integer.valueOf(request.getParameter("calories"));
		} catch (Exception e) {
			System.out
					.println(request.getParameter("calories") + " <--- not a number, forward to ----> errorPage.jsp ");
			// redirect to errorPage , then return to edit
			String url = "/errorPage.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
//---------------changes1----------
		String description = request.getParameter("description");

		Meal saveCreateMeal = new Meal(valueOfID,
				// <input type="datetime-local" value="${meal.dateTime }" name="dateTime">
				LocalDateTime.parse(request.getParameter("dateTime")),
				// <input type="text" value="${meal.description }" name="description">
				description,

				// Integer.valueOf(request.getParameter("calories"))
				// check user enter number if not to errorPage.jsp
				valCalories);

		// if getParameter("description") is empty, do nothing.
		if (!description.equals("")) {
			repository.save(saveCreateMeal);
		}
		response.sendRedirect("Meals");
	}
}
