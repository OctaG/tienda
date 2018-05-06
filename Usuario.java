public class Usuario extends Objeto{

  private String contrasena;

  public Usuario(String nombre, String contrasena, int tipo){
    super(nombre, tipo);
    this.contrasena = contrasena;
  }

  public int getTipo(){
    return tipo;
  }

  public boolean contrasenaCorrecta(String contrasena){
    if(this.contrasena.equals(contrasena)){
      return true;
    }
    return false;
  }

  public String getStatus(){
    return toString() + contrasena;
  }
}
