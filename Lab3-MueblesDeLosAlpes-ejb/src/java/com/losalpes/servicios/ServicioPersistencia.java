/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioPersistenciaMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.servicios;

import com.losalpes.entities.Ciudad;
import com.losalpes.entities.Pais;
import com.losalpes.entities.Usuario;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.losalpes.entities.*;
import java.util.Date;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.persistence.Query;

/**
 * Implementación de los servicios de persistencia
 */
@Stateless
public class ServicioPersistencia implements IServicioPersistenciaMockLocal, IServicioPersistenciaMockRemote, Serializable {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void initialize() {
        //crear ciudades
        List listPais = findAll(Pais.class);
        Ciudad bog = new Ciudad("Bogotá");
        Ciudad ny = new Ciudad("New York");
        if (listPais.isEmpty()) {

            List list = findAll(Ciudad.class);
            if (list.isEmpty()) { 
                /* --Unidirectional mapping
                ArrayList<Ciudad> array = new ArrayList<Ciudad>();
                array.add(bog);
                array.add(ny);
                array.add(new Ciudad("Cartagena"));
                Pais colombia = new Pais("Colombia", array);                
                create(colombia);
                
                ArrayList<Ciudad> array2 = new ArrayList<Ciudad>();
                array2.add(new Ciudad("Atlanta"));
                array2.add(new Ciudad("Chicago"));
                array2.add(new Ciudad("Miami"));
                array2.add(ny);
                array2.add(new Ciudad("Washington D.C"));
                Pais usa = new Pais("Estados Unidos", array2);
                create(usa);
                
                 ArrayList<Ciudad> array3 = new ArrayList<Ciudad>();
                array3.add(new Ciudad("Cambridge"));
                array3.add(new Ciudad("Canterbury"));
                array3.add(new Ciudad("Liverpool"));
                array3.add(new Ciudad("Manchester"));
                array3.add(new Ciudad("Oxford"));

                Pais ing = new Pais("Inglaterra", array3);
                */
                
                //Bidirecctional mapping pais - ciudad (los dos lados deben sincronizar)
                Ciudad cartagena = new Ciudad("Cartagena");
                Pais colombia = new Pais("Colombia", new ArrayList<Ciudad>());
                bog.setPais(colombia);
                ny.setPais(colombia);                
                cartagena.setPais(colombia);
                colombia.getCiudades().add(bog);
                colombia.getCiudades().add(ny);
                colombia.getCiudades().add(cartagena);
                create(colombia);
                
                Pais usa = new Pais("Estados Unidos", new ArrayList<Ciudad>());                
                Ciudad at = new Ciudad("Atlanta");                
                Ciudad ch = new Ciudad("Chicago");
                Ciudad mi = new Ciudad("Miami");
                at.setPais(usa);
                ch.setPais(usa);
                mi.setPais(usa);
                usa.getCiudades().add(at);
                usa.getCiudades().add(ch);
                usa.getCiudades().add(mi);
                create(usa);

                Pais ing = new Pais("Inglaterra", new ArrayList<Ciudad>());                
                Ciudad cam = new Ciudad("Cambridge");
                Ciudad can = new Ciudad("Canterbury");
                Ciudad liv = new Ciudad("Liverpool");
                cam.setPais(ing);
                can.setPais(ing);                
                liv.setPais(ing);
                ing.getCiudades().add(cam);
                ing.getCiudades().add(can);
                ing.getCiudades().add(liv);
                create(ing);
            }
        }
        
         Usuario usuario1 = new Usuario("client", "clientclient", TipoUsuario.Cliente, "client client", 1020102010, TipoDocumento.CC, 
         4300145, 314586587, bog, "DG 85", Profesion.Ingeniero, "client@ggg.com");
          Usuario usuario2 = new Usuario("user2", "user_client", TipoUsuario.Cliente, "user client", 1020102013, TipoDocumento.CC, 
         4300145, 314586587, ny, "DG 85", Profesion.Ingeniero, "client_user@ggg.com");
          
        //Inicializa el arreglo que contiene los usuarios
        List listUsuarios = findAll(Usuario.class);
        if (listUsuarios.isEmpty()) {
            create(usuario1);
            create(usuario2);
            //TODO: no crea el admin????
            Usuario admin = new Usuario("admin", "adminadmin", TipoUsuario.Administrador, "admin admin", 1020102011, TipoDocumento.CC, 
         4300145, 314586587, bog, "DG 85", Profesion.Ingeniero, "cliente@ggg.com");
            create(admin);
            
            /*Usuario usuario2 = new Usuario("juanPaz", "juanPaz", TipoUsuario.Cliente);
            usuario2.setNombreCompleto("Sebastian Paz");
            create(usuario2);*/
        }
        
