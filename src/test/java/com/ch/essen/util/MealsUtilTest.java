package com.ch.essen.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ch.essen.model.Meal;
import com.ch.essen.model.MealWithExceed;

class MealsUtilTest {
	   public static final List<Meal> MEALS = Arrays.asList(
	            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
	            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
	            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
	            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
	            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
	            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
	    );
	
	
	MealsUtil mealutil ;
	 
	List<MealWithExceed> emptyList  = Arrays.asList(new MealWithExceed(null, null, null, 0, false));
	
	List<MealWithExceed> expectedList = Arrays.asList(
			new MealWithExceed(
			 null,  (LocalDateTime.of(2015, Month.MAY, 30, 10, 0)), "Завтрак",  500,  false),
			new MealWithExceed(
					null,  (LocalDateTime.of(2015, Month.MAY, 31, 10, 0)), "Завтрак",  1000,  true)
			);
	
	
    List<MealWithExceed> mealsWithExceededList = MealsUtil.getFilteredWithExceeded(MealsUtil.MEALS, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

	@BeforeEach
	public void beforeEachMethod() {
		mealutil = new MealsUtil();
	}
	
	@Test
	void testcontainsAll() {
		
		assertTrue  (expectedList.containsAll (mealsWithExceededList  ));	
	}
	
	@Test
	void testGetWithExceeded() {
		assertEquals (expectedList,   mealsWithExceededList);
	
	}

//	@Test
	void testGetFilteredWithExceeded() {
		fail("Not yet implemented");
	}

//	@Test
	void testGetFilteredWithExceededByCycle() {
		fail("Not yet implemented");
	}

}
