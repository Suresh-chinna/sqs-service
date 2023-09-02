package com.sqs.process.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqs.process.entity.OrdersEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrdersEntity,Integer>{

}
