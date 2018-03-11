package net.ays.onlineportal.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView index()
	{
		
		ModelAndView mv = new ModelAndView("page");/*page is the logical name of view ,it will use page .jsp file inside WEB-INF/views/ folder ,we will resolve the name using Internal Resource View Resolver here by adding configuration in spring bean configuration file(dispatcher-servlet.xml)*/
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
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


}
