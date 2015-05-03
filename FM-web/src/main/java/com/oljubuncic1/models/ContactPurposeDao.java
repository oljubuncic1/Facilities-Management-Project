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

import com.oljubuncic1.entities.ContactPurpose;


@Repository
@Transactional(readOnly = false)
public class ContactPurposeDao implements ICrud<ContactPurpose, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public ContactPurposeDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(ContactPurpose t)
	{
		//template.setCheckWriteOperations(false);
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public ContactPurpose read(Integer id)
	{
		return (ContactPurpose)template.get(ContactPurpose.class, id);
	}

	@Override
	public void update(ContactPurpose t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
	}

	@Override
	@Transactional
	public Collection<ContactPurpose> getAll() {
		List<ContactPurpose> f = template.loadAll(ContactPurpose.class);
		
		
		
		Comparator<ContactPurpose> comparator = new Comparator<ContactPurpose>() {
		    public int compare(ContactPurpose f1, ContactPurpose f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

}
