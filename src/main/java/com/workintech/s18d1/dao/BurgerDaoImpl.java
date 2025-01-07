package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class BurgerDaoImpl implements BurgerDao {

    @Autowired
    private BurgerRepository burgerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Burger burger) {

        burgerRepository.save(burger);
    }

    @Override
    public Burger findById(int id) {

        return burgerRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Burger> findAll() {

//        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
//
//        return query.getResultList();
//
        return burgerRepository.findAll();
    }

    @Override
    public Collection<Burger> findByPrice(float price) {

        TypedQuery<Burger> query = entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY price DESC", Burger.class);

        query.setParameter("price", price);

        return query.getResultList();
    }

    @Override
    public Collection<Burger> findByBreadType(BreadType breadType) {

        TypedQuery<Burger> query = entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC", Burger.class);

        query.setParameter("breadType", breadType);

        return query.getResultList();
    }

    @Override
    public Collection<Burger> findByContent(String content) {

        TypedQuery<Burger> query = entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%', :content, '%c')", Burger.class);

        query.setParameter("content", content);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Burger burger) {

        entityManager.merge(burger);
    }

    @Override
    public void remove(int id) {

        burgerRepository.deleteById(id);

//        Burger b = findById(id);
//        entityManager.remove(b);
    }
}
