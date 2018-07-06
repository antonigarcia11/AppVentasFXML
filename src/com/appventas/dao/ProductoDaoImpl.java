package com.appventas.dao;

import com.appventas.modelo.Producto;
import com.appventas.db.Conexion;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

   

    @Override
    public List<Producto> findAllProducto() {
        return(List<Producto>)Conexion.getInstancia().findAll(Producto.class);
    }

    @Override
    public Producto findProductoById(Integer id) {
        return (Producto) (List<Producto>) (Producto) Conexion.getInstancia().findAll(Producto.class);

    }
    

    @Override
    public Producto findProductoByDescripcion(String descripcion) {
        List<Producto> producto;
        producto = (List<Producto>) Conexion.getInstancia().getEm()
                .createNamedQuery("select p from Producto p where p.descripcion =: descripcion")
                .setParameter("descripcion", descripcion)
                .getResultList();
        if (!producto.isEmpty()) {
            return producto.get(0);
        }
        return null;
    }
    
    @Override
    public void saveProducto(Producto elemento) {
        Conexion.getInstancia().save(elemento);

    }

    @Override
    public void deleteProducto(Producto elemento) {
        Conexion.getInstancia().delete(elemento);

    }

    @Override
    public void updateProducto(Producto elemento) {
        Conexion.getInstancia().update(elemento);

    }

    
   
}
