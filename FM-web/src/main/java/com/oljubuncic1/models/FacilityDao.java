package com.oljubuncic1.models;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oljubuncic1.entities.Facility;

// data currently dummied by a Map
public class FacilityDao implements ICrud<Facility, Integer>
{
	private Map<Integer, Facility> facilityData;
	
	public FacilityDao() {
		super();
		facilityData = new HashMap<Integer, Facility>();
		
	}

	@Override
	public Facility create(Facility t)
	{
		facilityData.put(t.getId(), t);
		return t;
	}

	@Override
	public Facility read(Integer id)
	{
		return facilityData.get(id);
	}

	@Override
	public Facility update(Facility t) {
		
		//replace doesn't work
		
		return create(t);
		
	}

	@Override
	public void delete(Integer id) {
		facilityData.remove(id);
		
	}

	@Override
	public Collection<Facility> getAll() {
		List<Facility> f = new ArrayList<Facility>(facilityData.values());
		
		
		
		Comparator<Facility> comparator = new Comparator<Facility>() {
		    public int compare(Facility f1, Facility f2) {
		        return f2.getId() - f1.getId();
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}

}
