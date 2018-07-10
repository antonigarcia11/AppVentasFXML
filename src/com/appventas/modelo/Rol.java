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
@Table(name="rol")
@NamedQueries({@NamedQuery(name="Rol.findAll",query="from Rol")})


public class Rol implements Serializable {
    private final IntegerProperty idRol;
    private final StringProperty descripcion;
    private Set<Usuario> usuarios;
    public Rol() {
        this.idRol = new SimpleIntegerProperty();
        this.descripcion = new SimpleStringProperty();
    }

    public Rol(int idRol, String descripcion) {
        this.idRol = new SimpleIntegerProperty (idRol);
        this.descripcion = new SimpleStringProperty (descripcion);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    public int getidRol() {
        return idRol.get();
    }
    public void setIdRol(int idRol){
        this.idRol.set(idRol);
    }
     public IntegerProperty idRol() {
        return idRol;
    }
    @Column(name="descripcion")
    public String getDescripcion(){
        return descripcion.get();
    }
    public void setDescripcion(String descripcion){
        this.descripcion.set(descripcion);
    }
    public StringProperty descripcion(){
        return descripcion;
    }
    @OneToMany(mappedBy = "rol")
    public Set<Usuario> getUsuarios(){
        return usuarios;
    }
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        //return "Rol{" + "idRol=" + idRol + ", descripcion=" + descripcion + ", usuarios=" + usuarios + '}';
        return idRol.get() + ") " + descripcion.get();
    }
}