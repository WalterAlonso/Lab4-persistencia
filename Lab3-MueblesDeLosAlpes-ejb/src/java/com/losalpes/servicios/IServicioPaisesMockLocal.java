/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioSeguridadLocal.java
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
import javax.ejb.Local;

/**
 * Contrato funcional de los servicios de autenticación en el sistema
 * 
 */
@Local
public interface IServicioPaisesMockLocal
{

    public List<Pais> getPaises();
    
    public void crearPais(Pais pais);
    
    public void eliminarPais(Long pais) throws OperacionInvalidaException ;

}
