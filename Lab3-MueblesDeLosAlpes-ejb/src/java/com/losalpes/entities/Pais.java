/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ Pais.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Clase que representa un país en el sistema
 *
 */
@Entity
public class Pais {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Id del país
     */
    @Id
    @GeneratedValue
    private Long id;

    
    /**
     * Nombre del país
     */
    @Column(nullable = false)
    private String nombre;
    /**
     * Ciudades que tiene el país
     */
    //Bidireccional mapping
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    //Unidirectional mapping
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "PAIS_ID")
    private List<Ciudad> ciudades =  new ArrayList<>();

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor de la clase (sin argumentos)
     */
    public Pais() {

    }

    /**
     * Constructor de la clase (con argumentos)
     *
     * @param nombre Nombre del país
     * @param ciudades Lista con las ciudades del país
     */
    public Pais(String nombre, List<Ciudad> ciudades) {
        this.nombre = nombre;
        this.ciudades = ciudades;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------
    /**
     * Devuele el nombre del país
     *
     * @return nombre Nombre del país
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del país
     *
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve todas las ciudades del país
     *
     * @return ciudades Ciudades del país
     */
    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    /**
     * Modifica las ciudades del país
     *
     * @param ciudades Nuevas ciudades
     */
    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
