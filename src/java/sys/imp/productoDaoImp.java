/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.Query;
import sys.dao.productoDao;
import sys.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.util.HibernateUtil;

/**
 *
 * @author Beto
 */
public class productoDaoImp implements productoDao {

    @Override
    public List<Producto> listarProductos() {
        List<Producto> lista = null;//limpiando lista
        Session session = HibernateUtil.getSessionFactory().openSession();//abriendo sesion
        Transaction t = session.beginTransaction();
        String hql = "FROM Producto";
        
        try {
            lista = session.createQuery(hql).list();//se llena la lista.... el '.list()' es para traer el tipo de valores
            t.commit();
            session.close();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    public void newProducto(Producto producto) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();//inicia la transaccion
            session.save(producto);//se guarda todo en el objeto cliente que recibe como parametro
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateProducto(Producto producto) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();//inicia la transaccion
            session.update(producto);//se actualiza todo en el objeto cliente que recibe como parametro
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteProducto(Producto producto) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();//inicia la transaccion
            session.delete(producto);//se actualiza todo en el objeto cliente que recibe como parametro
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Producto obtenerProductoPorCodBarra(Session session, String codBarra) throws Exception {
        String hql = "FROM Producto WHERE codBarra = :codBarra";
        Query q = session.createQuery(hql);
        q.setParameter("codBarra", codBarra);
        
        return (Producto) q.uniqueResult();
    }
    
}
