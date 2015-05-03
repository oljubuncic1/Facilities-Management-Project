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

import com.oljubuncic1.entities.Country;


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
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public Country read(Integer id)
	{
		return (Country)template.get(Country.class, id);
	}

	@Override
	public void update(Country t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
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

}
