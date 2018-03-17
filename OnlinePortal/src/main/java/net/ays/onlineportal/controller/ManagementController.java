package net.ays.onlineportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.ays.backendPortal.dao.CategoryDAO;
import net.ays.backendPortal.dao.ProductDAO;
import net.ays.backendPortal.dto.Category;
import net.ays.backendPortal.dto.Product;
import net.ays.onlineportal.util.FileUtil;
import net.ays.onlineportal.validator.ProductValidator;



@Controller
@RequestMapping("/manage")
public class ManagementController {

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
		
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;		
    
	
	@RequestMapping(value = "/product" , method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="operation",required= false)String operation){		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
        Product nProduct = new Product();
		
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);

		if(operation !=null)
		{
			if(operation.equals("product"))
			{
				mv.addObject("message" ,"Product Submitted successfully"); 
			}
			else if (operation.equals("category")) {
				mv.addObject("message", "Category submitted successfully!");
			}
		}
		return mv;
		
	}
	@RequestMapping(value = "/category", method=RequestMethod.POST)
	public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {					
		categoryDAO.add(mCategory);		
		return "redirect:" + request.getHeader("Referer") + "?operation=category";
	}
			
	
	@ModelAttribute("category")
	public Category modelCategory() {
		return new Category();
	}
	@ModelAttribute("categories") 
	public List<Category> getCategories() {
		return categoryDAO.list();
				

	}

	//handling product submission
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String handleproductSubmission(@Valid @ModelAttribute("product") Product product,BindingResult results, Model model ,HttpServletRequest request)
	{
		// mandatory file upload check
		if(product.getId() == 0) {
			new ProductValidator().validate(product, results);
		}
		else {
			// edit check only when the file has been selected
			if(!product.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(product, results);
			}			
		}
		if(results.hasErrors()) {
			model.addAttribute("title", "Manage Product!");
			model.addAttribute("userClickManageProduct",true);
			return "page";
		}
	
		logger.info(product.toString());
		if(product.getId() == 0) 
			productDAO.add(product);
			else
				productDAO.update(product);
		
		 //upload the file
		 if(!product.getFile().getOriginalFilename().equals("") ){
			FileUtil.uploadFile(request, product.getFile(), product.getCode()); 
		 }
		
		return "redirect:/manage/product?operation=product";
	}	
	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.GET)
	@ResponseBody
	public String managePostProductActivation(@PathVariable int id) {		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDAO.update(product);		
		return (isActive)? "Product Dectivated Successfully!": "Product Activated Successfully";
	}
	@RequestMapping("/{id}/product")
	public ModelAndView manageProductEdit(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		// Product nProduct = new Product();		
		mv.addObject("product", productDAO.get(id));

			
		return mv;
		
	}
	
	
}

	
