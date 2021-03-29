package sample.Vacunacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Inicio.Inicio;
import sample.Main;

import java.io.IOException;

public class Vacunacion {
    @FXML ImageView view;
    @FXML ImageView view2;

    Inicio inicio = new Inicio();

    public void phizer(MouseEvent event) throws IOException {
        inicio.registrarVacuna(1);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,900,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(200);

    }
    public void moderna(MouseEvent event) throws IOException{
        inicio.registrarVacuna(2);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,900,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(200);
    }
    public void entrarphizer(MouseEvent event){
        Effect brillo = new Glow(1);
        view.setEffect(brillo);

    }
    public void entrarmoderna(MouseEvent event){
        Effect brillo = new Glow(1);
        view2.setEffect(brillo);
    }
    public void salir(MouseEvent event){
        Effect brillo = new Glow(0);
        view.setEffect(brillo);
        view2.setEffect(brillo);

    }
}
