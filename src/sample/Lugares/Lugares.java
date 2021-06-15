package sample.Lugares;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import sample.Inicio.Inicio;
import sample.Main;
import sample.models.Conexion;
import sample.models.Fila;
import sample.models.Fila2;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Lugares {

    @FXML AnchorPane pane;
    @FXML TextField txtcalle;
    @FXML TextField txtnumero;
    @FXML ComboBox cbcolonia;
    @FXML ComboBox cblugares;
    @FXML Button btncancelar;
    @FXML Button btninsertar;

    TableColumn colId = new TableColumn("ID");
    TableColumn colcolonia = new TableColumn("COLONIA");
    TableColumn colcalle = new TableColumn("CALLE");
    TableColumn colnumero = new TableColumn("NUMERO");
    TableColumn collugar = new TableColumn("LUGAR");
    TableColumn colEditar = new TableColumn("     ");
    TableColumn colEliminar = new TableColumn("     ");
    @FXML TableView table;

    Inicio inicio = new Inicio();
    Conexion conexion;

    Fila2 filaedit;
    ObservableList<Fila2> datos = FXCollections.observableArrayList();

    @FXML
    protected void initialize() throws SQLException {
        conexion = new Conexion();
        colId.setMinWidth(30);
        colcolonia.setMinWidth(90);
        colcalle.setMinWidth(80);
        colnumero.setMinWidth(50);
        collugar.setMinWidth(100);

        cblugares.getItems().add(0,"CETIS 93");
        cblugares.getItems().add(1,"CENTRO DE CONVENCIONES");

        cbcolonia.getItems().add(0,"CENTRO");
        cbcolonia.getItems().add(1,"DUBLAN");
        cbcolonia.getItems().add(2,"VISTAS DEL SOL");
        cbcolonia.getItems().add(3,"ALAMEDAS");
        cbcolonia.getItems().add(4,"NUEVO DUBLAN");
        cbcolonia.getItems().add(5,"OBRERA");
        cbcolonia.getItems().add(6,"BUROCRATA");
        cbcolonia.getItems().add(7,"ACCION POPULAR");
        cbcolonia.getItems().add(8,"COLOSIO");
        cbcolonia.getItems().add(9,"PRI");

        colId.setCellValueFactory(new PropertyValueFactory<Fila2, String>("id"));
        colcolonia.setCellValueFactory(new PropertyValueFactory<Fila2, String>("colonia"));
        colcalle.setCellValueFactory(new PropertyValueFactory<Fila2, String>("calle"));
        colnumero.setCellValueFactory(new PropertyValueFactory<Fila2, String>("numero"));
        collugar.setCellValueFactory(new PropertyValueFactory<Fila2, String>("lugar"));
        colEditar.setCellFactory(celdaEditar);
        colEliminar.setCellFactory(celdaEliminar);

        table.getColumns().addAll(colId, colcolonia, colcalle, colnumero, collugar, colEditar, colEliminar);
        table.setItems(datos);
        recargar();

    }

    Callback<TableColumn<Fila2,String>, TableCell<Fila2,String>> celdaEditar = new Callback<TableColumn<Fila2, String>, TableCell<Fila2, String>>() {
        @Override
        public TableCell<Fila2, String> call(TableColumn<Fila2, String> fila2StringTableColumn) {
            TableCell<Fila2, String> cell = new TableCell<Fila2, String>() {
                Button btneditar = new Button("Editar");

                @Override
                protected void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btneditar.getStyleClass().add("btnaceptar");
                        setGraphic(btneditar);
                        setText(null);
                        btneditar.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                filaedit = getTableView().getItems().get(getIndex());
                                cbcolonia.getSelectionModel().select(filaedit.getColonia());
                                cblugares.getSelectionModel().select(filaedit.getLugar());
                                txtcalle.setText(filaedit.getCalle());
                                txtnumero.setText(filaedit.getNumero());
                                btncancelar.setVisible(true);
                                btninsertar.setText("ACTUALIZAR");
                            }
                        });
                    }
                }
            };
            return cell;
        }
    };

    Callback<TableColumn<Fila2,String>, TableCell<Fila2,String>> celdaEliminar = new Callback<TableColumn<Fila2, String>, TableCell<Fila2, String>>() {
        @Override
        public TableCell<Fila2, String> call(TableColumn<Fila2, String> fila2StringTableColumn) {
            TableCell<Fila2, String> cell = new TableCell<Fila2, String>() {
                Button btnEliminar = new Button("Eliminar");

                @Override
                protected void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEliminar.getStyleClass().add("btnrechazar");
                        setGraphic(btnEliminar);
                        setText(null);
                        btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Eliminar registro");
                                alert.setContentText("Desea eliminar el registro?");
                                Optional<ButtonType> resultado = alert.showAndWait();
                                if (resultado.get()==ButtonType.OK){
                                    Fila2 fila = getTableView().getItems().get(getIndex());
                                    conexion.insmodel("DELETE FROM lugares WHERE id = "+fila.getId());
                                    try {
                                        recargar();
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            };
            return cell;
        }
    };

    public void insertar(ActionEvent event) throws SQLException {

        if (btninsertar.getText().equals("ACTUALIZAR")){

            String colonia = cbcolonia.getSelectionModel().getSelectedItem().toString();
            String calle = txtcalle.getText();
            String numero = txtnumero.getText();
            String lugar = cblugares.getSelectionModel().getSelectedItem().toString();

            conexion.insmodel("UPDATE lugares set colonia ='"+colonia+"',calle ='"+calle+"'," +
                    "numero ='"+numero+"',lugar ='"+lugar+"' WHERE id ="+filaedit.getId());

            btninsertar.setText("INSERTAR");
            cbcolonia.getSelectionModel().clearSelection();
            cblugares.getSelectionModel().clearSelection();
            txtnumero.setText("");
            txtcalle.setText("");
            btncancelar.setVisible(false);
            recargar();


        }else{
            if (!txtcalle.getText().trim().equals("")
                    && !txtnumero.getText().trim().equals("")
                    && cblugares.getSelectionModel().getSelectedItem()!=null
                    && cbcolonia.getSelectionModel().getSelectedItem()!=null) {

                String colonia = cbcolonia.getSelectionModel().getSelectedItem().toString();
                String calle = txtcalle.getText();
                String numero = txtnumero.getText();
                String lugar = cblugares.getSelectionModel().getSelectedItem().toString();

                conexion.insmodel("INSERT INTO lugares (colonia,calle,numero,lugar) VALUES ('" + colonia
                        + "','" + calle + "','" + numero + "','" + lugar + "')");

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
    }

    public void recargar() throws SQLException {
        ResultSet res = conexion.consultar("SELECT * from lugares order by id DESC");
        datos.clear();
        if (res != null) {
            while (res.next()) {
                datos.add(new Fila2(res.getObject("id").toString(),res.getObject("colonia").toString(),
                        res.getObject("calle").toString(),res.getObject("numero").toString(),res.getObject("lugar").toString()));
            }
        }
    }



    public void cetis(MouseEvent event){
        ImageView imageView = new ImageView();
        Image image = new Image("file:src/sample/IMG/imagencetis.png");
        imageView.setImage(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setLayoutX(420);
        imageView.setLayoutY(175);
        pane.getChildren().add(imageView);
    }
    public void convenciones(MouseEvent event){
        ImageView imageView = new ImageView();
        Image image = new Image("file:src/sample/IMG/Imagenconvenciones.png");
        imageView.setImage(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setLayoutX(420);
        imageView.setLayoutY(175);
        pane.getChildren().add(imageView);
    }
    public void quitar(MouseEvent event){
        ImageView imageView = new ImageView();
        Image image = new Image("file:src/sample/IMG/Imagenblanco.png");
        imageView.setImage(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setLayoutX(420);
        imageView.setLayoutY(175);
        pane.getChildren().add(imageView);
    }

    public void botonConvenciones(ActionEvent event) throws IOException{
        inicio.registrarLugar(2);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,1200,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(20);

    }
    public void botonCetis(ActionEvent event) throws IOException{
        inicio.registrarLugar(1);
        Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
        Main.stage.setScene(new Scene(root,1200,600));
        Main.stage.setTitle("Vacunate.org");
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setX(20);

    }


    public void cancelar(){
        if (filaedit!=null){
            btninsertar.setText("INSERTAR");
            txtnumero.setText("");
            txtcalle.setText("");
            cbcolonia.getSelectionModel().clearSelection();
            cblugares.getSelectionModel().clearSelection();
            btncancelar.setVisible(false);
        }
    }
}
