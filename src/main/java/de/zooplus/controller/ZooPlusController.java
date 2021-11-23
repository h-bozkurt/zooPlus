package de.zooplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.zooplus.model.Order;
import de.zooplus.service.ZooPlusService;

/**
 * The Class ZooPlusController provides REST points to create customer, create
 * order, create payment, get customer balance and get order amount.
 */
@RestController
@RequestMapping("/api")
public class ZooPlusController {

	/** The zoo plus service. */
	@Autowired
	private ZooPlusService zooPlusService;

	/**
	 * Creates the order.
	 *
	 * @param orderId    the order id
	 * @param customerId the customer id
	 * @param amount     the amount
	 * @return the response entity
	 */
	@PostMapping("/createOrder")
	public ResponseEntity<Void> createOrder(@RequestParam(name = "id") String orderId,
			@RequestParam(name = "customerId") String customerId, @RequestParam(name = "amount") String amount) {
		try {
			int oId = Integer.parseInt(orderId);
			int cId = Integer.parseInt(customerId);
			int amnt = Integer.parseInt(amount);
			zooPlusService.createOrder(oId, cId, amnt);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Creates the customer.
	 *
	 * @param customerId the customer id
	 * @param name       the name
	 * @return the response entity
	 */
	@PostMapping("/createCustomer")
	public ResponseEntity<Void> createCustomer(@RequestParam(name = "customerId") String customerId,
			@RequestParam(name = "name") String name) {
		try {
			int cId = Integer.parseInt(customerId);
			zooPlusService.createCustomer(cId, name);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Creates the payment.
	 *
	 * @param orderId the order id
	 * @param amount  the amount
	 * @return the response entity
	 */
	@PutMapping("/addPayment")
	public ResponseEntity<Order> createPayment(@RequestParam(name = "orderId") String orderId,
			@RequestParam(name = "amount") String amount) {
		try {
			int oId = Integer.parseInt(orderId);
			int amnt = Integer.parseInt(amount);
			zooPlusService.createPayment(oId, amnt);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Gets the order balance.
	 *
	 * @param orderId the order id
	 * @return the order balance
	 */
	@GetMapping("/getOrderBalance")
	public ResponseEntity<Integer> getOrderBalance(@RequestParam(name = "orderId") String orderId) {
		Integer retVal = null;
		try {
			int oId = Integer.parseInt(orderId);
			retVal = zooPlusService.getOrderBalance(oId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	/**
	 * Gets the customer balance.
	 *
	 * @param customerId the customer id
	 * @return the customer balance
	 */
	@GetMapping("/getCustomerBalance")
	public ResponseEntity<Integer> getCustomerBalance(@RequestParam(name = "customerId") String customerId) {
		Integer retVal = null;
		try {
			int cId = Integer.parseInt(customerId);
			retVal = zooPlusService.getCustomerBalance(cId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
}
