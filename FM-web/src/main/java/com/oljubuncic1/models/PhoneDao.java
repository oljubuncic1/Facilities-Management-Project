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
	
		
		Session s = template.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.setFlushMode(FlushMode.AUTO);
		
		Integer id = (Integer) s.save(t);
		tx.commit();
		s.close();
		return id;
	}

	@Override
	public Phone read(Integer id)
	{
		return (Phone)template.get(Phone.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Phone t) {
		
		
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

	public Collection<Phone> getByName(List<String> facPhones, Facility f)
	{
		Collection<Phone> c = new HashSet<Phone>();
		
		for(String name:facPhones)
		{
			String name1 = name.toUpperCase();
			
			List<Integer> id = (List<Integer>) template.findByNamedParam("select p.id from Phone p where upper(p.number) = (:n)", "n", name1);
			if(id.size() == 0) // no results
				c.add(read(create(new Phone(1, name, "Support", f))));
			else
				c.add(read(id.get(0)));
		}
		
		
		return c;
	}

}
