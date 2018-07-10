package com.appventas.dao;

import com.appventas.modelo.Rol;
import java.util.List;


public interface RolDao {
    public List<Rol> findAllRol();
    public Rol findRolById(Integer id);
    public Rol findRolByDescripcion(String descripcion);
    public void saveRol (Rol elemento);
    public void deleteRol(Rol elemento);
    public void updateRol(Rol elemento);
}
