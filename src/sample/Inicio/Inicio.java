package sample.Inicio;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import sample.Main;
import sample.Usuario_clase;

import java.io.IOException;

public class Inicio {


    @FXML AnchorPane pane;
    @FXML Button bvacuna;
    @FXML Button bloca;
    @FXML Button bdiag;
    @FXML TextArea textousuario;

    static String vacuna;
    static String lugar;
    static boolean diagnostico;
    static Usuario_clase user = null;

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

    public void mostrarRegistro(ActionEvent event){
        String apto="";
        if (diagnostico){
            apto="Es apto para vacuanrse";
        }else if (diagnostico==false){
            apto="No es apto para vacuanrse";
        }

        if (user==null){

        }else if(vacuna!=null&&lugar!=null){
            textousuario.setText(
                    "*REGISTRO*"+"\n"+
                            "Nombre de usuario: "+user.getUsuario()+"\n"+
                            "Nombre: "+user.getNombre()+"\n"+
                            "Apellido: "+user.getApellido()+"\n"+
                            "Edad: "+user.getEdad()+"\n"+
                            "# Teléfono: "+user.getTelefono()+"\n"+
                            "Centro de vacunación: "+lugar+"\n"+
                            "Vacuna elegida: "+vacuna+"\n"+
                            "Estatus: "+apto);
        }


    }

    public void cambiarLugares(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Lugares/lugares.fxml"));
        Main.stage.setScene(new Scene(root,800,600));
        Main.stage.setTitle("Localizaciones");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(300);
        Main.stage.setY(50);
    }
    public void cambiarVacunas(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Vacunacion/vacunacion.fxml"));
        Main.stage.setScene(new Scene(root,800,600));
        Main.stage.setTitle("Vacunas");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(300);
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
            vacuna="Vacuna de Pfizer- BioNTech contra la COVID-19. ";
        }else if (valor==2){
            vacuna="Vacuna de Moderna contra la COVID-19. ";
        }
    }

    public void registrarDiagnostico(int valor){
        if (valor==1){
            diagnostico= true;
        }else if (valor==2){
            diagnostico= false;
        }
    }
}


