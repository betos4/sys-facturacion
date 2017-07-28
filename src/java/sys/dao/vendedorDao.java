/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import sys.model.Vendedor;

/**
 *
 * @author Beto
 */
public interface vendedorDao {
    //Metodos para el CRUD
    public List<Vendedor> listarVendedor();//Lista todos los clientes de la BDD
    public void newVendedor(Vendedor cliente);//Crea un nuevo cliente
    public void updateVendedor(Vendedor cliente);
    public void deleteVendedor(Vendedor cliente);
}
