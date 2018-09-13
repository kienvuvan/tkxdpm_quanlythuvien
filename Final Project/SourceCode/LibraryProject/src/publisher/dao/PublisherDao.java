/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher.dao;

import java.util.List;
import publisher.model.Publisher;

/**
 *
 * @author Linh
 */
public interface PublisherDao {
    List<Publisher> getAll();
}
