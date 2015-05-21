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

import com.oljubuncic1.entities.Address;
import com.oljubuncic1.entities.Category;
import com.oljubuncic1.entities.Country;
import com.oljubuncic1.entities.Facility;


@Repository
@Transactional(readOnly = false)
public class CategoryDao implements ICrud<Category, Integer>
{
	private HibernateTemplate template;
	
	
	
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	    
	}
	
	public CategoryDao() {
		super();
		
		
	}

	@Override
	@Transactional(readOnly = false)
	public Integer create(Category t)
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
	public Category read(Integer id)
	{
		return (Category)template.get(Category.class, id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Category t) {
		
		
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
	public Collection<Category> getAll() {
		List<Category> f = template.loadAll(Category.class);
		
		
		
		Comparator<Category> comparator = new Comparator<Category>() {
		    public int compare(Category f1, Category f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

	public Collection<Category> getByName(List<String> facCategories)
	{
			Collection<Category> c = new HashSet<Category>();
		
		for(String name:facCategories)
		{
			if(name.isEmpty()) continue;
			String name1 = name.toUpperCase();
			
			List<Integer> id = (List<Integer>) template.findByNamedParam("select c.id from Category c where upper(c.name) = (:n)", "n", name1);
			if(id.size() == 0) // no results
				c.add(read(create(new Category(1, name))));
			else
				c.add(read(id.get(0)));
		}
		
		
		return c;
	}

}

