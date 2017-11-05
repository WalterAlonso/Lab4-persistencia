/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.Pais;
import com.losalpes.entities.Usuario;
import com.losalpes.entities.Vendedor;
import com.losalpes.servicios.IServicioPaisesMockLocal;
import com.losalpes.servicios.IServicioPersistenciaMockLocal;
import com.losalpes.servicios.IServicioReportesMockLocal;
import com.losalpes.servicios.IServicioVendedoresMockLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author WALTER
 */
@Named("reporteBean")
public class ReporteBean implements Serializable {

    /**
     * Relación con la interfaz que provee los servicios necesarios del vendedor
     */
    @EJB
    private IServicioReportesMockLocal servicioReportes;

    @EJB
    private IServicioPersistenciaMockLocal servicioPersistencia;

    /**
     * Representa un nuevo vendedor a ingresar
     */
    private Long pais;

    private List<Usuario> usuarios;

    private String usuario;

    /**
     * Creates a new instance of Reportes
     */
    public ReporteBean() {
    }

    /**
     * @return the pais
     */
    public Long getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Long pais) {
        this.pais = pais;
    }

    public List<Usuario> getUsuarios() {
        usuarios = servicioPersistencia.findAll(Usuario.class);
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Devuelve los top 5 de los clientes
     *
     * @return La lista de clientes
     */
    public List<Object[]> getClientesTop(AjaxBehaviorEvent event) {
        List<Object[]> list = new ArrayList<>();
        if (pais != null && pais != -1) {
            list = servicioReportes.consultarTopClientesDePais(pais, 5);
        }
        return list;
    }

    /**
     * Devuelve el historial de compras de un cliente
     *
     * @return Historial de Ventas
     */
    public List<Object[]> getHistorialCompras(AjaxBehaviorEvent event) {
        List<Object[]> compras = new ArrayList<>();
        if (usuario != null) {
            compras = servicioReportes.consultarHistorialComprasDeUsuario(usuario);
        }
        return compras;
    }

    /**
     * Devuelve el top 3 de muebles
     *
     * @return La lista de muebles
     */
    public List<Object[]> getMueblesTop() {
        List<Object[]> list = new ArrayList<>();
        list = servicioReportes.consultarTopMuebles();
        return list;
    }

}
