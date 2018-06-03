package entities;

import entities.Products;
import entities.Shops;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-10T22:45:07")
@StaticMetamodel(ShopsProducts.class)
public class ShopsProducts_ { 

    public static volatile SingularAttribute<ShopsProducts, Products> productsId;
    public static volatile SingularAttribute<ShopsProducts, Integer> price;
    public static volatile SingularAttribute<ShopsProducts, Integer> id;
    public static volatile SingularAttribute<ShopsProducts, Shops> shopsId;

}