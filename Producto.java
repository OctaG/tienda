public class Producto extends Objeto{

  //Variables de instancia
  protected int cantidad;
  protected double precio;
  protected String codigoDeBarras;
  protected String categoria;

  //Constructor
  public Producto(String nombre, String codigoDeBarras, int tipo, int cantidad, double precio, String categoria){
    super(nombre, tipo);
    this.codigoDeBarras = codigoDeBarras;
    this.cantidad = cantidad;
    this.precio = precio;
    this.categoria = categoria;
  }

  public Producto(Producto other, int cantidad){
    super(other.getNombre(), other.getTipo());
    this.codigoDeBarras = other.codigoDeBarras;
    this.cantidad = cantidad;
    this.precio = other.precio;
    this.categoria = other.categoria;
  }

  public int getCantidad(){
    return cantidad;
  }

  public double getPrecio(){
    return precio;
  }

  public String getCodigoDeBarras(){
    return codigoDeBarras;
  }


  public void restar(int cantidad){
    this.cantidad -= cantidad;
  }

  public String getStatus(){
    return toString() + ": " +  precio;
  }

}