        List listVendedores = findAll(Vendedor.class);

        if (listVendedores.isEmpty()) {            
            ExperienciaVendedor ex1 = new ExperienciaVendedor(1L, "Banco de los Alpes", "Cajero", "Se desempeñó en diferentes áreas administrativas", 1998);           
            ArrayList<ExperienciaVendedor> experiencia1 = new ArrayList<ExperienciaVendedor>();
            experiencia1.add(ex1);
            Vendedor v = new Vendedor(1L, "Carlos Antonio", "Gomez Rodriguez", experiencia1, 900000, 80000, "Técnico en auditoría y costos", "vendedor1");
            create(v);
            
            ExperienciaVendedor ex2 = new ExperienciaVendedor(2L, "Marketplace de los Alpes", "Asesor de ventas", "Se desempeñó cómo consultor y asesor en área de ventas", 2006);
            ArrayList<ExperienciaVendedor> experiencia2 = new ArrayList<ExperienciaVendedor>();
            experiencia2.add(ex2);
           Vendedor v1 = new Vendedor(2L, "Claudia", "Sanchez Guerrero", experiencia2, 950000, 85000, "Comunicadora social", "vendedor2");
           create(v1);

             ExperienciaVendedor ex3 = new ExperienciaVendedor(3L, "Seguros de los Alpes", "Vendedor", "Se desempeñó como vendedora e impulsadora", 2010);
            ArrayList<ExperienciaVendedor> experiencia3 = new ArrayList<ExperienciaVendedor>();
            experiencia3.add(ex3);            
            Vendedor v2 = new Vendedor(3L, "Angela Patricia", "Montoya Zanabria", experiencia3, 1200000, 135000, "Técnico en Gestión de mercadeo", "vendedor2");
            create(v2);

            ExperienciaVendedor ex4 = new ExperienciaVendedor(4L, "Autopartes de los Alpes", "Director de producción", "Se desempeñó cómo director en el área de producción", 2009);
            ArrayList<ExperienciaVendedor> experiencia4 = new ArrayList<ExperienciaVendedor>();
            experiencia4.add(ex4); 
            Vendedor v3 = new Vendedor(4L, "Juan Pablo", "Escobar Vélez", experiencia4, 1000000, 100000, "Técnico en métodos de producción", "vendedor1");
            create(v3);

            //muebles = new ArrayList<Mueble>();

            //Agrega los muebles del sistema
             ArrayList<Mueble> muebles = new ArrayList();
            Mueble m1 = new Mueble(1L, "Silla clásica", "Una confortable silla con estilo del siglo XIX.", TipoMueble.Interior, 45, "sillaClasica", 123);
            create(m1);
            muebles.add(m1);
            Mueble m2 = new Mueble(2L, "Sillón new wave", "Innovador y cómodo. No existen mejores palabras para describir este hermoso sillón.", TipoMueble.Interior, 60, "newWave", 5655);
            create(m2);
            muebles.add(m2);
            Mueble m3 = new Mueble(3L, "Silla moderna", "Lo último en la moda de interiores. Esta silla le brindará la comodidad e innovación que busca", TipoMueble.Interior, 50, "sillaModerna", 5464);
            create(m3);
            muebles.add(m3);
            Mueble m4 = new Mueble(4L, "Mesa de jardín", "Una bella mesa para comidas y reuniones al aire libre.", TipoMueble.Exterior, 100, "mesaJardin", 4568);
            create(m4);
            muebles.add(m4);
            Mueble m5 = new Mueble(5L, "Orange games", "Una hermosa silla con un toqué moderno y elegante. Excelente para su sala de estar", TipoMueble.Interior, 70, "sillaNaranja", 1345);
            create(m5);
            muebles.add(m5);
            Mueble m6 = new Mueble(6L, "Cama king", "Una hermosa cama hecha en cedro para dos personas. Sus sueños no volveran a ser iguales.", TipoMueble.Interior, 50, "bed", 63358);
            create(m6);
            muebles.add(m6);
            Mueble m7 = new Mueble(7L, "Silla Neoclásica", "Una bella silla con un estilo neoclásico", TipoMueble.Exterior, 65, "neoClasica", 678);
            create(m7);
            muebles.add(m7);
            Mueble m8 = new Mueble(8L, "Camarote junior", "Con diseño moderno. Sus hijos ahora podrán tener unos felices sueños.", TipoMueble.Interior, 85, "camarote", 56565);
            create(m8);
            muebles.add(m8);
                        
            
            Random r = new Random();
            for (int e = 0; e < 10; e++) {
                //Genera un valor random para seleccionar el producto
                int rndProducto = (int) (Math.random() * 8);
                //Genera un valor random para seleccionar la cantidad 
                int rndCantidad = (int) (Math.random() * 15) + 1;

                RegistroVenta venta = new RegistroVenta();
                venta.setCantidad(rndCantidad);
                venta.setProducto(muebles.get(rndProducto));
                //Genera un valor random para establecer una fecha
                long ms = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 2 * 60 * 1000));
                venta.setFechaVenta(new Date(ms));
                //TODO: registro venta ciudad es entidad....!!!
                venta.setCiudad("Bogotá");
                venta.setComprador(usuario1);
                create(venta);
            }
            
