package sample.Lugares;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Inicio.Inicio;
import sample.Main;

import java.io.IOException;

public class Lugares {

    @FXML AnchorPane pane;

    Inicio inicio = new Inicio();

    public void cetis(MouseEvent event){
        ImageView imageView = new ImageView();
        Image image = new Image("file:src/sample/IMG/imagencetis.png");
        imageView.setImage(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setLayoutX(450);
        imageView.setLayoutY(175);
        pane.getChildren().add(imageView);
    }
    public void convenciones(MouseEvent event){
        ImageView imageView = new ImageView();
        Image image = new Image("file:src/sample/IMG/Imagenconvenciones.png");
        imageView.setImage(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setLayoutX(450);
        imageView.setLayoutY(175);
        pane.getChildren().add(imageView);
    }
    public void quitar(MouseEvent event){
        ImageView imageView = new ImageView();
        Image image = new Image("file:src/sample/IMG/Imagenblanco.png");
        imageView.setImage(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setLayoutX(450);
        imageView.setLayoutY(175);
        pane.getChildren().add(imageView);
    }

    public void botonConvenciones(ActionEvent event) throws IOException{
        inicio.registrarLugar(2);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,900,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(200);

    }
    public void botonCetis(ActionEvent event) throws IOException{
        inicio.registrarLugar(1);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,900,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(200);

    }
}
