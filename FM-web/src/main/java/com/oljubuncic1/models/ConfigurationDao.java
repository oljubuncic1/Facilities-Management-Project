package com.oljubuncic1.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oljubuncic1.entities.Configuration;

// data currently dummied by a Map
public class ConfigurationDao implements ICrud<Configuration, String>
{
	private Map<String, Configuration> configurationData;
	
	public ConfigurationDao() {
		super();
		configurationData = new HashMap<String, Configuration>();
		
	}

	@Override
	public Configuration create(Configuration t)
	{
		configurationData.put(t.getName(), t);
		return t;
	}

	@Override
	public Configuration read(String name)
	{
		return configurationData.get(name);
	}

	@Override
	public Configuration update(Configuration t) {
		
		//replace doesn't work
		
		return create(t);
		
	}

	@Override
	public void delete(String name) {
		configurationData.remove(name);
		
	}

	@Override
	public Collection<Configuration> getAll() {
		List<Configuration> f = new ArrayList<Configuration>(configurationData.values());
		
		
		
		Comparator<Configuration> comparator = new Comparator<Configuration>() {
		    public int compare(Configuration f1, Configuration f2) {
		        return f2.getName().compareTo(f1.getName());
		    }
		};

		Collections.sort(f, comparator);
		return f;
		
		
	}
	
}
