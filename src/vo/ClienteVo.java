package vo;

public class ClienteVo {
	private int codigoCliente;
	private String nombre;
	private String apellido;
	private String direccion;
	private String localidad;
	private String provincia;
	private String codigoPostal;
	private String telefono;
	private String DNI;

	public void setCodigoCliente(int cod) {
		codigoCliente = cod;
	}
	
	public int getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setNombre(String nom) {
		nombre = nom;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setApellido(String ape) {
		apellido = ape;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setDireccion(String dir) {
		direccion = dir;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setLocalidad(String loc) {
		localidad = loc;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public void setProvincia(String pro) {
		provincia = pro;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setCodigoPostal(String cp) {
		codigoPostal = cp;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public void setTelefono(String tel) {
		telefono = tel;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setDNI(String dni) {
		DNI = dni;
	}
	
	public String getDNI() {
		return DNI;
	}
}
