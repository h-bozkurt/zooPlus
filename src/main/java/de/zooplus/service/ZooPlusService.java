package de.zooplus.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.zooplus.model.Customer;
import de.zooplus.model.Order;
import de.zooplus.repository.CustomerRepository;
import de.zooplus.repository.OrderRepository;

/**
 * The Class ZooPlusService provides functionalities for the Controller.
 */
@Service
@Transactional
public class ZooPlusService {

	/** The customer repository. */
	@Autowired
	private CustomerRepository customerRepository;

	/** The order repository. */
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Creates the order.
	 *
	 * @param orderId the order id
	 * @param customerId the customer id
	 * @param amount the amount
	 * @return true, if successful
	 */
	public boolean createOrder(int orderId, int customerId, int amount) {
		// return value
		boolean retVal = false;
		//find customer by id
		Optional<Customer> customerData = customerRepository.findById(customerId);
		//if customer is found
		if (customerData.isPresent()) {
			//get customer
			Customer customer = customerData.get();
			//calculate customer's new balance
			int customerBalance = customer.getBalance() - amount;
			//set customer balance
			customer.setBalance(customerBalance);
			//create order
			Order order = new Order(orderId, customerData.get(), amount * -1);
			//save Order into DB
			orderRepository.save(order);
			//set return value
			retVal = true;
		}
		return retVal;
	}

	/**
	 * Creates the customer.
	 *
	 * @param customerId the customer id
	 * @param customerName the customer name
	 * @return true, if successful
	 */
	public boolean createCustomer(int customerId, String customerName) {
		//create Customer instance
		Customer customer = new Customer(customerId, customerName);
		//save Customer into DB
		customerRepository.save(customer);
		return true;
	}

	/**
	 * Creates the payment.
	 *
	 * @param orderId the order id
	 * @param amount the amount
	 * @return true, if successful
	 */
	public boolean createPayment(Integer orderId, Integer amount) {
		//return value
		boolean retVal = false;
		//find Order by id
		Optional<Order> orderData = orderRepository.findById(orderId);
		//if Order is found
		if (orderData.isPresent()) {
			//get order
			Order order = orderData.get();
			//get customer
			Customer customer = orderData.get().getCustomer();
			//calculate customer's new balance
			int customerBalance = customer.getBalance() + amount;
			//set new balance
			order.getCustomer().setBalance(customerBalance);
			//calculate new order's amount
			amount = order.getAmount() + amount;
			//set order amount
			order.setAmount(amount);
			//save Order into DB
			orderRepository.save(order);
			retVal = true;
		}
		return retVal;
	}

	/**
	 * Gets the order balance.
	 *
	 * @param orderId the order id
	 * @return the order balance
	 */
	public Integer getOrderBalance(int orderId) {
		//return value
		Integer retVal = null;
		//find Order by id
		Optional<Order> orderData = orderRepository.findById(orderId);
		//if Order is found
		if (orderData.isPresent()) {
			//get order
			Order order = orderData.get();
			//get order's amount
			retVal = order.getAmount();
		}
		return retVal;
	}

	/**
	 * Gets the customer balance.
	 *
	 * @param customerId the customer id
	 * @return the customer balance
	 */
	public Integer getCustomerBalance(int customerId) {
		//return value
		Integer retVal = null;//find Order by id
		//find customer by id
		Optional<Customer> customerData = customerRepository.findById(customerId);
		//if Customer is found
		if (customerData.isPresent()) {
			//get customer
			Customer customer = customerData.get();
			//get customer's balance
			retVal = customer.getBalance();
		}
		return retVal;
	}

}
