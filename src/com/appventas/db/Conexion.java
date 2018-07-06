package com.appventas.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Conexion {
    
    public final String PERSISTENCE_UNIT_NAME = "AppVentasPU";
    private EntityManager em;
    private static Conexion instancia;

    public Conexion() {

        try {
            em = Persistence
                    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                    .createEntityManager();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Conexion getInstancia() {

        if (instancia == null) {
            instancia = new Conexion();

        }
        return instancia;

    }

    public void save(Object elemento) {

        try {
            em.getTransaction().begin();
            em.persist(elemento);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public void delete(Object elemento) {

        try {
            em.getTransaction().begin();
            em.remove(elemento);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public void update(Object elemento) {

        try {
            em.getTransaction().begin();
            em.merge(elemento);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public List<?> findAll(Class<?> clase) {
        return em.createNamedQuery(clase.getTypeName()
                .substring(clase.getTypeName()
                        .lastIndexOf(".") + 1,clase.getTypeName()
                                .length()) + ".findAll").getResultList();

    }

    public Object findById(Class<?> clase, Integer id) {
        return em.find(clase, id);
    }

    public Object findById(Class<?> clase, String id) {
        return em.find(clase, id);
    }

    public EntityManager getEm() {
        return em;
    }
    
}
