package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.csa.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{

}
