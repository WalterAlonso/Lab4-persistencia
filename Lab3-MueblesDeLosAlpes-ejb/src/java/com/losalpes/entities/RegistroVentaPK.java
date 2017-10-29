/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author WALTER
 */

public class RegistroVentaPK implements Serializable{
   // @Column(name="fechaVenta",nullable=false)
    private Date fechaVenta;
    /*@Column(name="idMueble",nullable=false)*/
    private long producto;
   // @Column(name="IdUsuario",nullable=false)
    private String comprador;
    
    public RegistroVentaPK() {}

    public RegistroVentaPK(Date fechaVenta, String comprador, long producto) {
        this.fechaVenta = fechaVenta;
        this.comprador = comprador;
        this.producto = producto;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.fechaVenta);
        hash = 59 * hash + Objects.hashCode(this.comprador);
        hash = 59 * hash + Objects.hashCode(this.producto);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistroVentaPK other = (RegistroVentaPK) obj;
        if (!Objects.equals(this.fechaVenta, other.fechaVenta)) {
            return false;
        }
        if (!Objects.equals(this.comprador, other.comprador)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return true;
    }
}
    
