package entities;

import entities.Categories;
import entities.ShopsProducts;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-10T22:45:07")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, String> name;
    public static volatile SingularAttribute<Products, Integer> id;
    public static volatile CollectionAttribute<Products, ShopsProducts> shopsProductsCollection;
    public static volatile SingularAttribute<Products, Categories> categoryId;

}