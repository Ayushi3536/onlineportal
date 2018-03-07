package ten.ays.backendProject.dao;

import java.util.List;
import ten.ays.backendProject.dto.Category;

public interface CategoryDAO {

	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	public List<Category> list();
	public Category get(int id);
}
