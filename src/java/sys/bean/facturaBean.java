/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.clienteDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Producto;
import sys.util.HibernateUtil;

/**
 *
 * @author Beto
 */
@ManagedBean
@ViewScoped
public class facturaBean {
    
    Session session = null;
    Transaction transaction = null;
    
    private Cliente cliente;
    private Integer codigoCliente;
    
    private Producto producto;
    private String codigoBarra;
    private List<Detallefactura> listaDetalleFactura;
    
    
    public facturaBean() {
        //como tenemos un arrayList es necesario inicializarlo
        this.listaDetalleFactura = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }
    
    
    
    
    //Metodo para agregar los datos de los clientes por medio del dialogClientes
    public void agregarDatosCliente(Integer codCliente) {
        this.session = null;
        this.transaction = null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            
            //obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);
            
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));
        } catch (Exception e) {
            if(this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(this.session != null) {
                this.session.close();
            }
        }
    }
    
    //Metodo para agregar los datos del cliente por codCliente
    public void agregarDatosCliente2() {
        this.session = null;
        this.transaction = null;
        
        try {
            if(this.codigoCliente == null) {
                return;//nos mantiene siemre en el input
            }
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            
            //obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);
            
            //verifico si el objeto cliente se lleno
            if(this.cliente != null) {
                this.codigoCliente = null;
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado")); 
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cliente no encontrado"));
            }
            
            this.transaction.commit();            
        } catch (Exception e) {
            if(this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(this.session != null) {
                this.session.close();
            }
        }
    }
    
    //Metodo para agregar los datos de los productos por medio del dialogProductos
    public void agregarDatosProducto(String codBarra) {
        this.session = null;
        this.transaction = null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();
            this.transaction = this.session.beginTransaction();
            
            //obtener los datos del producto en la variable objeto producto, segun el codBarra
            this.producto = pDao.obtenerProductoPorCodBarra(this.session, codBarra);
            //pasando los datos del objeto al arrayList
            this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(), 0, this.producto.getPrecioVenta(), new BigDecimal(0)));
            this.transaction.commit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado"));
        } catch (Exception e) {
            if(this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(this.session != null) {
                this.session.close();
            }
        }
    }
    
    //Metodo para agregar los datos del producto por codBarra
    public void agregarDatosProducto2() {
        this.session = null;
        this.transaction = null;
        
        try {
            if(this.codigoBarra.equals("")) {
                return;//nos mantiene siemre en el input
            }
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();
            this.transaction = this.session.beginTransaction();
            
            //obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.producto = pDao.obtenerProductoPorCodBarra(this.session, this.codigoBarra);
            
            //verifico si el objeto cliente se lleno
            if(this.producto != null) {
                this.listaDetalleFactura.add(new Detallefactura(null, null, this.producto.getCodBarra(), this.producto.getNombreProducto(), 0, this.producto.getPrecioVenta(), new BigDecimal(0)));
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado")); 
            } else {
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto no encontrado"));
            }
            
            this.transaction.commit();            
        } catch (Exception e) {
            if(this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if(this.session != null) {
                this.session.close();
            }
        }
    }
}
