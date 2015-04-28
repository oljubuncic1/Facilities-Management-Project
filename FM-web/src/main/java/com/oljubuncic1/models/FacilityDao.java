package com.oljubuncic1.models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oljubuncic1.entities.Facility;


public class FacilityDao implements ICrud<Facility, Integer>
{
	private HibernateTemplate template;
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public FacilityDao() {
		super();
		
		
	}

	@Override
	public Integer create(Facility t)
	{
		return (Integer)template.save(t);
	}

	@Override
	public Facility read(Integer id)
	{
		return (Facility)template.get(Facility.class, id);
	}

	@Override
	public void update(Facility t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
	}

	@Override
	public Collection<Facility> getAll() {
		List<Facility> f = template.loadAll(Facility.class);
		
		
		
		Comparator<Facility> comparator = new Comparator<Facility>() {
		    public int compare(Facility f1, Facility f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

}
