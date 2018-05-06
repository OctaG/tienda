import java.util.ArrayList;
public class BaseDeDatos{

	//Variables de Instancia
	protected ArrayList<Objeto> lista = new ArrayList<Objeto>();

	//Otros métodos
	public void imprime(int item){
		System.out.println(lista.get(item).getNombre());
	}

	public int getSize(){
		return lista.size();
	}

	public void imprimeTodo(){
    for(Objeto objeto : lista){
      System.out.println(objeto.getNombre());
    }
  }

	public Objeto getObjeto(String nombre){
		for(Objeto objeto : lista){
			if(objeto.getNombre().equals(nombre)){
      	return objeto;
			}
    }
		return null;
	}

	public void agregar(Objeto objeto){
		lista.add(objeto);
	}

	public void modificar(Objeto viejo, Objeto nuevo){
		lista.set(lista.indexOf(viejo), nuevo);
	}

	public void eliminar(Objeto objeto){
		lista.remove(objeto);
	}
}
