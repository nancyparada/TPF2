package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.FabricanteVo;

import conexion.Conexion;

/**
 * Clase que permite el acceso a la base de datos
 * 
 * 
 */
public class FabricanteDao {

	private Conexion conex;

	public FabricanteDao() {
		conex = new Conexion();
	}

	/**
	 * Usa el objeto enviado para almacenar la persona
	 * @param miPersona
	 */
	public void registrarFabricante(FabricanteVo miFabricante) {
		try {
			Statement estatuto = conex.getConexion().createStatement();
			estatuto.executeUpdate("INSERT INTO fabricantes VALUES ('"
					+ miFabricante.getIdCodigoFab() + "', '"							
					+ miFabricante.getNombreFab() + "') ");
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
	public ArrayList<FabricanteVo> buscarFabricantesConMatriz() {
		ArrayList<FabricanteVo> miLista = new ArrayList<FabricanteVo>();
		FabricanteVo fabricante;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM Fabricantes ");

			while (rs.next()) {
				fabricante = new FabricanteVo();
				fabricante.setIdCodigoFab(Integer.parseInt(rs.getString("codigo")));
				fabricante.setNombreFab(rs.getString("nombre"));

				miLista.add(fabricante);
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
	public ArrayList< FabricanteVo> consultarFabricante(int documento) {
		ArrayList< FabricanteVo> miEmpleado = new ArrayList< FabricanteVo>();

		try {
			PreparedStatement consulta = conex.getConexion().prepareStatement("SELECT * FROM Fabricantes where codigo = ? ");
			consulta.setInt(1, documento);
			ResultSet res = consulta.executeQuery(); 





			if(res.next()){
				FabricanteVo fabricante= new FabricanteVo();
				fabricante.setIdCodigoFab(Integer.parseInt(res.getString("codigo")));
				fabricante.setNombreFab(res.getString("nombre"));


				miEmpleado.add(fabricante);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar el Fabricante\n"+e);
		}
		return miEmpleado;
	}




}
