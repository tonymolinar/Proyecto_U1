package sample.Inicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import sample.Main;
import sample.Usuario_clase;
import sample.models.Conexion;
import sample.models.Fila;
import sample.models.Fila3;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inicio {


    @FXML AnchorPane pane;
    @FXML Button bvacuna;
    @FXML Button bloca;
    @FXML Button bdiag;
    @FXML TextArea textousuario;

    @FXML TextField txtusuario;
    @FXML TextField txtvacuna;
    @FXML TextField txtlugar;
    @FXML TextField txtdiagnostico;
    Conexion conexion;
    TableColumn colId = new TableColumn("ID");
    TableColumn coluser = new TableColumn("USUARIO");
    TableColumn colvacuna = new TableColumn("VACUNA");
    TableColumn collugar = new TableColumn("LUGAR");
    TableColumn coldiag = new TableColumn("DIAGNOSTICO");
    @FXML TableView table;
    ObservableList<Fila3> datos = FXCollections.observableArrayList();

    static String vacuna;
    static String lugar;
    static int diagnostico;
    static Usuario_clase user = null;

    @FXML
    protected void initialize() throws SQLException {

        txtdiagnostico.setEditable(false);
        txtusuario.setEditable(false);
        txtvacuna.setEditable(false);
        txtlugar.setEditable(false);

        conexion = new Conexion();
        colId.setMinWidth(10);
        coluser.setMinWidth(50);
        colvacuna.setMinWidth(70);
        collugar.setMinWidth(60);
        coldiag.setMinWidth(60);

        colId.setCellValueFactory(new PropertyValueFactory<Fila3, String>("id"));
        coluser.setCellValueFactory(new PropertyValueFactory<Fila3, String>("user"));
        colvacuna.setCellValueFactory(new PropertyValueFactory<Fila3, String>("vacuna"));
        collugar.setCellValueFactory(new PropertyValueFactory<Fila3, String>("lugar"));
        coldiag.setCellValueFactory(new PropertyValueFactory<Fila3, String>("diagnostico"));

        table.getColumns().addAll(colId, coluser, colvacuna, collugar, coldiag);
        table.setItems(datos);
        recargar();
    }

    public void recargar() throws SQLException {
        ResultSet res = conexion.consultar("SELECT * from registros order by id DESC");
        datos.clear();
        if (res != null) {
            while (res.next()) {
                datos.add(new Fila3(res.getObject("id").toString()
                        , res.getObject("user").toString(), res.getObject("vacuna").toString()
                        , res.getObject("lugar").toString(), res.getObject("diagnostico").toString()));
            }
        }
    }

    public void insertar(ActionEvent event) throws SQLException {

            if (!txtvacuna.getText().trim().equals("")
                    && !txtlugar.getText().trim().equals("")
                    && !txtusuario.getText().trim().equals("")
                    && !txtdiagnostico.getText().trim().equals("")) {

                String user = txtusuario.getText();
                String lugar = txtlugar.getText();
                String vacuna = txtvacuna.getText();
                String diag = txtdiagnostico.getText();

                conexion.insmodel("INSERT INTO registros (user,vacuna,lugar,diagnostico) VALUES ('" + user
                        + "','" + vacuna + "','" + lugar + "','" + diag + "')");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("REGISTRO EXITOSO");
                alert.setHeaderText("");
                alert.setContentText("El registro fue insertado correctamente");
                alert.show();
                recargar();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR DE REGISTRO");
                alert.setHeaderText("");
                alert.setContentText("Favor de llenar todos los campos");
                alert.show();
            }
        }

    public void pantallaUsuario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Usuario/usuario.fxml"));
        Main.stage.setScene(new Scene(root,500,500));
        Main.stage.setTitle("Registro");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(400);
        Main.stage.setY(50);
    }

    public void info(int valor){
        Effect brillo = new Glow(0.5);
        Effect normal = new Glow(0);
        TextArea textArea = new TextArea();
        TextArea textArea2 = new TextArea();
        textArea.setEditable(false); textArea2.setEditable(false);
        textArea.setWrapText(true); textArea.setLayoutX(245); textArea.setLayoutY(180);
        textArea.setFont(new Font("Bell MT",25));
        textArea2.setFont(new Font("Bell MT",25));
        textArea2.setWrapText(true); textArea2.setLayoutX(221); textArea2.setLayoutY(420);
        textArea.setPrefSize(383,201);
        textArea2.setPrefSize(438,115);
        if (valor==1){
            bvacuna.setEffect(brillo);
            textArea.setText("Vacunación");
            textArea.setFont(new Font(50));
            textArea2.setStyle("-fx-text-fill: green;-fx-border-color: blue");
            textArea2.setText("Infórmate acerca de las vacunas disponibles y sus características.");
        }else if (valor==2){
            bloca.setEffect(brillo);
            textArea.setFont(new Font(50));
            textArea.setText("Instalaciones");
            textArea2.setStyle("-fx-text-fill: green;-fx-border-color: blue");
            textArea2.setText("Obtén información acerca de los centros de vacunación dentro de tu comunidad.");
        }else if (valor==3){
            bdiag.setEffect(brillo);
            textArea.setFont(new Font(50));
            textArea.setText("Diagnóstico");
            textArea2.setStyle("-fx-text-fill: green;-fx-border-color: blue");
            textArea2.setText("Averigua si eres apto para recibir la vacuna.");
        }else if (valor==0){
            bvacuna.setEffect(normal);
            bloca.setEffect(normal);
            bdiag.setEffect(normal);
            textArea2.setText("Para acceder a nuestros servicios favor de registrarse haciendo clic en el icono de la esquina superior derecha.");
            textArea2.setStyle("-fx-text-fill: red;-fx-border-color: blue");
            textArea.setText("Este es un sitio creado sin fines de lucro con la intención de ayudar y felicitar a las personas para que tengan información completa del proceso de vacunación contra el Covid-19.");
        }
        pane.getChildren().addAll(textArea,textArea2);

    }
    public void mostrarinfovac(MouseEvent event){
        info(1);
    }
    public void mostrarinfoloc(MouseEvent event){
        info(2);
    }
    public void mostrarinfodia(MouseEvent event){
        info(3);
    }
    public void cerrarinfo(MouseEvent event){
        info(0);
    }

    public void registrarUsuario(Usuario_clase usuario){
        user = usuario;

    }
    public void desbloq(ActionEvent event){
        if (user!=null){
            bvacuna.setDisable(false);
            bloca.setDisable(false);
            bdiag.setDisable(false);
        }
    }

    public void mostrarRegistro(ActionEvent event){
        String apto="";
        if (diagnostico==1){
            apto="Es apto para vacuanrse";
        }else if (diagnostico==2){
            apto="No es apto para vacuanrse";
        }

        if (user==null){

        }else if(vacuna!=null&&lugar!=null&&diagnostico!=0){
            textousuario.setText(
                    "*REGISTRO*"+"\n"+
                            "Nombre de usuario: "+user.getUsuario()+"\n"+
                            "Nombre: "+user.getNombre()+"\n"+
                            "Apellido: "+user.getApellido()+"\n"+
                            "Contraseña: "+user.getPassword()+"\n"+
                            "Email: "+user.getEmail()+"\n"+
                            "Centro de vacunación: "+lugar+"\n"+
                            "Vacuna elegida: "+vacuna+"\n"+
                            "Estatus: "+apto);

            txtdiagnostico.setText(apto);
            txtusuario.setText(user.getUsuario());
            txtlugar.setText(lugar);
            txtvacuna.setText(vacuna);
        }


    }

    public void cambiarLugares(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Lugares/lugares.fxml"));
        Main.stage.setScene(new Scene(root,1230,600));
        Main.stage.setTitle("Localizaciones");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(20);
        Main.stage.setY(50);
    }
    public void cambiarVacunas(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Vacunacion/vacunacion.fxml"));
        Main.stage.setScene(new Scene(root,1200,600));
        Main.stage.setTitle("Vacunas");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(50);
        Main.stage.setY(50);
    }

    public void cambiarDiagnostico(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Diagnostico/diagnostico.fxml"));
        Main.stage.setScene(new Scene(root,600,555));
        Main.stage.setTitle("Diagnostico");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(300);
        Main.stage.setY(50);
    }

    public void registrarLugar(int valor){
        if (valor==1){
            lugar="Cetis 93";
        }else if (valor==2){
            lugar="Centro de convenciones";
        }
    }

    public void registrarVacuna(int valor){
        if (valor==1){
            vacuna="Pfizer-BioNTech";
        }else if (valor==2){
            vacuna="Moderna";
        }
    }

    public void registrarDiagnostico(int valor){
        if (valor==1){
            diagnostico= 1;
        }else if (valor==2){
            diagnostico= 2;
        }
    }

}


