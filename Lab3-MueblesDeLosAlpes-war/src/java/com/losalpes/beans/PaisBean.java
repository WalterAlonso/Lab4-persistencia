/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.beans;

import com.losalpes.entities.Pais;
import com.losalpes.excepciones.OperacionInvalidaException;
import com.losalpes.servicios.IServicioPaisesMockLocal;
import com.losalpes.servicios.ServicioPaisesMock;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Juan Paz
 */
public class PaisBean implements Serializable {
 
    @EJB
    private IServicioPaisesMockLocal paisServices;

    private List<Pais> paises;

    private Pais pais;

    /**
     * Creates a new instance of PaisBean
     */
    public PaisBean() {
        //paisServices = new ServicioPaisesMock();
        paises = new ArrayList<Pais>();
    }

    public List<Pais> getPaises() {
        paises = paisServices.getPaises();
        
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void crearPais() {
        paisServices.crearPais(pais);
    }

    public void eliminar(ActionEvent evento) throws OperacionInvalidaException {

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            Long paisId = Long.parseLong((String)map.get("paisId"));

            paisServices.eliminarPais(paisId);

        } catch (OperacionInvalidaException e) {
            throw new OperacionInvalidaException(e.getMessage());

        }

    }
}
