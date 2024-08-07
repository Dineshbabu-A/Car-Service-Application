package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.csa.entity.Contract;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{

}
