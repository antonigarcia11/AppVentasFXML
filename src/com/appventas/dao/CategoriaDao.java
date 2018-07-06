package com.appventas.dao;

import com.appventas.modelo.Categoria;
import java.util.List;



public interface CategoriaDao {

    public List<Categoria> findAllCategoria();
    public Categoria findCategoriaById(Integer id);
    public Categoria findCategoriaByDescripcion(String descripcion);
    public void saveCategoria (Categoria elemento);
    public void deleteCategoria(Categoria elemento);
    public void updateCategoria(Categoria elemento);
    
}