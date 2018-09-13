/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category.dao;

import category.model.Category;
import java.util.List;

/**
 *
 * @author Linh
 */
public interface CategoryDao {
    List<Category> getAll();
}
