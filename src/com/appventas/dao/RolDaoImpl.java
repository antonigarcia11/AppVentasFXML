package com.appventas.dao;

import com.appventas.db.Conexion;
import com.appventas.modelo.Rol;
import java.util.List;

public class RolDaoImpl implements RolDao {

    @Override
    public List<Rol> findAllRol() {
        return (List<Rol>)Conexion.getInstancia().findAll(Rol.class);
    }

    @Override
    public Rol findRolById(Integer id) {
        return (Rol) Conexion.getInstancia().findById(Rol.class, id);
    }

    @Override
    public Rol findRolByDescripcion(String descripcion) {
        List<Rol>rol = (List<Rol>) Conexion.getInstancia().getEm()
                .createNamedQuery("select c from Rol c where c.descripcion =: descripcion")
                .setParameter("descripcion", descripcion)
                .getResultList();
        if (!rol.isEmpty()) {
            return rol.get(0);
        }
        return null;
    }

    @Override
    public void saveRol(Rol elemento) {
        Conexion.getInstancia().save(elemento);
    }

    @Override
    public void deleteRol(Rol elemento) {
        Conexion.getInstancia().delete(elemento);
    }

    @Override
    public void updateRol(Rol elemento) {
        Conexion.getInstancia().update(elemento);
    }
    
}
