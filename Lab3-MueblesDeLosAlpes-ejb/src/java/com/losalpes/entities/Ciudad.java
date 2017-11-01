/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ Ciudad.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Clase que representa una ciudad en el sistema
 *
 */
@Entity
public class Ciudad {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Id del ciudad
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Nombre de la ciudad
     */
    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "PAIS_ID")
    private Pais pais;

    /**
     * Devuelve el nombre de la ciudad
     *
     * @return nombre Nombre de la ciudad
     */
    public String getNombre() {
        return nombre;
    }

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------
    /**
     * Constructor de la clase (sin argumentos)
     */
    public Ciudad() {

    }

    /**
     * Constructor de la clase (con argumentos)
     *
     * @param nombre
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------
    /**
     * Modifica el nombre de la ciudad
     *
     * @param nombre Nuevo nombre de la ciudad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
}
