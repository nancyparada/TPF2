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

import dao.FabricanteDao;
import vo.FabricanteVo;

public class VentanaFabricantes extends JFrame implements ActionListener {

	FabricanteDao miFabricanteDao;
	
	private JLabel labelTitulo, labelTabla1;
	private JTextField textCod, textNombre, textPrecio, textFabricante;

	private JLabel cod, nombre, precio, fabricante;
	private JButton botonGuardar, botonCancelar;
	JTable mitabla1;
	JScrollPane mibarra1;

	/**
	 * constructor de la clase donde se inicializan todos los componentes de la
	 * ventana de registro
	 */
	public VentanaFabricantes() {
		miFabricanteDao = new FabricanteDao();
		
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
		labelTitulo.setText("REGISTRO DE FABRICANTES");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		labelTabla1 = new JLabel();
		labelTabla1.setText("Tabla Usando Matriz de datos");
		labelTabla1.setBounds(40, 250, 380, 50);



		cod = new JLabel();
		cod.setText("Codigo");
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
		ArrayList<FabricanteVo> miLista = miFabricanteDao.buscarFabricantesConMatriz();
		/*
		 * como sabemos que son 4 campos, definimos ese valor por defecto para
		 * las columnaslas filas dependen de los registros retornados
		 */
		String[][] informacion = new String[miLista.size()][2];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getIdCodigoFab() + "";	
			informacion[x][1] = miLista.get(x).getNombreFab() + "";
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
				FabricanteVo miFabricante = new FabricanteVo();
				miFabricante.setIdCodigoFab(Integer.parseInt(textCod.getText()));
				miFabricante.setNombreFab(textNombre.getText());


				miFabricanteDao.registrarFabricante(miFabricante);
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




