package de.zooplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.zooplus.model.Customer;

/**
 * The Interface CustomerRepository.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
