package com.oljubuncic1.models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oljubuncic1.entities.Address;
import com.oljubuncic1.entities.City;
import com.oljubuncic1.entities.Country;
import com.oljubuncic1.entities.Facility;


@Repository
@Transactional(readOnly = false)
public class AddressDao implements ICrud<Address, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public AddressDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(Address t)
	{
		//template.setCheckWriteOperations(false);
	
		
		Session s = template.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.setFlushMode(FlushMode.AUTO);
		
		Integer id = (Integer) s.save(t);
		tx.commit();
		s.close();
		return id;
	}

	@Override
	public Address read(Integer id)
	{
		return (Address)template.get(Address.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Address t) {
		
		
		Session s = template.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.setFlushMode(FlushMode.AUTO);
		s.update(t);
		tx.commit();
		s.close();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		Session s = template.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.setFlushMode(FlushMode.AUTO);
		s.delete(read(id));
		tx.commit();
		s.close();
		
	}

	@Override
	@Transactional
	public Collection<Address> getAll() {
		List<Address> f = template.loadAll(Address.class);
		
		
		
		Comparator<Address> comparator = new Comparator<Address>() {
		    public int compare(Address f1, Address f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}
	
	public Collection<Address> getByName(List<String> streets, List<String> numbers, List<String> codes, Collection<City> cities, Collection<Country> countries)
	{
		Collection<Address> c = new HashSet<Address>();
		
		
		for(int i = 0; i< streets.size();i++)
		{
			String street1 = streets.get(i).toUpperCase();
			String number1 = numbers.get(i).toUpperCase();
			String code1 = codes.get(i).toUpperCase();
			String city1 = ((City)cities.toArray()[i]).getName().toUpperCase();
			String country1 = ((Country)countries.toArray()[i]).getName().toUpperCase();
			
			Object[] params  = {street1, number1, code1, city1, country1};
			List<Integer> id = (List<Integer>) template.find("select a.id from Address a join a.city c join c.country cr where upper(a.street) = ? and upper(a.houseNumber) = ? and upper(a.postalCode) = ? and upper(c.name) = ? and upper(cr.name) = ?", params);
			if(id.size() == 0) // no results
				c.add(read(create(new Address(1, (City)cities.toArray()[i], numbers.get(i), streets.get(i), codes.get(i)))));
			else
				c.add(read(id.get(0)));
			
			
		}
		
		
		return c;
	}

}

