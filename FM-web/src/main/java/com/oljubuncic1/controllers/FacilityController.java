package com.oljubuncic1.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oljubuncic1.entities.Facility;
import com.oljubuncic1.models.FacilityDao;

@Controller
@RequestMapping("/facility")
public class FacilityController 
{
	private FacilityDao fd = new FacilityDao();
	
	
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
	
	
	
}
