package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject session factory 
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
	
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		//Tri par nom de famille : option 1 dans la base de données directement
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
//		Query<Customer> theQuery = 
//				currentSession.createQuery("from Customer", Customer.class);
		
		// execute query
		List<Customer> customers = theQuery.getResultList();
		
		//Tri par nom de famille : option 2 trier la liste par l'appli (à mettre normalement dans le service)
//		List<Customer> sortedCustomers = customers.stream()
//											.sorted(Comparator.comparing(Customer::getLastName))
//											.collect(Collectors.toList());
		
		//Tri par nom de famille : option 3 trier par javascript (en utilisant une datatable par exemple)
		
		//return results customers non trié
		return customers;
		//return results liste triée
		//return sortedCustomers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save or update s'il existe un id (méthode fournie par hibernate)
		currentSession.saveOrUpdate(theCustomer);	
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// delete object from database using the primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
			
	}

}
