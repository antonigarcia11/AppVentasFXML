package com.appventas.modelo;
import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "producto")
@NamedQueries({@NamedQuery(name="Producto.findAll",query="from Producto")})
public class Producto implements Serializable {
    private IntegerProperty codigoProducto;
    private final StringProperty descripcion;
    private final IntegerProperty precio;
    private final DoubleProperty precioUnitario;
    private final IntegerProperty existencias;
    
    private Categoria categoria;
    
    public Producto(){
        this.codigoProducto = new SimpleIntegerProperty();
        this.descripcion = new SimpleStringProperty("");
        this.precio = new SimpleIntegerProperty();
        this.precioUnitario = new SimpleDoubleProperty();
        this.existencias = new SimpleIntegerProperty();
    }
    public Producto(int codigoProducto, String descripcion, int precio, double precioUnitario, int existencias, Categoria categoria){
        this.codigoProducto = new SimpleIntegerProperty(codigoProducto);
         this.descripcion = new SimpleStringProperty (descripcion);
        this.precio = new SimpleIntegerProperty(precio);
        this.precioUnitario = new SimpleDoubleProperty(precioUnitario);
        this.existencias = new SimpleIntegerProperty(existencias);
        this.categoria = categoria;
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    public int getCodigoProducto() {
        return codigoProducto.get();
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto.set(codigoProducto);
    }
    
    public IntegerProperty codigoProducto(){
        return codigoProducto;
    }
     
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    public StringProperty descripcion(){
        return descripcion;
    }
    
    @Column(name = "precio")
    public int getPrecio(){
        return precio.get();
    }
    
    public void setPrecio(int precio){
        this.precio.set(precio);
    }
    
    public IntegerProperty precio(){
        return precio;
    }
    
    @Column(name = "precioUnitario")
    public double getPrecioUnitario(){
        return precioUnitario.get();
    }
    
    public void setPrecioUnitario(double precioUnitario){
        this.precioUnitario.set(precioUnitario);
    }
    
    public DoubleProperty precioUnitario(){
        return precioUnitario;
    }
    
    @Column(name = "existencias")
    public int getExistencias(){
        return existencias.get();
    }
    
    public void setExistencias(int existencias){
        this.existencias.set(existencias);
    }
    
   public IntegerProperty existencias(){
       return existencias;
   }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_categoria", updatable = false, insertable = true, nullable = false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigoProducto=" + codigoProducto + ", descripcion=" + descripcion + ", categoria=" + categoria + '}';
    }

}