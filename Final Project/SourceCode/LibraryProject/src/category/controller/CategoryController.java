/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category.controller;

import category.model.Category;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author kienanh2903
 */
public class CategoryController {

    private Category category;
    
    public CategoryController() {
        category = new Category();
    }
    public List<Category> getAll() {
        return category.getAll();
    }
    public void loadListCategoryToComboBox(JComboBox jcb, List<Category> list){
        jcb.removeAllItems();
        jcb.addItem("All");
        for(int i=0;i<list.size();i++){
            jcb.addItem(list.get(i).getCat());
        }
    }
}
