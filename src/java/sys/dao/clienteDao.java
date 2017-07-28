/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Cliente;

/**
 *
 * @author Beto
 */
public interface clienteDao {
    //Metodos para el CRUD
    public List<Cliente> listarClientes();//Lista todos los clientes de la BDD
    public void newCliente(Cliente cliente);//Crea un nuevo cliente
    public void updateCliente(Cliente cliente);
    public void deleteCliente(Cliente cliente);
    
    //este metodo se utilizara en la factura, facturabean
    public Cliente obtenerClientePorCodigo(Session session, Integer codCliente) throws Exception;
}
