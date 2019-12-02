package conexion;

import java.sql.*;


/**
 * Clase que permite conectar con la base de datos
 *
 */
public class Conexion {
	static String bd = "informaticav2";
	static String login = "root";
	static String password = "";
	//  static String url = "jdbc:mysql://localhost/"+bd;
	static String url = "jdbc:mysql://localhost/"+bd+"?useSSL=true";

	Connection conn;

	/** Constructor de DbConnection */
	public Conexion() {
		try{
			//obtenemos el driver de para mysql
			//Class.forName("com.mysql.jdbc.Driver");
			//obtenemos la conexión
			conn = DriverManager.getConnection(url,login,password);

		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**Permite retornar la conexión*/
	public Connection getConexion(){
		return conn;
	}

	public void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}