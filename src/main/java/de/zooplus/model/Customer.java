package de.zooplus.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class Customer keeps customer model.
 */
@Entity
@Table(name = "customers")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "orders" })
public class Customer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(unique = true, nullable = false)
	private int id;

	/** The name. */
	@Column
	private String name;

	/** The balance. */
	@Column
	private int balance;

	/** The orders. */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Order> orders = new HashSet<>();

	/**
	 * Instantiates a new customer.
	 */
	public Customer() {

	}

	/**
	 * Instantiates a new customer.
	 *
	 * @param id   the id
	 * @param name the name
	 */
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
		this.balance = 0;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return this.orders;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
		for (Order b : this.orders) {
			b.setCustomer(this);
		}
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

}
