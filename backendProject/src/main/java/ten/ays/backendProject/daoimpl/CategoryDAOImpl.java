package ten.ays.backendProject.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ten.ays.backendProject.dao.CategoryDAO;
import ten.ays.backendProject.dto.Category;

/*This annotation specifies that the class will provide data access and spring will handle it*/

@Repository("categoryDAO")//name will be same as object name given in pagecontroller
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private static List<Category> categories = new ArrayList<>();
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		String selectActiveCategory = "FROM Category WHERE active = :active";
	
		Query query= sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active",true);
		return query.getResultList();
		
	
	}
	//getting single category based on id
	@Override
	public Category get(int id) {

		/*	for(Category category :categories)
			{
				if(category.getId() == (id))
					return category;
			}
			return null;*/
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
		}
	@Override
	@Transactional
	public boolean add(Category category) {
		try{
			sessionFactory.getCurrentSession().persist(category);
			return true;
			
		}catch(Exception e)
		{e.printStackTrace();
		return false;
		}
		
		
	}
	

	
	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
			
		}catch(Exception e)
		{e.printStackTrace();
		return false;
		}
		
	}
	@Override
	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
			
		}catch(Exception e)
		{e.printStackTrace();
		return false;
		}
		
	}
	}



