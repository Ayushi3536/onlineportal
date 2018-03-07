package ten.ays.backendProject.test;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ten.ays.backendProject.dao.CategoryDAO;
import ten.ays.backendProject.dto.Category;

public class CategoryTestCase {
private static AnnotationConfigApplicationContext context;

private static CategoryDAO categoryDAO;

private Category category;

@BeforeClass
public static void init()
{
	
	context = new AnnotationConfigApplicationContext();
	context.scan("net.ays.backendProject.dao");
	context.scan("net.ays.backendProject.daoimpl");
	context.scan("net.ays.backendProject.config"); 
	context.scan("net.ays.backendProject");
	
	context.refresh();
	categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
//@Test
public void testAddCategory()
{
category = new Category();
category.setName("Television");
category.setDescription("Thi!");
category.setImageURL("CAT_1.png");
assertEquals("Successfully added",true,categoryDAO.add(category));


}
//@Test
public void testGetCategory()
{
category = categoryDAO.get(3);	
assertEquals("Successfully fetched a single category from a table","Television",category.getName());

}
//@Test
public void testUpdateCategory()
{
category = categoryDAO.get(3);	
category.setName("TV");
assertEquals("Successfully updated a single category from a table",true,categoryDAO.update(category));

}
//@Test
public void testDeleteCategory()
{
category = categoryDAO.get(3);	
assertEquals("Successfully deleted a single category from a table",true,categoryDAO.delete(category));

}
@Test
public void testListCategory()
{
//category = categoryDAO.get(3);	
//assertEquals("Successfully fetched LIST OF ACTIVE category from a table",1,categoryDAO.list().size());

}
@Test
public void testCRUDCategory()
{
	category = new Category();
	category.setName("Laptop");
	category.setDescription("Thi!");
	category.setImageURL("CAT_1.png");
	assertEquals("Successfully added",true,categoryDAO.add(category));

	category = new Category();
	category.setName("Mobile");
	category.setDescription("Thi!");
	category.setImageURL("CAT_1.png");
	assertEquals("Successfully added",true,categoryDAO.add(category));
	category = categoryDAO.get(3);	
	category.setName("TV");
	assertEquals("Successfully updated a single category from a table",true,categoryDAO.update(category));
	assertEquals("Successfully deleted a single category from a table",true,categoryDAO.delete(category));
//	assertEquals("Successfully fetched LIST OF ACTIVE category from a table",2,categoryDAO.list().size());


	
}
}
