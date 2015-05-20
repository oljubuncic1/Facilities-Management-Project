package com.oljubuncic1.models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oljubuncic1.entities.Country;
import com.oljubuncic1.entities.Facility;


@Repository
@Transactional(readOnly = false)
public class CountryDao implements ICrud<Country, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public CountryDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(Country t)
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
	public Country read(Integer id)
	{
		return (Country)template.get(Country.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Country t) {
		
		
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
	public Collection<Country> getAll() {
		List<Country> f = template.loadAll(Country.class);
		
		
		
		Comparator<Country> comparator = new Comparator<Country>() {
		    public int compare(Country f1, Country f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}
	
	public Collection<Country> getByName(List<String> names)
	{
		Collection<Country> c = new HashSet<Country>();
		
		for(String name:names)
		{
			String name1 = name.toUpperCase();
			
			List<Integer> id = (List<Integer>) template.findByNamedParam("select c.id from Country c where upper(c.name) = (:n)", "n", name1);
			if(id.size() == 0) // no results
				c.add(read(create(new Country(1, name))));
			else
				c.add(read(id.get(0)));
		}
		
		
		return c;
	}

}
