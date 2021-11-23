package de.zooplus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Order keeps Order model.
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id
	@Column(unique = true, nullable = false)
	private Integer id;
	
	/** The customer. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	/** The amount. */
	@Column
	private Integer amount = 0;
	
	/**
	 * Instantiates a new order.
	 */
	public Order() {
		
	}
	
	/**
	 * Instantiates a new order.
	 *
	 * @param id the id
	 * @param customer the customer
	 * @param amount the amount
	 */
	public Order(Integer id, Customer customer, Integer amount) {
		this.id = id;
		this.customer = customer;
		this.amount = amount;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