            for (int e = 0; e < 5; e++) {
                //Genera un valor random para seleccionar el producto
                int rndProducto = (int) (Math.random() * 8);
                //Genera un valor random para seleccionar la cantidad 
                int rndCantidad = (int) (Math.random() * 15) + 1;

                RegistroVenta venta = new RegistroVenta();
                venta.setCantidad(rndCantidad);
                venta.setProducto(muebles.get(rndProducto));
                //Genera un valor random para establecer una fecha
                long ms = -946771200000L + (Math.abs(r.nextLong()) % (70L * 365 * 2 * 60 * 1000));
                venta.setFechaVenta(new Date(ms));
                //TODO: registro venta ciudad es entidad....!!!
                venta.setCiudad("New York");
                venta.setComprador(usuario2);
                create(venta);
            }
        }  
    }

    /**
     * La entidad encargada de persistir en la base de datos
     */
    //TODO
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor de la clase. Inicializa los atributos.
     */
    public ServicioPersistencia() {

    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Permite crear un objeto dentro de la persistencia del sistema.
     *
     * @param obj Objeto que representa la instancia de la entidad que se quiere
     * crear.
     */
    @Override
    public void create(Object obj) {
        em.persist(obj);
    }

    /**
     * Permite modificar un objeto dentro de la persistencia del sistema.
     *
     * @param obj Objeto que representa la instancia de la entidad que se quiere
     * modificar.
     */
    @Override
    public void update(Object obj) {
        em.merge(obj);
    }

    /**
     * Permite borrar un objeto dentro de la persistencia del sistema.
     *
     * @param obj Objeto que representa la instancia de la entidad que se quiere
     * borrar.
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        em.remove(obj);

    }

    /**
     * Retorna la lista de todos los elementos de una clase dada que se
     * encuentran en el sistema.
     *
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase dada que se
     * encuentran en el sistema.
     */
    @Override
    public List findAll(Class c) {
        
        return em.createQuery("select l from " + c.getSimpleName() + "  l").getResultList();
       
    }

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de
     * la entidadi.
     *
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    @Override
    public Object findById(Class c, Object id) {
        return em.find(c, id);
    }
    
    public List<Object[]> findByQuery(String sql) {
        Query query4 = em.createQuery(
                "Select c2.login, c2.nombreCompleto, count(c2), SUM(c5.precio * c1.cantidad) "
                        + "FROM RegistroVenta c1 "
                        + "inner join c1.comprador c2 "
                        + "inner join c2.ciudad c3 "
                        + "inner join c3.pais c4 "
                        + "inner join c1.producto c5 "
                        + "Where c4.id = :idPais"
                        + "group by c2").setParameter("idPais", 1);
        
        // query..append(" join pg.ditPatronSujetoObligado pso");
        
        List<Object[]> result6 = query4.getResultList();
        if(result6.size() > 0){
            String h = "";
        }
return result6;

 /* TypedQuery<Object[]> query = em.createQuery(
      "SELECT c.name, c.capital.name FROM Country AS c", Object[].class);
  List<Object[]> results = query.getResultList();
  for (Object[] result : results) {
      System.out.println("Country: " + result[0] + ", Capital: " + result[1]);
  }*/
        
    }
}
