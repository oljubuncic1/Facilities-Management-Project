package com.oljubuncic1.controllers;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oljubuncic1.entities.Configuration;
import com.oljubuncic1.entities.Facility;
import com.oljubuncic1.models.FacilityDao;



@Controller
@RequestMapping("/uploadFacilities")
public class UploadController {
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(ModelMap model)
	{
		
		
		return "upload";
	}
	
	
	
	@RequestMapping(value="/addConfForm")
	public String addFormConfiguration(ModelMap model)
	{
		
		
		return "configuration";
	}
	
	
	@RequestMapping(value="/addConf")
	public String addConfiguration(@ModelAttribute("Configuration") Configuration c, Map<String, Object> map)
	{
		
		
		return "configuration";
	}

}
