package sample.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Inicio.Inicio;
import sample.Main;
import sample.Usuario_clase;
import sample.models.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    @FXML AnchorPane pane;
    @FXML TextField txtusuario;
    @FXML TextField txtnombre;
    @FXML TextField txtapellido;
    @FXML PasswordField txtpassword;
    @FXML TextField txtemail;

    Inicio inicio = new Inicio();
    private Conexion conexion;

    @FXML protected void initialize(){
        conexion = new Conexion();
    }

    public void registrar(ActionEvent event) throws IOException, SQLException {

        if (txtpassword.getText().equals("") || txtapellido.getText().equals("")) {

        } else if (txtapellido.getText().equals("") || txtemail.getText().equals("")) {

        } else if (txtusuario.getText().equals("")) {

        } else {

            String email = txtemail.getText();
            String password = txtpassword.getText();

            ResultSet resultSet = conexion.consultar("select * from users where email='" + email + "' and password='" + password + "'");

            if (resultSet != null) {
                int cont = 0;
                while (resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Acceso al sistema");
                    alert.setHeaderText("");
                    alert.setContentText("Bienvenido " + resultSet.getObject("name"));
                    alert.show();
                    cont++;
                }
                if (cont == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR DE ACCESO AL SISTEMA");
                    alert.setHeaderText("");
                    alert.setContentText("Credenciales no validas");
                    alert.show();
                } else {
                    Usuario_clase usuario = new Usuario_clase(txtusuario.getText(),
                            txtnombre.getText(), txtapellido.getText(), password, txtemail.getText());
                    inicio.registrarUsuario(usuario);
                    Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
                    Main.stage.setScene(new Scene(root, 1200, 600));
                    Main.stage.setTitle("Vacunate.org");
                    Main.stage.setResizable(false);
                    Main.stage.setMaximized(false);
                    Main.stage.setX(20);
                }
            }


        }

    }


    public void verificar(ActionEvent event){

        if (txtpassword.getText().equals("pass123")&&txtemail.getText().equals("user1@gmail.com")){

            txtusuario.setText("user1");
            txtnombre.setText("Nombre");
            txtapellido.setText("Apellido");

        }else if(txtpassword.getText().equals("pass1234")&&txtemail.getText().equals("user2@gmail.com")){

            txtusuario.setText("user2");
            txtnombre.setText("Nombre2");
            txtapellido.setText("Apellido2");
        }else {
            txtusuario.setText("");
            txtnombre.setText("");
            txtapellido.setText("");
        }

    }


    }

