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

import com.oljubuncic1.entities.City;
import com.oljubuncic1.entities.Country;
import com.oljubuncic1.entities.Facility;


@Repository
@Transactional(readOnly = false)
public class CityDao implements ICrud<City, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public CityDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(City t)
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
	public City read(Integer id)
	{
		return (City)template.get(City.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(City t) {
		
		
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
	public Collection<City> getAll() {
		List<City> f = template.loadAll(City.class);
		
		
		
		Comparator<City> comparator = new Comparator<City>() {
		    public int compare(City f1, City f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}
	
	
	@Transactional
	public Collection<String> getByCountry(String country)
	{
		String p = country;
		p = p.toUpperCase();
		Object[] params  = {p};
		
		
		List<String> l = (List<String>) template.find("select distinct c.name from City c join c.country cr"
				+ " where upper(cr.name) = ?", params);
		
		return l;
	}
	
	public Collection<City> getByName(List<String> cityNames, Collection<Country> countryNames)
	{
		Collection<City> c = new HashSet<City>();
		
		int i = 0;
		for(String name:cityNames)
		{
			if(name.isEmpty()) continue;
			String name1 = name.toUpperCase();
			
			String crname = ((Country) countryNames.toArray()[i]).getName();
			crname = crname.toUpperCase();
			Object[] params  = {name1, crname};
			List<Integer> id = (List<Integer>) template.find("select c.id from City c join c.country cr where upper(c.name) = ? and upper(cr.name) = ?", params);
			if(id.size() == 0) // no results
				c.add(read(create(new City(1, (Country) countryNames.toArray()[i], name))));
			else
				c.add(read(id.get(0)));
			
			i++;
		}
		
		
		return c;
	}

}
