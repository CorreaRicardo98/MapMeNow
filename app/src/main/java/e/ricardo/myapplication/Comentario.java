package e.ricardo.myapplication;

public class Comentario {
    private int id;
    private String mensaje;

    public Comentario(int id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getId() {

        return id;
    }

    public String getMensaje() {
        return mensaje;
    }
}
