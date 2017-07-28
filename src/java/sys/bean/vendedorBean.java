/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.vendedorDao;
import sys.imp.vendedorDaoImp;
import sys.model.Vendedor;

/**
 *
 * @author Beto
 */
@ManagedBean
@ViewScoped
public class vendedorBean {

    private List<Vendedor> listaVendedores;
    private Vendedor vendedor;
    
    public vendedorBean() {
        //Para no tener problemas al crear la vista de vendedores
        vendedor = new Vendedor();
    }    

    public void setListaVendedores(List<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public List<Vendedor> getListaVendedores() {
        vendedorDao vDao = new vendedorDaoImp();
        listaVendedores = vDao.listarVendedor();
        return listaVendedores;
    }
    
    //Metodos para el CRUD
    public void prepararNuevoVendedor() {//solo crea una nueva instancia para limpiar la lista
        vendedor = new Vendedor();
    }
    
    public void nuevoVendedor() {
        vendedorDao cDao = new vendedorDaoImp();
        cDao.newVendedor(vendedor);
    }
     
    public void modificarVendedor() {
        vendedorDao cDao = new vendedorDaoImp();
        cDao.updateVendedor(vendedor);
        vendedor = new Vendedor();//Para limpiar el formulario
    }
    
    public void eliminarVendedor() {
        vendedorDao cDao = new vendedorDaoImp();
        cDao.deleteVendedor(vendedor);
        vendedor = new Vendedor();//para limpiar el formulario
    }
}
