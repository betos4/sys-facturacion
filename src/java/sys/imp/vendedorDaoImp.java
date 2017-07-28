/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.vendedorDao;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

/**
 *
 * @author Beto
 */
public class vendedorDaoImp implements vendedorDao{
    @Override
    public List<Vendedor> listarVendedor() {
        List<Vendedor> lista = null;//limpiando lista
        Session session = HibernateUtil.getSessionFactory().openSession();//abriendo sesion
        Transaction t = session.beginTransaction();
        String hql = "FROM Vendedor";
        
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
    public void newVendedor(Vendedor vendedor) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();//inicia la transaccion
            session.save(vendedor);//se guarda todo en el objeto cliente que recibe como parametro
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
    public void updateVendedor(Vendedor vendedor) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();//inicia la transaccion
            session.update(vendedor);//se actualiza todo en el objeto cliente que recibe como parametro
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
    public void deleteVendedor(Vendedor vendedor) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();//inicia la transaccion
            session.delete(vendedor);//se actualiza todo en el objeto cliente que recibe como parametro
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
}
