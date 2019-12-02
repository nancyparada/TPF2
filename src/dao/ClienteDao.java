package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.ClienteVo;

import conexion.Conexion;

/**
 * Clase que permite el acceso a la base de datos
 * 
 * 
 */
public class ClienteDao {

	private Conexion conex;

	public ClienteDao() {
		conex = new Conexion();
	}

	/**
	 * Usa el objeto enviado para almacenar la persona
	 * @param miPersona
	 */
	public void registrarCliente(ClienteVo miCliente) {
		try {
			Statement estatuto = conex.getConexion().createStatement();
			estatuto.executeUpdate("INSERT INTO clientes VALUES ('"
					+ miCliente.getCodigoCliente() + "', '"
					+ miCliente.getNombre() + "', '"
					+ miCliente.getApellido() + "', '"
					+ miCliente.getDireccion() + "', '"
					+ miCliente.getLocalidad() + "', '"
					+ miCliente.getProvincia() + "', '"
					+ miCliente.getCodigoPostal() + "', '"
					+ miCliente.getTelefono() + "', '"
					+ miCliente.getDNI() + "') ");
			JOptionPane.showMessageDialog(null,
					"Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null,
					"No se Registro, verifique la consola para ver el error",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 * Retorna una lista con los datos de la BD, para luego ser 
	 * recorrida y almacenada en la tabla por medio de la Matriz
	 * @return
	 */
	public ArrayList<ClienteVo> buscarClientesConMatriz() {
		ArrayList<ClienteVo> miLista = new ArrayList<ClienteVo>();
		ClienteVo cliente;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM clientes ");

			while (rs.next()) {
				cliente = new ClienteVo();
				cliente.setCodigoCliente(Integer.parseInt(rs.getString("codcliente")));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setLocalidad(rs.getString("localidad"));
				cliente.setProvincia(rs.getString("Provincia"));
				cliente.setTelefono(rs.getString("Telefono"));
				cliente.setCodigoPostal(rs.getString("cod_postal"));
				cliente.setDNI(rs.getString("DNI"));

				miLista.add(cliente);
			}
			rs.close();
			estatuto.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		return miLista;
	}

	/**
	 * permite consultar el empleado asociado al documento enviado
	 * como parametro 
	 * @param documento 
	 * @return
	 */
	public ArrayList< ClienteVo> consultarCliente(int codigo) {
		ArrayList<ClienteVo> miCliente = new ArrayList<ClienteVo>();

		try {
			PreparedStatement consulta = conex.getConexion().prepareStatement("SELECT * FROM clientes where codcliente = ?");
			consulta.setInt(1, codigo);
			ResultSet res = consulta.executeQuery(); 
			if(res.next()){
				ClienteVo cliente= new ClienteVo();
				cliente.setCodigoCliente(Integer.parseInt(res.getString("codcliente")));
				cliente.setNombre(res.getString("nombre"));
				cliente.setApellido(res.getString("apellido"));
				cliente.setDireccion(res.getString("direccion"));
				cliente.setLocalidad(res.getString("localidad"));
				cliente.setProvincia(res.getString("Provincia"));
				cliente.setTelefono(res.getString("Telefono"));
				cliente.setCodigoPostal(res.getString("cod_postal"));
				cliente.setDNI(res.getString("DNI"));
				miCliente.add(cliente);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar el Cliente\n"+e);
		}
		return miCliente;
	}




}
