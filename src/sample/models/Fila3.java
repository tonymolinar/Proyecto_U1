package sample.models;

import javafx.beans.property.SimpleStringProperty;

public class Fila3 {

    private SimpleStringProperty id;
    private SimpleStringProperty user;
    private SimpleStringProperty vacuna;
    private SimpleStringProperty lugar;
    private SimpleStringProperty diagnostico;

    public Fila3(String id, String user, String vacuna, String lugar, String diagnostico) {
        this.id = new SimpleStringProperty(id);
        this.user = new SimpleStringProperty(user);
        this.vacuna = new SimpleStringProperty(vacuna);
        this.lugar = new SimpleStringProperty(lugar);
        this.diagnostico = new SimpleStringProperty(diagnostico);
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

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getVacuna() {
        return vacuna.get();
    }

    public SimpleStringProperty vacunaProperty() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna.set(vacuna);
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

    public String getDiagnostico() {
        return diagnostico.get();
    }

    public SimpleStringProperty diagnosticoProperty() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico.set(diagnostico);
    }
}
