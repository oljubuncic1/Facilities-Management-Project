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
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
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
		if(f.getId()!=0)
		fd.create(f);
		return "add";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam int fac_id)
	{
		fd.delete(fac_id);
		return "redirect:/facility/list";
	}
	
	
	
}
