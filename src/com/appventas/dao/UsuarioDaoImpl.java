package com.appventas.dao;

import com.appventas.db.Conexion;
import com.appventas.modelo.Usuario;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public List<Usuario> findAllUsuario() {
        return(List<Usuario>)Conexion.getInstancia().findAll(Usuario.class);
    }

    @Override
    public Usuario findUsuarioById(Integer id) {
        return (Usuario) (List<Usuario>) (Usuario) Conexion.getInstancia().findAll(Usuario.class);
    }

    @Override
    public Usuario findUsuarioByDescripcion(String descripcion) {
        List<Usuario>usuario;
        usuario = (List<Usuario>) Conexion.getInstancia().getEm()
                .createNamedQuery("select c from Usuario c where c.descripcion =: descripcion")
                .setParameter("descripcion", descripcion)
                .getResultList();
        if (!usuario.isEmpty()) {
            return usuario.get(0);
        }
        return null;
    }

    @Override
    public void saveUsuario(Usuario elemento) {
        Conexion.getInstancia().save(elemento);
    }

    @Override
    public void deleteUsuario(Usuario elemento) {
        Conexion.getInstancia().delete(elemento);
    }

    @Override
    public void updateUsuario(Usuario elemento) {
        Conexion.getInstancia().update(elemento);
    }
    
}
