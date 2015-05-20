package com.oljubuncic1.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;









import java.util.Set;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oljubuncic1.entities.Address;
import com.oljubuncic1.entities.CSVConfiguration;
import com.oljubuncic1.entities.Category;
import com.oljubuncic1.entities.City;
import com.oljubuncic1.entities.Country;
import com.oljubuncic1.entities.Email;
import com.oljubuncic1.entities.Facility;
import com.oljubuncic1.entities.Phone;
import com.oljubuncic1.factory.CSVFactory;
import com.oljubuncic1.models.AddressDao;
import com.oljubuncic1.models.CategoryDao;
import com.oljubuncic1.models.CityDao;
import com.oljubuncic1.models.ConfigurationDao;
import com.oljubuncic1.models.CountryDao;
import com.oljubuncic1.models.EmailDao;
import com.oljubuncic1.models.FacilityDao;
import com.oljubuncic1.models.PhoneDao;

@Controller
@RequestMapping("/facility")
public class FacilityController 
{
	//private FacilityDao fd = new FacilityDao();
	private ConfigurationDao cd = new ConfigurationDao();
	
	Resource r =new ClassPathResource("/com/oljubuncic1/controllers/applicationContext.xml");  
    BeanFactory factory=new XmlBeanFactory(r);  
      
    FacilityDao fd =(FacilityDao)factory.getBean("facDao");
    AddressDao ad = (AddressDao)factory.getBean("addressDao");
    CityDao cityd = (CityDao)factory.getBean("cityDao");
    CountryDao countryd = (CountryDao)factory.getBean("countryDao");
    CategoryDao categd = (CategoryDao)factory.getBean("categoryDao");
    PhoneDao phoned = (PhoneDao)factory.getBean("phoneDao");
    EmailDao emaild = (EmailDao)factory.getBean("emailDao");
	
	
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(ModelMap model)
	{
		
		model.addAttribute("facilitiesList", fd.getAll());
		model.addAttribute("countriesList", countryd.getAll());
		model.addAttribute("categoriesList", categd.getAll());
		return "index";
	}
	
	@RequestMapping(value="/single", method = RequestMethod.GET)
	public String singleFacility(@RequestParam int fac_id, ModelMap model)
	{
		
		model.addAttribute("singleFacility", fd.read(fac_id));
		return "single";
	}
	
	@RequestMapping(value="/add")
	public String add(@RequestParam("name") String facName, @RequestParam("website") String facWebsite, @RequestParam("street") List<String> facStreets,
			@RequestParam("number") List<String> facNumbers, @RequestParam("postal_code") List<String> facCodes,
			@RequestParam("city") List<String> facCities, @RequestParam("country") List<String> facCountries,
			@RequestParam("phone") List<String> facPhones, @RequestParam("email") List<String> facEmails,
			@RequestParam("category") List<String> facCategories, @RequestParam("description") String facDesc, Map<String, Object> map, ModelMap model)
	{
		
		//fd.create(f);
		//fd.create(new Facility(123, "ime1", "web1", "desc1"));
		//cityd.create(new City(1, new Country(1, "Drzava1"), "Grad1"));
		
		Integer id= fd.create(new Facility(1, facName, facDesc, facWebsite));
		Facility f = fd.read(id);
		
		
		
		Collection<Country> countries = countryd.getByName(facCountries);
		Collection<City> cities = cityd.getByName(facCities, countries);
		Collection<Address> addresses = ad.getByName(facStreets, facNumbers, facCodes,  cities,  countries);
		Collection<Category> categories = categd.getByName(facCategories);
		Collection<Email> emails = emaild.getByName(facEmails, f);
		Collection<Phone> phones = phoned.getByName(facPhones, f);
		
		
		fd.update(new Facility(id, facName, facDesc, facWebsite, (Set<Category>) categories, (Set<Phone>) phones, (Set<Email>) emails, (Set<Address>) addresses));
		
		
		return "redirect:/facility/";
		
	}
	
	@RequestMapping(value="/addForm")
	public String addForm(@ModelAttribute("Facility") Facility f, Map<String, Object> map)
	{
		
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
		
		return "redirect:/facility/";
	}
	
	@RequestMapping(value="/update")
	public String update(@RequestParam int fac_id, @ModelAttribute("Facility") Facility f, ModelMap model)
	{
		fd.delete(f.getId());
		
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
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("cName") String cName, ModelMap model)
	{
		
		//fact.setFile("C:\\Users\\Orhan\\Desktop\\sample.csv");
		//fact.setC(cd.read(cName));
		//fact.setC(cd.read("conf"));
		
		
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("fac.csv")));
                stream.write(bytes);
                stream.close();
            }
            catch(Exception e) {}
            
		}
		CSVFactory fact = new CSVFactory(cd.read(cName), "fac.csv");
                
        
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
	
	@RequestMapping(value="/search/{searchString}")
	public String searchByParameter(@PathVariable String searchString, ModelMap model)
	{
		
		Collection<Facility> n = fd.getAllByParamList(searchString);
		
		model.clear();
		model.addAttribute("countriesList", countryd.getAll());
		model.addAttribute("categoriesList", categd.getAll());
		model.addAttribute("numberOfFac", n.size());
		model.addAttribute("facilitiesList1", n);
		
		return "search";
	}
	
	
	@RequestMapping(value="/country/{selectedCountry}")
	public @ResponseBody Collection<String> populateCities(@PathVariable String selectedCountry, ModelMap model)
	{
		
		Collection<String> cities = cityd.getByCountry(selectedCountry);
		
		return cities;
	}
	
	
	@RequestMapping(value="/advancedSearch")
	public String advancedSearch(@RequestParam("facName") String facName, @RequestParam("catName") String catName,
			@RequestParam("addrName") String addrName, @RequestParam("num") String num,
			@RequestParam("countryName") String countryName, @RequestParam("cityName") String cityName, ModelMap model)
	{
		
		if(facName == null) facName = "";
		if(catName.compareTo("Any") == 0) catName = "Any";
		if(countryName.compareTo("Any") == 0) countryName = "Any";
		if(cityName.compareTo("Any") == 0) cityName = "Any";
		if(addrName == null) addrName = "";
		if(num == null) num = "";
		
		
		Collection<Facility> n = fd.getAllByMultipleParamList(facName, catName, addrName, num, countryName, cityName);
		
		model.clear();
		model.addAttribute("countriesList", countryd.getAll());
		model.addAttribute("categoriesList", categd.getAll());
		model.addAttribute("numberOfFac", n.size());
		model.addAttribute("facilitiesList2", n);
		return "advancedSearch";
	}
	
	
	
}
