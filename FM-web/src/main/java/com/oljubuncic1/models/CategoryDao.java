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

import com.oljubuncic1.entities.Category;


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
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
		return (Integer)template.save(t);
	}

	@Override
	public Category read(Integer id)
	{
		return (Category)template.get(Category.class, id);
	}

	@Override
	public void update(Category t) {
		
		
		
		 template.update(t);
		
	}

	@Override
	public void delete(Integer id) {
		template.delete(id);
		
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

}

