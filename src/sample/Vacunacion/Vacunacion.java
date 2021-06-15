package sample.Vacunacion;

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
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Inicio.Inicio;
import sample.Main;
import sample.models.Conexion;
import sample.models.Fila;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Vacunacion {
    @FXML ImageView view;
    @FXML ImageView view2;
    @FXML TextField txtmarca;
    @FXML TextField txtdesc;
    @FXML TextField txtdosis;
    @FXML TextField txtfecha;
    @FXML Button btncancelar;
    @FXML Button btninsertar;
    TableColumn colId = new TableColumn("ID");
    TableColumn colmarca = new TableColumn("MARCA");
    TableColumn coldesc = new TableColumn("DESCRIPCION");
    TableColumn coldosis = new TableColumn("DOSIS");
    TableColumn colfecha = new TableColumn("FECHA-VAC");
    TableColumn colEditar = new TableColumn("     ");
    TableColumn colEliminar = new TableColumn("     ");
    @FXML TableView table;
    Fila filaedit;

    ObservableList<Fila> datos = FXCollections.observableArrayList();
    Callback<TableColumn<Fila,String>, TableCell<Fila,String>> celdaEditar = new Callback<TableColumn<Fila, String>, TableCell<Fila, String>>() {
        @Override
        public TableCell<Fila, String> call(TableColumn<Fila, String> filaStringTableColumn) {
            TableCell<Fila, String> cell = new TableCell<Fila, String>() {
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
                                txtmarca.setText(filaedit.getMarca());
                                txtdesc.setText(filaedit.getDesc());
                                txtdosis.setText(filaedit.getDosis());
                                txtfecha.setText(filaedit.getFecha());
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

    Callback<TableColumn<Fila,String>, TableCell<Fila,String>> celdaEliminar = new Callback<TableColumn<Fila, String>, TableCell<Fila, String>>() {
        @Override
        public TableCell<Fila, String> call(TableColumn<Fila, String> filaStringTableColumn) {
            TableCell<Fila, String> cell = new TableCell<Fila, String>() {
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
                                    Fila fila = getTableView().getItems().get(getIndex());
                                    conexion.insmodel("DELETE FROM vacunas WHERE id = "+fila.getId());
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

        Conexion conexion;
        Inicio inicio = new Inicio();

        @FXML
        protected void initialize() throws SQLException {
            conexion = new Conexion();
            colId.setMinWidth(40);
            colmarca.setMinWidth(110);
            coldesc.setMinWidth(200);
            coldosis.setMinWidth(80);
            colfecha.setMinWidth(100);

            colId.setCellValueFactory(new PropertyValueFactory<Fila, String>("id"));
            colmarca.setCellValueFactory(new PropertyValueFactory<Fila, String>("marca"));
            coldesc.setCellValueFactory(new PropertyValueFactory<Fila, String>("desc"));
            coldosis.setCellValueFactory(new PropertyValueFactory<Fila, String>("dosis"));
            colfecha.setCellValueFactory(new PropertyValueFactory<Fila, String>("fecha"));
            colEditar.setCellFactory(celdaEditar);
            colEliminar.setCellFactory(celdaEliminar);

            table.getColumns().addAll(colId, colmarca, coldesc, coldosis, colfecha, colEditar, colEliminar);
            table.setItems(datos);
            recargar();
        }

        public void recargar() throws SQLException {
            ResultSet res = conexion.consultar("SELECT * from vacunas order by id DESC");
            datos.clear();
            if (res != null) {
                while (res.next()) {
                    datos.add(new Fila(res.getObject("id").toString()
                            , res.getObject("marca").toString(), res.getObject("descripcion").toString()
                            , res.getObject("dosis").toString(), res.getObject("fecha").toString()));
                }
            }
        }

        public void insertar(ActionEvent event) throws SQLException {

            if (btninsertar.getText().equals("ACTUALIZAR")){

                String marca = txtmarca.getText();
                String desc = txtdesc.getText();
                String dosis = txtdosis.getText();
                String fecha = txtfecha.getText();

                conexion.insmodel("UPDATE vacunas set marca ='"+marca+"',descripcion ='"+desc+"'," +
                        "dosis ='"+dosis+"',fecha ='"+fecha+"' WHERE id ="+filaedit.getId());

                btninsertar.setText("INSERTAR");
                txtmarca.setText("");
                txtfecha.setText("");
                txtdesc.setText("");
                txtdosis.setText("");
                btncancelar.setVisible(false);
                recargar();

            }else{
                if (!txtmarca.getText().trim().equals("")
                        && !txtdesc.getText().trim().equals("")
                        && !txtdosis.getText().trim().equals("")
                        && !txtfecha.getText().trim().equals("")) {

                    String marca = txtmarca.getText();
                    String desc = txtdesc.getText();
                    String dosis = txtdosis.getText();
                    String fecha = txtfecha.getText();

                    conexion.insmodel("INSERT INTO vacunas (marca,descripcion,dosis,fecha) VALUES ('" + marca
                            + "','" + desc + "','" + dosis + "','" + fecha + "')");

                    System.out.println("INSERT INTO vacunas (marca,descripcion,dosis,fecha) VALUES ('" + marca
                            + "','" + desc + "','" + dosis + "','" + fecha + "')");

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

        public void phizer(MouseEvent event) throws IOException {
            inicio.registrarVacuna(1);
            Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
            Main.stage.setScene(new Scene(root, 1200, 600));
            Main.stage.setTitle("Vacunate.org");
            Main.stage.setResizable(false);
            Main.stage.setMaximized(false);
            Main.stage.setX(20);

        }

        public void moderna(MouseEvent event) throws IOException {
            inicio.registrarVacuna(2);
            Parent root = FXMLLoader.load(getClass().getResource("../Inicio/inicio.fxml"));
            Main.stage.setScene(new Scene(root, 1200, 600));
            Main.stage.setTitle("Vacunate.org");
            Main.stage.setResizable(false);
            Main.stage.setMaximized(false);
            Main.stage.setX(20);
        }

        public void entrarphizer(MouseEvent event) {
            Effect brillo = new Glow(1);
            view.setEffect(brillo);

        }

        public void entrarmoderna(MouseEvent event) {
            Effect brillo = new Glow(1);
            view2.setEffect(brillo);
        }

        public void salir(MouseEvent event) {
            Effect brillo = new Glow(0);
            view.setEffect(brillo);
            view2.setEffect(brillo);

        }

        public void cancelar(){
            if (filaedit!=null){
                btninsertar.setText("INSERTAR");
                txtmarca.setText("");
                txtfecha.setText("");
                txtdesc.setText("");
                txtdosis.setText("");
                btncancelar.setVisible(false);
            }
        }

    }
