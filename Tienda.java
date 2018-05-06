import java.util.Scanner;
import java.util.ArrayList;
public class Tienda{
  public static void main(String[] args){
    BaseDeDatos inventario = new BaseDeDatos();
    BaseDeDatos usuarios = new BaseDeDatos();
    Carrito carrito = new Carrito();
    Scanner lectura = new Scanner(System.in);
    int opcion;

    Usuario user1 = new Usuario("AdminInv","1234", 1);
    Usuario user2 = new Usuario("Vendedor","1234", 2);
    Usuario user3 = new Usuario("AdminUsu","1234", 3);
    usuarios.agregar(user1);
    usuarios.agregar(user2);
    usuarios.agregar(user3);

    while(true){
     System.out.println("Bienvenido a la tiendita, selecciona una opcion:");
     System.out.println("1. Administrar inventario | 2. Vender | 3. Administrar usuarios");
     usuarios.imprimeTodo();
     inventario.imprimeTodo();
     opcion = lectura.nextInt();
     logIn(opcion, usuarios, inventario, carrito);
   }
 }

  public static void menu(int opcion, BaseDeDatos usuarios, BaseDeDatos inventario, Carrito carrito){
    Scanner lectura = new Scanner(System.in);
    switch(opcion){
      case 1:
        System.out.println("ADMINISTRAR INVENTARIO");
        System.out.println("1. Agregar Producto | 2. Modificar Producto | 3. Eliminar Producto");
        opcion = lectura.nextInt();
        administrarInventario(opcion, inventario);
        break;
      case 2:
        System.out.println("VENTA DE PRODUCTOS");
        venderProducto(inventario, carrito);
        imprimirTicket(carrito);
        break;
      case 3:
        System.out.println("ADMINISTRAR USUARIOS");
        System.out.println("1. Agregar Usuario | 2. Modificar Usuario | 3. Eliminar usuario");
        opcion = lectura.nextInt();
        administrarUsuarios(opcion, usuarios);
        System.out.println("CARRITO: ");
        break;
      default:
        System.out.println("Default");
        break;
    }
  }

  //TODO: Decidir cómo manejar los tipos de usuario(String, int, enum). Actualmente es un int
  public static void logIn(int opcion, BaseDeDatos usuarios, BaseDeDatos inventario, Carrito carrito){
    Usuario usuario = encontrarUsuario(usuarios);
    if(usuario.getTipo() == opcion){
      menu(opcion, usuarios, inventario, carrito);
    }
    else{
      System.out.println("Usted no tiene permiso para acceder a esta sección");
    }
  }

  //TODO: Escoger un mejor nombre para esta funcion.
  public static Usuario encontrarUsuario(BaseDeDatos usuarios){
    Scanner lectura = new Scanner(System.in);
    String nombre;
    String contrasena;
    Usuario usuario;

    do{
      System.out.println("Escribe tu usuario: ");
      nombre = lectura.next();

      //TODO: Agregar manejo de excepciones para cuando el usuario no exista y regrese un objeto null.
      usuario = (Usuario)usuarios.getObjeto(nombre);

      System.out.println("Escribe tu contrasena: ");
      contrasena = lectura.next();
    }while(!usuario.contrasenaCorrecta(contrasena));

    return usuario;
  }

  public static void administrarUsuarios(int opcion, BaseDeDatos usuarios){
    switch(opcion){
      case 1:
        agregarUsuario(usuarios);
        break;
      case 2:
        modificarUsuario(usuarios);
        break;
      case 3:
        eliminarUsuario(usuarios);
        break;
      default:
        break;
    }
  }

  public static Usuario crearUsuario(){
    Scanner lectura = new Scanner(System.in);
    String nombre;
    String contrasena;
    int tipo;
    System.out.println("AGREGAR USUARIOS");
    System.out.println("Ingrese el nombre del usuario: ");
    nombre = lectura.next();
    System.out.println("Ingrese la contrasena del usuario: ");
    contrasena = lectura.next();
    System.out.println("Ingrese el tipo de usuario:");
    System.out.println("1. Administrador de Inventario | 2. Vendedor | 3. Administrador de usuarios:");
    tipo = lectura.nextInt();

    return new Usuario(nombre, contrasena, tipo);
  }

