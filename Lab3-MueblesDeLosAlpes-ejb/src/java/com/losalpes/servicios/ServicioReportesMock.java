/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Pais;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author WALTER
 */
@Stateless
public class ServicioReportesMock implements IServicioReportesMockRemote, IServicioReportesMockLocal {

    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    public List<Object[]> consultarTopClientesDePais(Long idPais, int cantidad) {
        String query = "Select c2.login, c2.nombreCompleto, count(c2) compras, SUM(c5.precio * c1.cantidad) dinero "
                + "FROM RegistroVenta c1 "
                + "inner join c1.comprador c2 "
                + "inner join c2.ciudad c3 "
                + "inner join c3.pais c4 "
                + "inner join c1.producto c5 "
                + "Where c4.id = " + idPais
                + " group by c2 "
                + " ORDER BY SUM(c5.precio * c1.cantidad) DESC";
        List<Object[]> clientes = persistencia.findByQuery(query, cantidad);
        return clientes;
    }

    public List<Object[]> consultarHistorialComprasDeUsuario(String idUsuario) {
        String query = "SELECT c1.fechaVenta, c2.nombre, c1.cantidad, (c1.cantidad * c2.precio) precio "
                + "FROM RegistroVenta c1 "
                + "INNER JOIN c1.producto c2 "
                + "INNER JOIN c1.comprador c3 "
                + "WHERE c3.login ='" + idUsuario + "'";

        List<Object[]> compras = persistencia.findByQuery(query, 100);
        return compras;
    }

    public List<Object[]> consultarTopMuebles() {
        String query = "SELECT c2.nombre, SUM(c1.cantidad) cantidad "
                + "FROM RegistroVenta c1 "
                + "INNER JOIN c1.producto c2 "
                + "group by c2 "
                + " ORDER BY SUM(c1.cantidad) DESC";

        List<Object[]> compras = persistencia.findByQuery(query, 3);
        return compras;
    }

}
