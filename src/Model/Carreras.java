package Model;

public class Carreras {
	private int id,creditos;
	private String nombre;
	
	public Carreras(){}
	
	public Carreras(int id,String nombre,int creditos){
		setId(id);
		setNombre(nombre);
		setCreditos(creditos);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
