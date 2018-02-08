package net.ays.backendProject.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.ays.backendProject.dao.CategoryDAO;
import net.ays.backendProject.dto.Category;

/*This annotation specifies that the class will provide data access and spring will handle it*/

@Repository("categoryDAO")//name will be same as object name given in pagecontroller
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	static{
		Category category1 = new Category();
		category1.setId("1");
		category1.setName("Lamp");
		category1.setDescription("Antique");
		
		Category category2 = new Category();
		category2.setId("2");
		category2.setName("Lap");
		category2.setDescription("Antique");
		
		Category category3 = new Category();
		category3.setId("3");
		category3.setName("Lamp");
		category3.setDescription("Antique");
		
		
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		
	}
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		
		return categories;
	}

}
