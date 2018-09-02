package com.ch.essen.repository;


import java.util.Collection;

import com.ch.essen.model.Meal;

/**
 */
public interface IMealRepository {
    Meal save(Meal Meal);

    void delete(int id);

    Meal get(int id);

    Collection<Meal> getAll();
}
