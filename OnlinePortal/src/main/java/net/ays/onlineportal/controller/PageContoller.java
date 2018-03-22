package net.ays.onlineportal.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.ays.backendPortal.dao.CategoryDAO;
import net.ays.backendPortal.dao.ProductDAO;
import net.ays.backendPortal.dto.Category;
import net.ays.backendPortal.dto.Product;
import net.ays.onlineportal.exception.ProductNotFoundException;



@Controller   /*This controller will have all the request mapping */
public class PageContoller {
	
	private static final Logger logger = LoggerFactory.getLogger(PageContoller.class);
	
	
	
    @Autowired /*It will take care of creating instance of CategoryDAO*/
	private CategoryDAO categoryDAO;
    
    @Autowired
    private ProductDAO productDAO;
	
    /*HandlerMapper*/
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index(@RequestParam(name="logout",required=false)String logout)
	{
		
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		mv.addObject("title","Make your Home beautiful");	
		mv.addObject("categories",categoryDAO.list());
		if(logout!=null) {
			mv.addObject("message", "You have successfully logged out!");			
		}
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
	/*methods to load category dynamically*/
	@RequestMapping(value={"/show/all/products"})
	public ModelAndView showAllProducts()
	{
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		
		mv.addObject("title","Allproducts ");	
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("LoadAllProducts",true);
		
		return mv;
	}
	@RequestMapping(value={"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		
		//categoryDAO to fetch a single category
		Category category = null;
     	category=categoryDAO.get(id);
		mv.addObject("title",category.getName());	
		mv.addObject("categories",null);//categoryDAO.list());
		mv.addObject("category",category);
		
		mv.addObject("LoadclickProducts",true);
		
		return mv;
	}
	@RequestMapping(value = "/show/{id}/product") 
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
	  if(product == null)
		  throw new ProductNotFoundException();
		
		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
	
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProduct", true);
		
		
		return mv;
		
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,
			@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv= new ModelAndView("login");
		mv.addObject("title", "Login");
		if(error!=null) {
			mv.addObject("message", "Username and Password is invalid!");
		}
		if(logout!=null) {
			mv.addObject("logout", "You have logged out successfully!");
		}
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}	
	
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Aha! Caught You.");		
		mv.addObject("errorDescription", "You are not authorized to view this page!");		
		mv.addObject("title", "403 Access Denied");		
		return mv;
	}	


}
