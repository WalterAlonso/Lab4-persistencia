/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.Pais;
import com.losalpes.entities.Vendedor;
import com.losalpes.servicios.IServicioPaisesMockLocal;
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
public class ReporteBean implements Serializable{

     /**
     * Relación con la interfaz que provee los servicios necesarios del vendedor
     */
    @EJB
    private IServicioReportesMockLocal servicioReportes;
    
    @EJB
    private IServicioPaisesMockLocal servicioPaises;
    
     /**
     * Representa un nuevo vendedor a ingresar
     */
    private Long pais;

    
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
    
    /**
     * Devuelve  los top 5 de los clientes
     * @return La lista de clientes
     */
    public List<Object[]> getClientesTop(AjaxBehaviorEvent event)
    {
        List<Object[]> list = new ArrayList<>();
        if (pais != null && pais != -1){
            list = servicioReportes.consultarTopClientesDePais(pais, 5);            
        }
        return list;
    }

    
    
    
}
