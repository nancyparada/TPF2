package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.ClienteDao;
import vo.ClienteVo;

public class VentanaClientes extends JFrame implements ActionListener {

	ClienteDao miClienteDao;
	
	private JLabel labelTitulo, labelTabla1;
	private JTextField textCod, textNombre, textApellido, textDireccion, textLocalidad, textProvincia, textCodigoPostal, textTelefono, textDNI;

	private JLabel cod, nombre, apellido,direccion,localidad,provincia,codigoPostal,telefono,DNI;
	private JButton botonGuardar, botonCancelar;
	JTable mitabla1;
	JScrollPane mibarra1;

	/**
	 * constructor de la clase donde se inicializan todos los componentes de la
	 * ventana de registro
	 */
	public VentanaClientes() {
		miClienteDao = new ClienteDao();
		
		inicializarVentana();
	}
	
	private void inicializarVentana() {
		setLayout(null);


		botonGuardar = new JButton();
		botonGuardar.setBounds(110, 250, 120, 25);
		botonGuardar.setText("Registrar");	
		add(botonGuardar);
		botonGuardar.addActionListener(this);


		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 250, 120, 25);
		botonCancelar.setText("Cancelar");
		add(botonCancelar);
		botonCancelar.addActionListener(this);

		labelTitulo = new JLabel();
		labelTitulo.setText("REGISTRO DE Clientes");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		labelTabla1 = new JLabel();
		labelTabla1.setText("Tabla Usando Matriz de datos");
		labelTabla1.setBounds(40, 300, 380, 50);



		cod = new JLabel();
		cod.setText("Codigo:");
		cod.setBounds(20, 60, 80, 25);
		add(cod);
		
		textCod = new JTextField();
		textCod.setBounds(90, 60, 80, 25);
		add(textCod);

		nombre = new JLabel();
		nombre.setText("Nombre:");
		nombre.setBounds(20, 90, 80, 25);
		add(nombre);

		textNombre = new JTextField();
		textNombre.setBounds(90, 90, 100, 25);
		add(textNombre);
		
		apellido = new JLabel();
		apellido.setText("Apellido:");
		apellido.setBounds(195, 90, 80, 25);
		add(apellido);

		textApellido = new JTextField();
		textApellido.setBounds(265, 90, 100, 25);
		add(textApellido);
		
		direccion = new JLabel();
		direccion.setText("Direccion:");
		direccion.setBounds(20, 120, 80, 25);
		add(direccion);

		textDireccion = new JTextField();
		textDireccion.setBounds(90, 120, 100, 25);
		add(textDireccion);
		
		localidad = new JLabel();
		localidad.setText("Localidad:");
		localidad.setBounds(195, 120, 80, 25);
		add(localidad);

		textLocalidad = new JTextField();
		textLocalidad.setBounds(265, 120, 100, 25);
		add(textLocalidad);

		//provincia y telefono   150
		
		provincia = new JLabel();
		provincia.setText("Provincia:");
		provincia.setBounds(20, 150, 80, 25);
		add(provincia);

		textProvincia = new JTextField();
		textProvincia.setBounds(90, 150, 100, 25);
		add(textProvincia);
		
		telefono = new JLabel();
		telefono.setText("Telefono:");
		telefono.setBounds(195, 150, 80, 25);
		add(telefono);

		textTelefono = new JTextField();
		textTelefono.setBounds(265, 150, 100, 25);
		add(textTelefono);
		
		//codigo postal y DNI   180
		
		codigoPostal= new JLabel();
		codigoPostal.setText("Cod.Postal:");
		codigoPostal.setBounds(20, 180, 80, 25);
		add(codigoPostal);

		textCodigoPostal = new JTextField();
		textCodigoPostal.setBounds(90, 180, 100, 25);
		add(textCodigoPostal);
		
		DNI = new JLabel();
		DNI.setText("DNI:");
		DNI.setBounds(195, 180, 80, 25);
		add(DNI);

		textDNI = new JTextField();
		textDNI.setBounds(265, 180, 100, 25);
		add(textDNI);


		// ////////////////////////////////////////////
		mibarra1 = new JScrollPane();
		mibarra1.setBounds(40, 350, 400, 130);
		mostrarDatosUsandoLogica1();// mostramos la tabla




		add(labelTitulo);
		add(labelTabla1);
		add(mibarra1);

		limpiar();
		setSize(480, 650);
		setTitle("TABLA ClIENTES");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
	}

	/**
	 * Permite el llenado de la tabla1 usando sin utilizar el table model, para
	 * esto usamos logica de programación por medio de la captura de información
	 * en una lista y luego en una matriz para llenar la tabla
	 */
	public void mostrarDatosUsandoLogica1() {

		String titulos[] = { "Codigo","Nombre", "Apellido", "Direccion","Localidad","Provincia","Cod. Postal","Telefono","DNI"};
		String información[][] = obtieneMatriz2();// obtenemos la informacion de
		// la BD

		mitabla1 = new JTable(información, titulos);
		mitabla1.setEnabled(false);
		mitabla1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		mibarra1.setViewportView(mitabla1);
	}






	/**
	 * Metodo que permite retornar la matriz con información de la BD
	 * 
	 * @return
	 */
	private String[][] obtieneMatriz2() {
		/*
		 * llamamos al metodo que retorna la info de la BD y la almacena en la
		 * lista
		 */
		ArrayList<ClienteVo> miLista = miClienteDao.buscarClientesConMatriz();
		/*
		 * como sabemos que son 4 campos, definimos ese valor por defecto para
		 * las columnaslas filas dependen de los registros retornados
		 */
		String[][] informacion = new String[miLista.size()][9];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getCodigoCliente() + "";	
			informacion[x][1] = miLista.get(x).getNombre() + "";
			informacion[x][2] = miLista.get(x).getApellido() + "";
			informacion[x][3] = miLista.get(x).getDireccion() + "";
			informacion[x][4] = miLista.get(x).getLocalidad() + "";
			informacion[x][5] = miLista.get(x).getProvincia() + "";
			informacion[x][6] = miLista.get(x).getCodigoPostal() + "";
			informacion[x][7] = miLista.get(x).getTelefono() + "";
			informacion[x][8] = miLista.get(x).getDNI() + "";
		}
		return informacion;
	}





	/**
	 * Limpia el formulario de Registro
	 */
	private void limpiar() {
		textCod.setText("");
		textNombre.setText("");
		textApellido.setText("");
		textDireccion.setText("");
		textLocalidad.setText("");
		textProvincia.setText("");
		textTelefono.setText("");
		textCodigoPostal.setText("");
		textDNI.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonGuardar) {

			try {
				ClienteVo miCliente = new ClienteVo();
				miCliente.setCodigoCliente(Integer.parseInt(textCod.getText()));
				miCliente.setNombre(textNombre.getText());
				miCliente.setApellido(textApellido.getText());
				miCliente.setDireccion(textDireccion.getText());
				miCliente.setLocalidad(textLocalidad.getText());
				miCliente.setProvincia(textProvincia.getText());
				miCliente.setTelefono(textTelefono.getText());
				miCliente.setCodigoPostal(textCodigoPostal.getText());
				miCliente.setDNI(textDNI.getText());


				miClienteDao.registrarCliente(miCliente);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Error en el Ingreso de Datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			} finally {
				/* Actualizamos siempre las tablas despues del registro */
				mostrarDatosUsandoLogica1();
				limpiar();
			}
		}
		if (e.getSource() == botonCancelar) {
			limpiar();
		}
	}

}