  public static Usuario buscarUsuario(BaseDeDatos usuarios){
    Scanner lectura = new Scanner(System.in);
    String nombre;
    Usuario usuario;
    System.out.println("Escribe el nombre del usuario: ");
    nombre = lectura.next();
    return (Usuario) usuarios.getObjeto(nombre);
  }

  public static void agregarUsuario(BaseDeDatos usuarios){
    usuarios.agregar(crearUsuario());
  }

  public static void modificarUsuario(BaseDeDatos usuarios){
    usuarios.modificar(buscarUsuario(usuarios), crearUsuario());
  }

  public static void eliminarUsuario(BaseDeDatos usuarios){
    usuarios.eliminar(buscarUsuario(usuarios));
  }

  public static void administrarInventario(int opcion, BaseDeDatos inventario){
    switch(opcion){
      case 1:
        agregarProducto(inventario);
        break;
      case 2:
        modificarProducto(inventario);
        break;
      case 3:
        eliminarProducto(inventario);
        break;
      default:
        System.out.println("Error");
        break;
    }
  }

  public static Producto crearProducto(){
    Scanner lectura = new Scanner(System.in);
    int tipo;
    String codigoDeBarras;
    String nombre;
    String categoria;
    int cantidad;
    double precio;

    System.out.println("Bebida o Alimento: ");
    System.out.println("1. Bebida | 2. Alimento");
    tipo = lectura.nextInt();

    System.out.println("Introduce el código de barras: ");
    codigoDeBarras = lectura.next();

    System.out.println("Categoria: ");
    categoria = lectura.next();

    System.out.println("Nombre del producto: ");
    nombre = lectura.next();

    System.out.println("Cantidad: ");
    cantidad = lectura.nextInt();

    System.out.println("Costo Unitario: ");
    precio = lectura.nextDouble();

    if(tipo == 1){
      return new Alimento(nombre, codigoDeBarras, tipo, cantidad, precio, categoria);
    }
    else{
      return new Bebida(nombre, codigoDeBarras, tipo, cantidad, precio, categoria);
    }
  }

  public static Producto buscarProducto(BaseDeDatos inventario){
    Scanner lectura = new Scanner(System.in);
    String nombre;
    Producto producto;
    System.out.println("Escribe el nombre del producto: ");
    nombre = lectura.next();
    return (Producto) inventario.getObjeto(nombre);
  }

  public static void agregarProducto(BaseDeDatos inventario){
    inventario.agregar(crearProducto());
  }

  public static void modificarProducto(BaseDeDatos inventario){
    inventario.modificar(buscarProducto(inventario), crearProducto());
  }

  public static void eliminarProducto(BaseDeDatos inventario){
    inventario.eliminar(buscarProducto(inventario));
  }

  public static void venderProducto(BaseDeDatos inventario, Carrito carrito){
    Scanner lectura = new Scanner(System.in);
    int cantidad;
    Producto producto = buscarProducto(inventario);
    System.out.println("¿Cuántos productos a vender?: ");
    cantidad = lectura.nextInt();
    producto.restar(cantidad);
    carrito.agregar(new Producto(producto, cantidad));
  }

  public static void imprimirTicket(Carrito carrito){
    System.out.println("TU COMPRA ES: ");
    System.out.println("Artículos: ");
    carrito.imprimeTodo();
    System.out.println("Cantidad: ");
    System.out.println(carrito.getSize() + " artículos en el carrito");
    System.out.println("Precio Unitario: ");
    carrito.imprimePrecio();
    System.out.println("Total: ");
    System.out.println(carrito.calcularTotal() + "MXN");

  }

}
