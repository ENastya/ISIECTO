/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Shops;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Настенька
 */
@Stateless
public class ShopsFacade extends AbstractFacade<Shops> {

    @PersistenceContext(unitName = "HelperServPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShopsFacade() {
        super(Shops.class);
    }
    
}
