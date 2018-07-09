
package com.appventas.sistema;

import com.appventas.controller.CategoriaController;
import com.appventas.controller.ProductoController;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppVentas extends Application {

    private final String PAQUETE_VISTA = "/com/appventas/view/";
    private Stage escenarioPrincipal;
   
    @Override
    public void start(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

       //mostrarHelloWorld();
        mostrarCategoria();
        //mostrarProducto();
        //mostrarCategoria();
        this.escenarioPrincipal.setTitle("Ventas");
        this.escenarioPrincipal.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

   public void mostrarCategoria() {
        try {
            CategoriaController categoriaController = (CategoriaController) 
                    cambiarEscena("Categoria.fxml", 600, 400);

        } catch (Exception e) {
        }
    }
    
    private void mostrarProducto() {
        try {
            ProductoController productoController = (ProductoController)
                cambiarEscena("Producto.fxml", 600,400);
        } catch (Exception e) {
        }
        
    }

    
     

    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws IOException {
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = AppVentas.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(AppVentas.class.getResource(PAQUETE_VISTA + fxml));
        Scene escena = new Scene((AnchorPane) cargadorFXML.load(archivo), ancho, alto);
        this.escenarioPrincipal.setScene(escena);
        this.escenarioPrincipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }

    
   

}

