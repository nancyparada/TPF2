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
		botonGuardar.setBounds(110, 200, 120, 25);
		botonGuardar.setText("Registrar");	
		add(botonGuardar);
		botonGuardar.addActionListener(this);


		botonCancelar = new JButton();
		botonCancelar.setBounds(250, 200, 120, 25);
		botonCancelar.setText("Cancelar");
		add(botonCancelar);
		botonCancelar.addActionListener(this);

		labelTitulo = new JLabel();
		labelTitulo.setText("REGISTRO DE Clientes");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		labelTabla1 = new JLabel();
		labelTabla1.setText("Tabla Usando Matriz de datos");
		labelTabla1.setBounds(40, 250, 380, 50);



		cod = new JLabel();
		cod.setText("Codigo Cliente");
		cod.setBounds(20, 120, 80, 25);
		add(cod);

		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(290, 160, 80, 25);
		add(nombre);



		textCod = new JTextField();
		textCod.setBounds(80, 120, 190, 25);
		add(textCod);

		textNombre = new JTextField();
		textNombre.setBounds(340, 160, 80, 25);
		add(textNombre);




		// ////////////////////////////////////////////
		mibarra1 = new JScrollPane();
		mibarra1.setBounds(40, 300, 400, 130);
		mostrarDatosUsandoLogica1();// mostramos la tabla




		add(labelTitulo);
		add(labelTabla1);
		add(mibarra1);

		limpiar();
		setSize(480, 650);
		setTitle("TABLA FABRICANTES");
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

		String titulos[] = { "Codigo","Nombre Fabricante" };
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
		String[][] informacion = new String[miLista.size()][2];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getIdCodigoCliente() + "";	
			informacion[x][1] = miLista.get(x).getNombreCliente() + "";
		}
		return informacion;
	}





	/**
	 * Limpia el formulario de Registro
	 */
	private void limpiar() {
		textCod.setText("");
		textNombre.setText("");


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonGuardar) {

			try {
				ClienteVo miCliente = new ClienteVo();
				cliente.setIdCodigoCliente(Integer.parseInt(textCod.getText()));
				miCliente.setCliente(textNombre.getText());


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




