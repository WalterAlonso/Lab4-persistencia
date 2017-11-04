/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Pais;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author WALTER
 */
@Remote
public interface IServicioReportesMockRemote {
    /**
     * Obtiene el top de clientes
     * @param idPais el id del pais
     * @param cantidad la cantidad de clientes a obtener.
     * @throws OperacionInvalidaException excepcion
     */
    public List<Object[]> consultarTopClientesDePais(Long idPais, int cantidad);
}
