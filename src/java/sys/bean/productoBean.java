/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.productoDao;
import sys.imp.productoDaoImp;
import sys.model.Producto;

/**
 *
 * @author Beto
 */
@ManagedBean
@ViewScoped
public class productoBean {
    private List<Producto> listaProductos;
    private Producto producto;
    
    public productoBean() {
        producto = new Producto();
    }    

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public List<Producto> getListaProductos() {
        productoDao cDao = new productoDaoImp();
        listaProductos = cDao.listarProductos();        
        return listaProductos;
    }
    
    //Metodos para el CRUD
    public void prepararNuevoProducto() {//solo crea una nueva instancia para limpiar la lista
        producto = new Producto();
    }
    
    public void nuevoProducto() {
        productoDao cDao = new productoDaoImp();
        cDao.newProducto(producto);
    }
     
    public void modificarProducto() {
        productoDao cDao = new productoDaoImp();
        cDao.updateProducto(producto);
        producto = new Producto();
    }
    
    public void eliminarProducto() {
        productoDao cDao = new productoDaoImp();
        cDao.deleteProducto(producto);
        producto = new Producto();
    }
}
