package com.appventas.modelo;

import java.io.Serializable;
import java.util.Set;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
@NamedQueries({@NamedQuery(name="Categoria.findAll",query="from Categoria")})
public class Categoria implements Serializable{
    
    private final IntegerProperty codigoCategoria;
    private final StringProperty descripcion;
    private Set<Producto> productos;
  
    
    public Categoria(){
        this.codigoCategoria = new SimpleIntegerProperty();
        this.descripcion = new SimpleStringProperty("");
    }
    public Categoria(int codigoCategoria, String descripcion){
        this.codigoCategoria = new SimpleIntegerProperty(codigoCategoria);
        this.descripcion = new SimpleStringProperty(descripcion);
    }    
        
    @Override
    public String toString(){
        //return "Categoria{" + "codigoCategoria=" + codigoCategoria.get() + ", descripcion=" + descripcion.get() + '}';
        return codigoCategoria.get() + " | " + descripcion.get();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_categoria")
    public int getCodigoCategoria(){
        return codigoCategoria.get();
    }
    public void setCodigoCategoria(int codigoCategoria){
        this.codigoCategoria.set(codigoCategoria);
    }
    public IntegerProperty codigoCategoria(){
        return codigoCategoria;
    }
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion.get();
    }
    public void setDescripcion(String descripcion){
        this.descripcion.set(descripcion);
    }
    public StringProperty descripcion() {
        return descripcion;
    }
    @OneToMany(mappedBy = "categoria")
    public Set<Producto> getProductos(){
        return productos;
    }
    public void setProductos(Set<Producto> productos){
        this.productos = productos;
    }
}
