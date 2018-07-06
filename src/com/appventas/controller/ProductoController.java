
package com.appventas.controller;


import com.appventas.dao.CategoriaDaoImpl;
import com.appventas.dao.ProductoDao;
import com.appventas.dao.ProductoDaoImpl;
import com.appventas.modelo.Categoria;
import com.appventas.modelo.Producto;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


public class ProductoController implements Initializable{
    
    @FXML private TableView<Producto> tblProducto;
    @FXML private TableColumn<Producto, Number> colCodigoProducto;
    @FXML private TableColumn<Producto, Number> colPrecio;
    @FXML private TableColumn<Producto,String>colDescripcion;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, Number> colPrecioUnidad;
    @FXML private TableColumn<Producto, Number> colExistencias;
    
   @FXML private ComboBox cbxCategorias;
    
    @FXML private Button btnNuevo;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Button btnEliminar;
    
    @FXML private TextField txtPrecio;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecioUnitario;
    @FXML private TextField txtExistencias;

    @FXML private ObservableList<Producto> listaProductos;
    @FXML private ObservableList<Categoria> listaCategorias;
    @FXML private ObservableList<String> listaDescripciones;
    
    private ProductoDaoImpl productoDao = new ProductoDaoImpl();
    private CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getProductos();
        enlazarDatos();
        enlazarColumnas();
        mostrarCategorias();
    }
    
    public void enlazarDatos() {
        tblProducto.setItems(listaProductos);
        
    }
    public  void enlazarColumnas() {
       colCodigoProducto.setCellValueFactory(cellData->cellData.getValue().codigoProducto());
       colPrecio.setCellValueFactory(cellData->cellData.getValue().precio());
       colDescripcion.setCellValueFactory(cellData->cellData.getValue().descripcion());        
       colCategoria.setCellValueFactory(cellData->cellData.getValue().getCategoria().descripcion());
       colPrecioUnidad.setCellValueFactory(cellData->cellData.getValue().precioUnitario());
       colExistencias.setCellValueFactory(cellData->cellData.getValue().existencias());
    }
    public void nuevo(){
       txtDescripcion.setDisable(false);
        txtExistencias.setDisable(false);
        txtPrecio.setDisable(false);
        txtPrecioUnitario.setDisable(false);
        
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        btnCancelar.setDisable(false);
        btnEliminar.setDisable(true);
    }
    public void guardar(){
        try {
            Producto producto = new Producto();
            for (Categoria categoria: listaCategorias)
                if (categoria.getDescripcion().equals(cbxCategorias.getSelectionModel().getSelectedItem())){
                    producto.setCategoria(categoria);
                }
            
            
            producto.setDescripcion(txtDescripcion.getText());
            producto.setPrecio(Integer.parseInt(txtPrecio.getText()));
            producto.setExistencias(Integer.parseInt(txtExistencias.getText()));
            producto.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
           
            productoDao.saveProducto(producto);
            listaProductos.add(producto);
            txtDescripcion.setDisable(true);
            txtDescripcion.setText("");
        
            txtExistencias.setText("");
            txtExistencias.setDisable(true);
            txtPrecioUnitario.setText("");
            txtPrecioUnitario.setDisable(true);
            txtPrecio.setText("");
            txtPrecio.setDisable(true);
            btnNuevo.setDisable(false);
            btnGuardar.setDisable(true);
            btnCancelar.setDisable(true);
            btnEliminar.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cancelar(){
        txtDescripcion.setText("");
        txtDescripcion.setDisable(true);
        txtExistencias.setText("");
        txtExistencias.setDisable(true);
        txtPrecioUnitario.setText("");
        txtPrecioUnitario.setDisable(true);
        txtPrecio.setText("");
        txtPrecio.setDisable(true);
        
        
        btnNuevo.setDisable(false);
        btnGuardar.setDisable(true);
        btnCancelar.setDisable(true);
        btnEliminar.setDisable(false);
    }
    
    public void eliminar(){
        
        if(tblProducto.getSelectionModel().getSelectedItem() != null){
            int respuesta = JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar el registro?","Eliminar",JOptionPane.YES_NO_OPTION);            
            if(respuesta == JOptionPane.YES_OPTION){
                productoDao.deleteProducto(tblProducto.getSelectionModel().getSelectedItem());
                listaProductos.remove(tblProducto.getSelectionModel().getSelectedItem());
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar un elemento");
        }
        
    }

    public void getProductos() {
        listaProductos = FXCollections.observableArrayList(productoDao.findAllProducto());
    }

    private void mostrarCategorias() {
        listaCategorias= FXCollections.observableArrayList(categoriaDao.findAllCategoria());
        listaDescripciones= FXCollections.observableArrayList(listaCategorias.stream().map(categoria->categoria.getDescripcion()).collect(Collectors.toList()));
        cbxCategorias.setItems(listaDescripciones);
    }
}
  