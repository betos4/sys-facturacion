/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Producto;

/**
 *
 * @author Beto
 */
public interface productoDao {
    //Metodos para el CRUD
    public List<Producto> listarProductos();//Lista todos los clientes de la BDD
    public void newProducto(Producto producto);//Crea un nuevo cliente
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
    
    //metodo utilizado en la facturaBean
    public Producto obtenerProductoPorCodBarra(Session session, String codBarra) throws Exception;
}
