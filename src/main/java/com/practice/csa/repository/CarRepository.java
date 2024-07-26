package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.csa.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

}
