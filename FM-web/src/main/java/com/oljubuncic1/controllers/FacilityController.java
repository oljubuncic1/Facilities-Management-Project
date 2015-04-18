package com.oljubuncic1.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oljubuncic1.entities.CSVConfiguration;
import com.oljubuncic1.entities.Facility;
import com.oljubuncic1.factory.CSVFactory;
import com.oljubuncic1.models.ConfigurationDao;
import com.oljubuncic1.models.FacilityDao;

@Controller
@RequestMapping("/facility")
public class FacilityController 
{
	private FacilityDao fd = new FacilityDao();
	private ConfigurationDao cd = new ConfigurationDao();
	
	
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(ModelMap model)
	{
		
		model.addAttribute("facilitiesList", fd.getAll());
		return "index";
	}
	
	@RequestMapping(value="/single", method = RequestMethod.GET)
	public String singleFacility(@RequestParam int fac_id, ModelMap model)
	{
		
		model.addAttribute("singleFacility", fd.read(fac_id));
		return "single";
	}
	
	@RequestMapping(value="/add")
	public String add(@ModelAttribute("Facility") Facility f, Map<String, Object> map)
	{
		fd.create(f);
		return "redirect:/facility/";
	}
	
	@RequestMapping(value="/addForm")
	public String addForm(@ModelAttribute("Facility") Facility f, Map<String, Object> map)
	{
		Facility.previousID();
		return "add";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam int fac_id)
	{
		fd.delete(fac_id);
		return "redirect:/facility/";
	}
	
	@RequestMapping(value="/updatePost")
	public String updatePost(@ModelAttribute("Facility") Facility f, Map<String, Object> map)
	{
		
		
		fd.update(f);
		Facility.setcurrentID(Facility.maxID + 1);
		return "redirect:/facility/";
	}
	
	@RequestMapping(value="/update")
	public String update(@RequestParam int fac_id, @ModelAttribute("Facility") Facility f, ModelMap model)
	{
		fd.delete(f.getId());
		Facility.setcurrentID(fac_id - 1);
		Facility current = fd.read(fac_id);
		model.addAttribute("facility", current);
		return "update";
	}
	
	
	@RequestMapping(value="/uploadForm")
	public String uploadForm(@ModelAttribute("CSVFactory") CSVFactory fact, ModelMap model)
	{
		
		model.addAttribute("configurations", cd.getAll());
		return "upload";
	}
	
	
	@RequestMapping(value="/upload")
	public String upload(@ModelAttribute("CSVFactory") CSVFactory fact, ModelMap model)
	{
		
		fact.setFile("C:\\Users\\Orhan\\Desktop\\sample.csv");
		fact.setC(cd.read(fact.getcName()));
		//fact.setC(cd.read("conf"));
		//CSVFactory fact = new CSVFactory(cd.read(con_name), "C:\\Users\\Orhan\\Desktop\\sample.csv");
		int facCount = fact.createFacilities(fd);
		model.addAttribute("numberUploaded", Integer.toString(facCount));
		model.addAttribute("configurations", cd.getAll());
		return "upload";
	}
	
	
	@RequestMapping(value="/addConfForm")
	public String addConfForm(@ModelAttribute("CSVConfiguration") CSVConfiguration c, Map<String, Object> map)
	{
		
		
		return "configuration";
	}
	
	
	@RequestMapping(value="/addConf")
	public String addConf(@ModelAttribute("CSVConfiguration") CSVConfiguration c, Map<String, Object> map)
	{
		
		cd.create(c);
		
		return "redirect:/facility/uploadForm";
	}
	
	
	
}
