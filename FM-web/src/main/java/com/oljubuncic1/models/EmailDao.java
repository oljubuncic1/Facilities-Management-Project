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

import com.oljubuncic1.entities.Email;


@Repository
@Transactional(readOnly = false)
public class EmailDao implements ICrud<Email, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public EmailDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(Email t)
	{
		//template.setCheckWriteOperations(false);
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public Email read(Integer id)
	{
		return (Email)template.get(Email.class, id);
	}

	@Override
	public void update(Email t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
	}

	@Override
	@Transactional
	public Collection<Email> getAll() {
		List<Email> f = template.loadAll(Email.class);
		
		
		
		Comparator<Email> comparator = new Comparator<Email>() {
		    public int compare(Email f1, Email f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

}
