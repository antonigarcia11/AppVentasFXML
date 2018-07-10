package com.appventas.dao;

import com.appventas.modelo.Usuario;
import java.util.List;

public interface UsuarioDao {
    public List<Usuario> findAllUsuario();
    public Usuario findUsuarioById(Integer id);
    public Usuario findUsuarioByDescripcion(String descripcion);
    public void saveUsuario (Usuario elemento);
    public void deleteUsuario(Usuario elemento);
    public void updateUsuario(Usuario elemento);
    
}
