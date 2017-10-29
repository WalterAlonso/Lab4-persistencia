package com.losalpes.entities;

import com.losalpes.entities.Ciudad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-28T19:21:45")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile SingularAttribute<Pais, Long> id;
    public static volatile SingularAttribute<Pais, String> nombre;
    public static volatile ListAttribute<Pais, Ciudad> ciudades;

}