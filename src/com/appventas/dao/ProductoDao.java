package com.appventas.dao;

import com.appventas.modelo.Producto;
import java.util.List;

public interface ProductoDao {

    public List<Producto> findAllProducto();
    public Producto findProductoById(Integer id);
    public Producto findProductoByDescripcion(String descripcion);
    public void saveProducto (Producto elemento);
    public void deleteProducto(Producto elemento);
    public void updateProducto(Producto elemento);    
}
