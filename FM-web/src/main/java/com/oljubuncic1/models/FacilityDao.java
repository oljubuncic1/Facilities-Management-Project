package com.oljubuncic1.models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oljubuncic1.entities.Address;
import com.oljubuncic1.entities.Facility;


@Repository
@Transactional(readOnly = false)
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
	@Transactional(readOnly = false)
	public Integer create(Facility t)
	{
		//template.setCheckWriteOperations(false);
	
		//template.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		
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
	@Transactional
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
	
	@Transactional
	public Collection<Facility> getAllByWord(String searchString)
	{
		
		Collection<Facility> fac = new HashSet<Facility>();
		//fac.addAll( getAllByParam("name", searchString));
		//fac.addAll( getAllByParam("website", searchString));
		//fac.addAll( getAllByParam("description", searchString));
		//fac.addAll(getAllByParamList("street", searchString));
		
		
		
		
		
		return (Collection<Facility>) fac;
		
		
	}
	
	
	public Collection<Facility> getAllByParam(String param, String searchString)
	{
		String p = "%" + searchString + "%";
		p = p.toUpperCase();
		Object[] params  = {p};
		return (Collection<Facility>) template.find("from Facility where upper(" + param + ") like  ?", params);
	}
	
	public Collection<Facility> getAllByParamList(String searchString)
	{
		String p = "%" + searchString + "%";
		p = p.toUpperCase();
		Object[] params  = {p,p,p,p,p,p,p};
		
		
		List<Integer> l = (List<Integer>) template.find("select distinct f.id from Facility f join f.addresses a join a.city c join c.country cr join f.categories cat"
				+ " where upper(f.name) like ? or upper(f.website) like ? or upper(f.description) like ? "
				+ " or upper(a.street) like ? or upper(c.name) like ? or upper(cr.name) like ? or upper(cat.name) like ?", params);
		
		Collection<Facility> f = makeFacilities(l);
		
		return f;
		
		
	}
	
	
	public Collection<Facility> getAllByMultipleParamList(String name, String category, String address, String number, String country, String city)
	{
		
		String exp = "=";
		
		if(category=="Any" || category=="") category = "%";
		if(city=="Any") city = "%";
		if(country=="Any") country = "%";
		if(name=="") name="%";
		if(address=="") address="%";
		if(number=="") {
			exp = "like";
			number = "%";
		}
		
		name = name.toUpperCase();
		category = category.toUpperCase();
		address = address.toUpperCase();
		city = city.toUpperCase();
		country = country.toUpperCase();
		
		Object[] params  = {name,address,number,city,country,category};
		
		
		List<Integer> l = (List<Integer>) template.find("select distinct f.id from Facility f join f.addresses a join a.city c join c.country cr join f.categories cat"
				+ " where upper(f.name) like ? and upper(a.street) like ? "
				+ "and a.houseNumber " + exp + " ? and upper(c.name) like ? and upper(cr.name) like ? and upper(cat.name) like ?", params);
		
		Collection<Facility> f = makeFacilities(l);
		
		return f;
		
		
	}
	
	
	private Collection<Facility> makeFacilities(List<Integer> l)
	{
		Collection<Facility> c = new ArrayList<Facility>();
		
		for(Integer o : l)
		{
			c.add(read((Integer) o));
			
		}
		
		return c;
	}
	
	

}
