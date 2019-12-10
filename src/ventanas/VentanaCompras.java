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

import dao.CompraDao;
import vo.CompraVo;

public class VentanaCompras extends JFrame implements ActionListener {

	CompraDao miCompraDao;
	
	private JLabel labelTitulo, labelTabla1;
	private JTextField textCliente, textArticulo, textFecha, textUnidades;

	private JLabel codCliente, codArticulo, fecha, unidades;
	private JButton botonGuardar, botonCancelar;
	JTable mitabla1;
	JScrollPane mibarra1;

	/**
	 * constructor de la clase donde se inicializan todos los componentes de la
	 * ventana de registro
	 */
	public VentanaCompras() {
		miCompraDao = new CompraDao();
		
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
		labelTitulo.setText("REGISTRO DE COMPRAS");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		labelTabla1 = new JLabel();
		labelTabla1.setText("Tabla Usando Matriz de datos");
		labelTabla1.setBounds(40, 300, 380, 50);



		codCliente = new JLabel();
		codCliente.setText("Cliente:");
		codCliente.setBounds(20, 90, 80, 25);
		add(codCliente);

		textCliente = new JTextField();
		textCliente.setBounds(90, 90, 100, 25);
		add(textCliente);
		
		codArticulo = new JLabel();
		codArticulo.setText("Articulo:");
		codArticulo.setBounds(195, 90, 80, 25);
		add(codArticulo);

		textArticulo = new JTextField();
		textArticulo.setBounds(265, 90, 100, 25);
		add(textArticulo);
		
		fecha = new JLabel();
		fecha.setText("Fecha:");
		fecha.setBounds(20, 120, 80, 25);
		add(fecha);

		textFecha = new JTextField();
		textFecha.setBounds(90, 120, 100, 25);
		add(textFecha);
		
		unidades = new JLabel();
		unidades.setText("unidades:");
		unidades.setBounds(195, 120, 80, 25);
		add(unidades);

		textUnidades = new JTextField();
		textUnidades.setBounds(265, 120, 100, 25);
		add(textUnidades);

		
		
		


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

		String titulos[] = { "Cliente", "Articulo","Fecha","Unidades"};
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
		ArrayList<CompraVo> miLista = miCompraDao.buscarCompraConMatriz();
		/*
		 * como sabemos que son 4 campos, definimos ese valor por defecto para
		 * las columnaslas filas dependen de los registros retornados
		 */
		String[][] informacion = new String[miLista.size()][4];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getCodigoCliente() + "";	
			informacion[x][1] = miLista.get(x).getCodigoArticulo() + "";
			informacion[x][2] = miLista.get(x).getFecha() + "";
			informacion[x][3] = miLista.get(x).getUnidades() + "";
			
		}
		return informacion;
	}





	/**
	 * Limpia el formulario de Registro
	 */
	private void limpiar() {
		textCliente.setText("");
		textArticulo.setText("");
		textFecha.setText("");
		textUnidades.setText("");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonGuardar) {

			try {
			/*	ClienteVo miCliente = new ClienteVo();
				miCliente.setCodigoCliente(Integer.parseInt(textCod.getText()));
				miCliente.setNombre(textNombre.getText());
				miCliente.setApellido(textApellido.getText());
				miCliente.setDireccion(textDireccion.getText());
				miCliente.setLocalidad(textLocalidad.getText());
				miCliente.setProvincia(textProvincia.getText());
				miCliente.setTelefono(textTelefono.getText());
				miCliente.setCodigoPostal(textCodigoPostal.getText());
				miCliente.setDNI(textDNI.getText());


				miClienteDao.registrarCliente(miCliente);*/
				
				CompraVo miCompra= new CompraVo();
				miCompra.setCodigoCliente(Integer.parseInt(textCliente.getText()));
				miCompra.setCodigoArticulo(Integer.parseInt(textArticulo.getText()));
				miCompra.setFecha(textFecha.getText());
				miCompra.setUnidades(Integer.parseInt(textUnidades.getText()));
				
				miCompraDao.registrarCompra(miCompra);
				
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




