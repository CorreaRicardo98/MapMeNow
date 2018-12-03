package e.ricardo.myapplication;

public class Usuario {
    private String id,nombre,usuario,pass,ape_p,ape_m,sex,email;

    public Usuario(String id, String nombre, String usuario, String pass, String ape_p, String ape_m, String sex, String email) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.ape_p = ape_p;
        this.ape_m = ape_m;
        this.sex = sex;
        this.email = email;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setApe_p(String ape_p) {
        this.ape_p = ape_p;
    }

    public void setApe_m(String ape_m) {
        this.ape_m = ape_m;
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

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
    }

    public String getApe_p() {
        return ape_p;
    }

    public String getApe_m() {
        return ape_m;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }
}
