package sample.models;

import javafx.beans.property.SimpleStringProperty;

public class Fila {
    private SimpleStringProperty id;
    private SimpleStringProperty marca;
    private SimpleStringProperty desc;
    private SimpleStringProperty dosis;
    private SimpleStringProperty fecha;

    public Fila(String id, String marca, String desc, String dosis, String fecha) {
        this.id = new SimpleStringProperty(id);
        this.marca = new SimpleStringProperty(marca);
        this.desc = new SimpleStringProperty(desc);
        this.dosis = new SimpleStringProperty(dosis);
        this.fecha = new SimpleStringProperty(fecha);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getMarca() {
        return marca.get();
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getDesc() {
        return desc.get();
    }

    public SimpleStringProperty descProperty() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public String getDosis() {
        return dosis.get();
    }

    public SimpleStringProperty dosisProperty() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis.set(dosis);
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }
}
