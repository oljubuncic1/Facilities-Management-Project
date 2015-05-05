package com.oljubuncic1.models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oljubuncic1.entities.City;


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
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public City read(Integer id)
	{
		return (City)template.get(City.class, id);
	}

	@Override
	public void update(City t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
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

}
