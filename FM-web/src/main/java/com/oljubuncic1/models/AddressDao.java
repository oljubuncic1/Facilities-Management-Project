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

import com.oljubuncic1.entities.Address;


@Repository
@Transactional(readOnly = false)
public class AddressDao implements ICrud<Address, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public AddressDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(Address t)
	{
		//template.setCheckWriteOperations(false);
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public Address read(Integer id)
	{
		return (Address)template.get(Address.class, id);
	}

	@Override
	public void update(Address t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
	}

	@Override
	@Transactional
	public Collection<Address> getAll() {
		List<Address> f = template.loadAll(Address.class);
		
		
		
		Comparator<Address> comparator = new Comparator<Address>() {
		    public int compare(Address f1, Address f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

}

