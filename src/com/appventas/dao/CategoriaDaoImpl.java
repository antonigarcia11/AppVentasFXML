package com.appventas.dao;

import com.appventas.modelo.Categoria;
import com.appventas.db.Conexion;
import java.util.List;

public class CategoriaDaoImpl implements CategoriaDao {

    @Override
    public List<Categoria> findAllCategoria() {
        return (List<Categoria>)Conexion.getInstancia().findAll(Categoria.class);
    }

    @Override
    public Categoria findCategoriaById(Integer id) {
        return (Categoria) Conexion.getInstancia().findById(Categoria.class, id);
    }

    @Override
    public Categoria findCategoriaByDescripcion(String descripcion) {
        
       List<Categoria>categoria = (List<Categoria>) Conexion.getInstancia().getEm()
                .createNamedQuery("select c from Categoria c where c.descripcion =: descripcion")
                .setParameter("descripcion", descripcion)
                .getResultList();
        if (!categoria.isEmpty()) {
            return categoria.get(0);
        }
        return null;
    }

    @Override
    public void saveCategoria(Categoria elemento) {
        Conexion.getInstancia().save(elemento);
    }

    @Override
    public void deleteCategoria(Categoria elemento) {
        Conexion.getInstancia().delete(elemento);
    }

    @Override
    public void updateCategoria(Categoria elemento) {
        Conexion.getInstancia().update(elemento);
    }

}
