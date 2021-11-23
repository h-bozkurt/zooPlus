package de.zooplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.zooplus.model.Order;

/**
 * The Interface OrderRepository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
