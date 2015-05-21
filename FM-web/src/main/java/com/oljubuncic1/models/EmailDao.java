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

import com.oljubuncic1.entities.Category;
import com.oljubuncic1.entities.Email;
import com.oljubuncic1.entities.Facility;


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
	
		
		Session s = template.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.setFlushMode(FlushMode.AUTO);
		
		Integer id = (Integer) s.save(t);
		tx.commit();
		s.close();
		return id;
	}

	@Override
	public Email read(Integer id)
	{
		return (Email)template.get(Email.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Email t) {
		
		
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

	public Collection<Email> getByName(List<String> facEmails, Facility f)
	{
		Collection<Email> c = new HashSet<Email>();
		
		for(String name:facEmails)
		{
			if(name.isEmpty()) continue;
			String name1 = name.toUpperCase();
			
			List<Integer> id = (List<Integer>) template.findByNamedParam("select e.id from Email e where upper(e.address) = (:n)", "n", name1);
			if(id.size() == 0) // no results
				c.add(read(create(new Email(1,  name, f))));
			else
				c.add(read(id.get(0)));
		}
		
		
		return c;
	}

}
