package sample.models;

import javafx.beans.property.SimpleStringProperty;

public class Fila2 {

    private SimpleStringProperty id;
    private SimpleStringProperty colonia;
    private SimpleStringProperty calle;
    private SimpleStringProperty numero;
    private SimpleStringProperty lugar;

    public Fila2(String id, String colonia, String calle, String numero, String lugar) {
        this.id = new SimpleStringProperty(id);
        this.colonia = new SimpleStringProperty(colonia);
        this.calle = new SimpleStringProperty(calle);
        this.numero = new SimpleStringProperty(numero);
        this.lugar = new SimpleStringProperty(lugar);
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

    public String getColonia() {
        return colonia.get();
    }

    public SimpleStringProperty coloniaProperty() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia.set(colonia);
    }

    public String getCalle() {
        return calle.get();
    }

    public SimpleStringProperty calleProperty() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle.set(calle);
    }

    public String getNumero() {
        return numero.get();
    }

    public SimpleStringProperty numeroProperty() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public String getLugar() {
        return lugar.get();
    }

    public SimpleStringProperty lugarProperty() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar.set(lugar);
    }
}
