package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;

import java.util.Collection;

public interface BurgerDao {

    void save(Burger burger);

    Burger findById(int id);

    Collection<Burger> findAll();

    Collection<Burger> findByPrice(float price);

    Collection<Burger> findByBreadType(BreadType breadType);

    Collection<Burger> findByContent(String content);

    void update(Burger burger);

    void remove(int id);
}
