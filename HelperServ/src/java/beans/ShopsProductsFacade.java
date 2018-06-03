/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.ShopsProducts;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Настенька
 */
@Stateless
public class ShopsProductsFacade extends AbstractFacade<ShopsProducts> {

    @PersistenceContext(unitName = "HelperServPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShopsProductsFacade() {
        super(ShopsProducts.class);
    }
    
}
