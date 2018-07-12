package com.appventas.controller;

import com.appventas.dao.RolDaoImpl;
import com.appventas.dao.UsuarioDaoImpl;
import com.appventas.modelo.Rol;
import com.appventas.modelo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class UsuarioController implements Initializable{
    private enum ACCIONES {NUEVO,EDITAR};
    @FXML private TableView<Usuario> tblUsuario;
    @FXML private TableColumn<Usuario, Number> colId_Usuario;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colLogin;
    @FXML private TableColumn<Usuario, String> colPassword;
    @FXML private TableColumn<Usuario, String> colEmail;
    @FXML private TableColumn<Usuario, String> colRol_Usuario;
    
    @FXML private ComboBox cmbRol;

    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;

    @FXML private TextField txtNombre;
    @FXML private TextField txtLogin;
    @FXML private TextField txtPassword;
    @FXML private TextField txtEmail;

    @FXML private ObservableList<Usuario> listaUsuarios;
    @FXML private ObservableList<Rol> listaRoles;

    private UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
    private RolDaoImpl rolDao = new RolDaoImpl();
    private ACCIONES accion;
    private Usuario elementoSeleccionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUsuarios();
        enlazarDatos();
        enlazarColumnas();
        mostrarRoles();
    }
    public void seleccionarElemento(){
        if(tblUsuario.getSelectionModel().getSelectedItem() != null){
            elementoSeleccionado = tblUsuario.getSelectionModel().getSelectedItem();
            txtNombre.setText(elementoSeleccionado.getNombre());
            txtLogin.setText(elementoSeleccionado.getLogin());
            txtPassword.setText(elementoSeleccionado.getPassword());
            txtEmail.setText(elementoSeleccionado.getEmail());
            cmbRol.setValue(elementoSeleccionado.getRol());
        }
    }
    public void getUsuarios() {
        listaUsuarios = FXCollections.observableArrayList(usuarioDao.findAllUsuario());
    }
    
    public void enlazarDatos() {
        tblUsuario.setItems(listaUsuarios);
    }

    public void enlazarColumnas() {
        colId_Usuario.setCellValueFactory(cellData->cellData.getValue().Id_Usuario());
        colNombre.setCellValueFactory(cellData->cellData.getValue().nombre());
        colLogin.setCellValueFactory(cellData->cellData.getValue().login());
        colPassword.setCellValueFactory(cellData->cellData.getValue().password());
        colEmail.setCellValueFactory(cellData->cellData.getValue().email());
        colRol_Usuario.setCellValueFactory(cellData->cellData.getValue().getRol().descripcion());
    }
    private void mostrarRoles() {
        listaRoles= FXCollections.observableArrayList(rolDao.findAllRol());
        cmbRol.setItems(listaRoles);
    }
    public void nuevo(){
        txtNombre.setDisable(false);
        txtLogin.setDisable(false);
        txtPassword.setDisable(false);
        txtEmail.setDisable(false);
        
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEliminar.setDisable(true);
        btnEditar.setDisable(true);
        cmbRol.setDisable(false);
        accion = ACCIONES.NUEVO;
    }
    public void guardar(){
        if(cmbRol.getSelectionModel().getSelectedItem() ==null){
            JOptionPane.showMessageDialog(null,"Debe seleccionar una Rol");
        }else if(txtNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre");
        }else{
            try {
                switch(accion){
                    case NUEVO:
                        Usuario usuario = new Usuario();
                        usuario.setRol((Rol)cmbRol.getSelectionModel().getSelectedItem());
                        usuario.setNombre(txtNombre.getText());
                        usuario.setLogin(txtLogin.getText());
                        usuario.setPassword(txtPassword.getText());
                        usuario.setEmail(txtEmail.getText());
                        usuarioDao.saveUsuario(usuario);
                        listaUsuarios.add(usuario);
                        break;
                    case EDITAR:
                        elementoSeleccionado.setNombre(txtNombre.getText());
                        elementoSeleccionado.setLogin(txtLogin.getText());
                        elementoSeleccionado.setPassword(txtPassword.getText());
                        elementoSeleccionado.setEmail(txtEmail.getText());
                        elementoSeleccionado.setRol((Rol)cmbRol.getSelectionModel().getSelectedItem());
                        usuarioDao.saveUsuario(elementoSeleccionado);
                        listaUsuarios.set(tblUsuario.getSelectionModel().getSelectedIndex(),elementoSeleccionado);
                }        
                        txtNombre.setDisable(true);
                        txtNombre.setText("");
                        txtLogin.setText("");
                        txtLogin.setDisable(true);
                        txtPassword.setText("");
                        txtPassword.setDisable(true);
                        txtEmail.setText("");
                        txtEmail.setDisable(true);
                        btnNuevo.setDisable(false);
                        btnGuardar.setDisable(true);
                        btnCancelar.setDisable(true);
                        btnEliminar.setDisable(false);
                        btnEditar.setDisable(false);
                        cmbRol.getSelectionModel().clearSelection();
                        cmbRol.setDisable(true);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void cancelar(){
        txtNombre.setText("");
        txtNombre.setDisable(true);
        txtLogin.setText("");
        txtLogin.setDisable(true);
        txtPassword.setText("");
        txtPassword.setDisable(true);
        txtEmail.setText("");
        txtEmail.setDisable(true);
        cmbRol.getSelectionModel().clearSelection();
        cmbRol.setDisable(true);
        
        
        btnNuevo.setDisable(false);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEliminar.setDisable(false);
        btnEditar.setDisable(false);
    }
    public void eliminar(){
        
        if(tblUsuario.getSelectionModel().getSelectedItem() != null){
            int respuesta = JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar el registro?","Eliminar",JOptionPane.YES_NO_OPTION);            
            if(respuesta == JOptionPane.YES_OPTION){
                usuarioDao.deleteUsuario(tblUsuario.getSelectionModel().getSelectedItem());
                listaUsuarios.remove(tblUsuario.getSelectionModel().getSelectedItem());
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar un elemento");
        }
    }
    public void modificar (){
        if(tblUsuario.getSelectionModel().getSelectedItem() !=null){
            
            txtNombre.setDisable(false);
            txtLogin.setDisable(false);
            txtPassword.setDisable(false);
            txtEmail.setDisable(false);
            
            cmbRol.setDisable(false);
            btnNuevo.setDisable(true);
            btnEliminar.setDisable(true);
            btnEditar.setDisable(false);
            btnGuardar.setDisable(false);
            btnCancelar.setDisable(false);
            accion = ACCIONES.EDITAR;
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar un elemento");
        }
    }
    
    
}
