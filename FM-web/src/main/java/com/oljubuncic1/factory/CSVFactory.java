package com.oljubuncic1.factory;

import com.oljubuncic1.entities.CSVConfiguration;
import com.oljubuncic1.entities.Configuration;
import com.oljubuncic1.entities.Facility;
import com.oljubuncic1.models.FacilityDao;



import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVFactory {
	
	
	private String cName;
	private Configuration c;
	private String file;
	
	
	private CSVReader reader = null;
	private int numberAdded;
	
	
	public CSVFactory() {}
	
	public CSVFactory(Configuration conf, String file)
	{
		setC(conf);
		setFile(file);
		numberAdded = 0;
	
	}


	public Configuration getC() {
		return c;
	}


	public void setC(Configuration c) {
		this.c = c;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}
	
	
	public int createFacilities(FacilityDao fd)
	{
		
		// returns number of facilities added
		
		Map<Integer, String> attributes = new HashMap<Integer, String>();
		int numberOfFac = 0;
		try
        {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(file),',');
            String [] nextLine;
            
            
            
          //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
            	int i = 0;
                for(String token : nextLine)
                {
                    attributes.put( Integer.parseInt(((CSVConfiguration) getC()).getData().get(i)), token);
                    i++;
                }
                
                
                fd.create(new Facility(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5), attributes.get(6), attributes.get(7), attributes.get(8), "BH"));
                attributes.clear();
                numberOfFac++;
            }
            
            
            
            
        }
        catch (Exception e) {
            
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                
            }
        }
		
		return numberOfFac;
	}



	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
}
