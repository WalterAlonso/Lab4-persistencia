/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioVendedoresMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.servicios;

import com.losalpes.entities.Pais;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementación de los servicios de administración de un vendedor en el
 * sistema
 *
 */
@Stateless
public class ServicioPaisesMock implements IServicioPaisesMockLocal, IServicioPaisesMockRemote {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor de la clase sin argumentos
     */
    public ServicioPaisesMock() {
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    @Override
    public List<Pais> getPaises() {
        return persistencia.findAll(Pais.class);
    }

    @Override
    public void crearPais(Pais pais) {
        try
        {
            persistencia.create(pais);
        }
        catch (OperacionInvalidaException ex)
        {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void eliminarPais(Long pais) throws OperacionInvalidaException {
        try {
            Pais p = (Pais) persistencia.findById(Pais.class, pais);
            persistencia.delete(p);
        } catch (OperacionInvalidaException e) {
            throw new OperacionInvalidaException("Ocurrió un error al momento de eliminar");
        }
    }

}
