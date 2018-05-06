//TODO: Escoger un mejor nombre para esta clase
public abstract class Objeto{

  protected String nombre;
  protected int tipo;


  public Objeto(String nombre, int tipo){
    this.nombre = nombre;
    this.tipo = tipo;
  }

  public String getNombre(){
    return nombre;
  }

  public int getTipo(){
    return tipo;
  }

  public String toString(){
    return nombre + " " + tipo;
  }

  public abstract String getStatus();

}
