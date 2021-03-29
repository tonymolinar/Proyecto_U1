package sample.Diagnostico;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Inicio.Inicio;
import sample.Main;

import java.io.IOException;

public class Diagnostico {

    Inicio inicio = new Inicio();

    public void si(ActionEvent event) throws IOException {
        inicio.registrarDiagnostico(1);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,900,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(200);


    }
    public void no(ActionEvent event)throws IOException{
        inicio.registrarDiagnostico(2);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,900,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(200);

    }

}
