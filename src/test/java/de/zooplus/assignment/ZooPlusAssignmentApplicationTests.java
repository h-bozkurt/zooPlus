package de.zooplus.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.zooplus.model.Customer;
import de.zooplus.model.Order;
import de.zooplus.repository.CustomerRepository;
import de.zooplus.repository.OrderRepository;
import de.zooplus.service.ZooPlusService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ZooPlusAssignmentApplicationTests {
	
	@Autowired
	private ZooPlusService zooPlusService;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	void createCustomerOrderTest() {
		zooPlusService.createCustomer(1, "Huseyin");
		Optional<Customer> customerData = customerRepository.findById(1);
		assertEquals(true, customerData.isPresent());
		assertEquals("Huseyin", customerData.get().getName());
		assertEquals(1, customerData.get().getId());
		
		zooPlusService.createOrder(1,1,100);
		Optional<Order> orderData = orderRepository.findById(1);
		assertEquals(true, orderData.isPresent());
		assertEquals(-100, orderData.get().getAmount());
		assertEquals(1, orderData.get().getId());
		assertEquals(1, orderData.get().getCustomer().getId());
		assertEquals("Huseyin" , orderData.get().getCustomer().getName());
		assertEquals(-100, orderData.get().getCustomer().getBalance());
		
		zooPlusService.createPayment(1, 110);
		assertEquals(10, orderData.get().getAmount());
		assertEquals(10, orderData.get().getAmount());
		
		zooPlusService.createOrder(2,1,50);
		Optional<Order> orderData1 = orderRepository.findById(2);
		assertEquals(true, orderData1.isPresent());
		assertEquals(-50, orderData1.get().getAmount());
		assertEquals(2, orderData1.get().getId());
		assertEquals(1, orderData1.get().getCustomer().getId());
		assertEquals("Huseyin" , orderData1.get().getCustomer().getName());
		assertEquals(-40, orderData1.get().getCustomer().getBalance());
		
		orderData = orderRepository.findById(1);
		assertEquals(10, orderData.get().getAmount());
		
		assertEquals(-40 ,zooPlusService.getCustomerBalance(1));
		assertEquals(10 ,zooPlusService.getOrderBalance(1));
		assertEquals(-50 ,zooPlusService.getOrderBalance(2));
	}
	
	@Test
	void failureTests() {
		assertEquals(false, zooPlusService.createOrder(1,1,100));
		assertEquals(false, zooPlusService.createPayment(1,100));
		assertEquals(null, zooPlusService.getCustomerBalance(1));
		assertEquals(null, zooPlusService.getOrderBalance(1));
	}

}
