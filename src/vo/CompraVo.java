package vo;

public class CompraVo {

	int codCliente;
	int codArticulo;
	String fecha;
	int unidades;
	
	public void setCodigoCliente(int cod) 
	{
		codCliente=cod;
	}
	public int getCodigoCliente() 
	{
		return codCliente;
	}
	public void setCodigoArticulo(int art) 
	{
		codArticulo=art;
	}
	public int getCodigoArticulo() 
	{
		return codArticulo;
	}
	public void setFecha(String fech) 
	{
		fecha= fech;
	}
	public String getFecha() 
	{
		return fecha;
	}
	public void setUnidades(int unid) 
	{
		unidades= unid;
	}
	public int getUnidades() 
	{
		return unidades;
	}
	
	

}
