package e.ricardo.myapplication;

public class Comentario {
    private String id;
    private String mensaje;

    public Comentario(String id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId() {

        return id;
    }

    public String getMensaje() {
        return mensaje;
    }
}
