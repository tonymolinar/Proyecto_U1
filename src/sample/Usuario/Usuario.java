package sample.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Inicio.Inicio;
import sample.Main;
import sample.Usuario_clase;

import java.io.IOException;

public class Usuario {
    @FXML AnchorPane pane;
    @FXML TextField txtusuario;
    @FXML TextField txtnombre;
    @FXML TextField txtapellido;
    @FXML TextField txtedad;
    @FXML TextField txttelefono;

    Inicio inicio = new Inicio();

    public void registrar(ActionEvent event) throws IOException {

        if (txtedad.getText().equals("")||txtapellido.getText().equals("")){

        }else if (txtapellido.getText().equals("")||txttelefono.getText().equals("")){

        }else if (txtusuario.getText().equals("")){

        }else {
            int edad = Integer.parseInt(txtedad.getText());
            Usuario_clase usuario = new Usuario_clase(txtusuario.getText(),txtnombre.getText(),txtapellido.getText(),edad,txttelefono.getText());
            inicio.registrarUsuario(usuario);
            Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
            Main.stage.setScene(new Scene(root,900,600));
            Main.stage.setTitle("Vacunate.org");
            Main.stage.setResizable(false);
            Main.stage.setMaximized(false);
            Main.stage.setX(200);
        }










    }



}
