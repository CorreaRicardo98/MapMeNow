package e.ricardo.myapplication;

public class Usuario {

    private String id1,mensaje;

    private String id,nombre,pass,ape,sex,email;

    public Usuario(String id, String nombre, String pass, String ape, String sex, String email) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.ape = ape;
        this.sex = sex;
        this.email = email;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setApe_p(String ape) {
        this.ape = ape;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {

        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public String getApe_p() {
        return ape;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }
}
