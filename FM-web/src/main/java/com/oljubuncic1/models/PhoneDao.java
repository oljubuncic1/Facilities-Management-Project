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

import com.oljubuncic1.entities.Phone;


@Repository
@Transactional(readOnly = false)
public class PhoneDao implements ICrud<Phone, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public PhoneDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(Phone t)
	{
		//template.setCheckWriteOperations(false);
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public Phone read(Integer id)
	{
		return (Phone)template.get(Phone.class, id);
	}

	@Override
	public void update(Phone t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
	}

	@Override
	@Transactional
	public Collection<Phone> getAll() {
		List<Phone> f = template.loadAll(Phone.class);
		
		
		
		Comparator<Phone> comparator = new Comparator<Phone>() {
		    public int compare(Phone f1, Phone f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

}
