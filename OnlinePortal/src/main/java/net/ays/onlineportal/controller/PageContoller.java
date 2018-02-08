package net.ays.onlineportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.ays.backendProject.dao.CategoryDAO;

@Controller   /*This controller will have all the request mapping */
public class PageContoller {
	
	@Autowired/*It will take care of creating instance of CategoryDAO*/
	private CategoryDAO categoryDAO;
	
	/*HandlerMapper*/
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		
		mv.addObject("title","Make your Home beautiful");	
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("LoadHome",true);
		
		return mv;
	}
	@RequestMapping(value={"/about"})
	public ModelAndView about()
	{
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		
		mv.addObject("title","About Us");	
		mv.addObject("LoadAbout",true);
		
		return mv;
	}
	@RequestMapping(value={"/listProduct"})
	public ModelAndView listProduct()
	{
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		
		mv.addObject("title","Your Product");	
		mv.addObject("LoadListProduct",true);
		
		return mv;
	}
	@RequestMapping(value={"/contact"})
	public ModelAndView contact()
	{
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		
		mv.addObject("title","Contact Us");	
		mv.addObject("LoadContact",true);
		
		return mv;
	}
	

}
