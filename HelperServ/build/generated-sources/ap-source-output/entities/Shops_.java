package entities;

import entities.ShopsProducts;
import entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-10T22:45:07")
@StaticMetamodel(Shops.class)
public class Shops_ { 

    public static volatile SingularAttribute<Shops, String> name;
    public static volatile SingularAttribute<Shops, Integer> id;
    public static volatile SingularAttribute<Shops, String> place;
    public static volatile SingularAttribute<Shops, Users> userId;
    public static volatile CollectionAttribute<Shops, ShopsProducts> shopsProductsCollection;

}