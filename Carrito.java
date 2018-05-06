public class Carrito extends BaseDeDatos{

  public double calcularTotal(){
    double total = 0.0;
    for(Objeto o : lista){
      Producto producto = (Producto) o;
      total += producto.getPrecio() * producto.getCantidad();
    }
    return total;
  }

  public void imprimePrecio(){
    for(Objeto o : lista){
      Producto producto = (Producto) o;
      System.out.println(producto.getNombre() + ": " + producto.getPrecio() + ", ");
    }
  }

}
