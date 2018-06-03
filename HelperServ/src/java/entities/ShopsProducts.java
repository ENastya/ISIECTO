/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Настенька
 */
@Entity
@Table(name = "shops_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopsProducts.findAll", query = "SELECT s FROM ShopsProducts s")
    , @NamedQuery(name = "ShopsProducts.findById", query = "SELECT s FROM ShopsProducts s WHERE s.id = :id")
    , @NamedQuery(name = "ShopsProducts.findByPrice", query = "SELECT s FROM ShopsProducts s WHERE s.price = :price")})
public class ShopsProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @JoinColumn(name = "shops_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopsId;
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productsId;

    public ShopsProducts() {
    }

    public ShopsProducts(Integer id) {
        this.id = id;
    }

    public ShopsProducts(Integer id, int price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Shops getShopsId() {
        return shopsId;
    }

    public void setShopsId(Shops shopsId) {
        this.shopsId = shopsId;
    }

    public Products getProductsId() {
        return productsId;
    }

    public void setProductsId(Products productsId) {
        this.productsId = productsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopsProducts)) {
            return false;
        }
        ShopsProducts other = (ShopsProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ShopsProducts[ id=" + id + " ]";
    }
    
}
