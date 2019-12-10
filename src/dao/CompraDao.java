package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.CompraVo;

import conexion.Conexion;

/**
 * Clase que permite el acceso a la base de datos
 * 
 * 
 */
public class CompraDao {

	private Conexion conex;

	public CompraDao() {
		conex = new Conexion();
	}

	/**
	 * Usa el objeto enviado para almacenar la persona
	 * @param miPersona
	 */
	public void registrarCompra(CompraVo miCompra) {
		try {
			Statement estatuto = conex.getConexion().createStatement();
			estatuto.executeUpdate("INSERT INTO compra VALUES ('"
					+ miCompra.getCodigoCliente() + "', '"
					+ miCompra.getCodigoArticulo() + "', '"
					+ miCompra.getFecha() + "','"
					+ miCompra.getUnidades() + "')");
					
					//insert into informaticav2.compra values(1,106,"2019-12-03",2);
					
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
	public ArrayList<CompraVo> buscarCompraConMatriz() {
		ArrayList<CompraVo> miLista = new ArrayList<CompraVo>();
		CompraVo compra;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM compra ");

			while (rs.next()) {
				compra = new CompraVo();
				compra.setCodigoCliente(Integer.parseInt(rs.getString("clientes_codcliente")));
				compra.setCodigoArticulo((Integer.parseInt(rs.getString("articulos_codigo"))));
				compra.setFecha(rs.getString("fecha"));
				compra.setUnidades((Integer.parseInt(rs.getString("unidades"))));
				

				miLista.add(compra);
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
	public ArrayList< CompraVo> consultarCompraPorCliente(int codigo) {
		ArrayList<CompraVo> misCompras = new ArrayList<CompraVo>();

		try {
			PreparedStatement consulta = conex.getConexion().prepareStatement("SELECT * FROM compra where clientes_codcliente = ?");
			consulta.setInt(1, codigo);
			ResultSet res = consulta.executeQuery(); 
			if(res.next()){
				CompraVo compra = new CompraVo();
				compra.setCodigoCliente(Integer.parseInt(res.getString("clientes_codcliente")));
				compra.setCodigoArticulo((Integer.parseInt(res.getString("articulos_codigo"))));
				compra.setFecha(res.getString("fecha"));
				compra.setUnidades((Integer.parseInt(res.getString("unidades"))));
				misCompras.add(compra);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la compra\n"+e);
		}
		return misCompras;
	}




}

