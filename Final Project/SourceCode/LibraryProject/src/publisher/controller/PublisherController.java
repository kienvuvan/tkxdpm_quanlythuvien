/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher.controller;

import java.util.List;
import javax.swing.JComboBox;
import publisher.model.Publisher;

/**
 *
 * @author kienanh2903
 */
public class PublisherController {
    
    private Publisher publisher;
            
    public PublisherController() {
        publisher = new Publisher();
    }
    
    public List<Publisher> getAll() {
        return publisher.getAll();
    }
    
    public void loadListPublisherToComboBox(JComboBox jcb, List<Publisher> list){
        jcb.removeAllItems();
        jcb.addItem("All");
        for(int i=0;i<list.size();i++){
            jcb.addItem(list.get(i).getPublisher());
        }
        jcb.setSelectedItem("All");
    }
}
