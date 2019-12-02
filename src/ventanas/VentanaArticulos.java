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

import dao.ArticuloDao;
import vo.ArticuloVo;

public class VentanaArticulos extends JFrame implements ActionListener {

	ArticuloDao miArticuloDao;
	
	private JLabel labelTitulo, labelTabla1;
	private JTextField textCod, textNombre, textPrecio, textFabricante,textStock;

	private JLabel cod, nombre, precio, fabricante,stock;
	private JButton botonGuardar, botonCancelar;
	JTable mitabla1;
	JScrollPane mibarra1;

	/**
	 * Constructor de la clase donde se inicializan todos los componentes de la
	 * ventana de registro
	 */
	public VentanaArticulos() {
		miArticuloDao = new ArticuloDao();

		inicializarVista();
	}

	private void inicializarVista() {
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
		labelTitulo.setText("REGISTRO DE ARTICULOS");
		labelTitulo.setBounds(120, 20, 380, 30);
		labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));

		labelTabla1 = new JLabel();
		labelTabla1.setText("Tabla Usando Matriz de datos");
		labelTabla1.setBounds(40, 250, 380, 50);



		/*		cod = new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 80, 80, 25);
		add(cod);
		 */
		cod = new JLabel();
		cod.setText("Codigo");
		cod.setBounds(20, 120, 80, 25);
		add(cod);

		nombre = new JLabel();
		nombre.setText("Nombre");
		nombre.setBounds(290, 160, 80, 25);
		add(nombre);

		precio = new JLabel();
		precio.setText("Precio");
		precio.setBounds(290, 120, 80, 25);
		add(precio);

		fabricante = new JLabel();
		fabricante.setText("Fabricante");
		fabricante.setBounds(20, 160, 80, 25);
		add(fabricante);
		
		
		stock = new JLabel();
		stock.setText("Stock");
		stock.setBounds(20, 80, 80, 25);
		add(stock);



		textCod = new JTextField();
		textCod.setBounds(80, 120, 190, 25);
		add(textCod);

		textNombre = new JTextField();
		textNombre.setBounds(340, 160, 80, 25);
		add(textNombre);

		textPrecio = new JTextField();
		textPrecio.setBounds(340, 120, 80, 25);
		add(textPrecio);

		textFabricante = new JTextField();
		textFabricante.setBounds(80, 160, 190, 25);
		add(textFabricante);

		textStock = new JTextField();
		textStock.setBounds(80, 80, 190, 25);
		add(textStock);

		// ////////////////////////////////////////////
		mibarra1 = new JScrollPane();
		mibarra1.setBounds(40, 300, 400, 130);
		mostrarDatosUsandoLogica1();// mostramos la tabla




		add(labelTitulo);
		add(labelTabla1);
		add(mibarra1);

		limpiar();
		setSize(480, 650);
		setTitle("TABLA ARTICULOS");
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

		String titulos[] = { "Codigo","Nombre", "Precio", "Cod Fabricante" , "Stock" };
		String información[][] = obtieneMatriz1();// obtenemos la informacion de
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
	private String[][] obtieneMatriz1() {
		/*
		 * llamamos al metodo que retorna la info de la BD y la almacena en la
		 * lista
		 */
		ArrayList<ArticuloVo> miLista = miArticuloDao.buscarArticulosConMatriz();
		/*
		 * como sabemos que son 5 campos, definimos ese valor por defecto para
		 * las columnaslas filas dependen de los registros retornados
		 */
		String[][] informacion = new String[miLista.size()][5];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getIdCodigoArt() + "";
			informacion[x][1] = miLista.get(x).getNombreArt() + "";
			informacion[x][2] = miLista.get(x).getPrecioArt() + "";	
			informacion[x][3] = miLista.get(x).getFabricanteCodArt() + "";
			informacion[x][4] = miLista.get(x).getStockArt() + "";
		}
		return informacion;
	}





	/**
	 * Limpia el formulario de Registro
	 */
	private void limpiar() {
		textCod.setText("");
		textNombre.setText("");
		textPrecio.setText("");
		textFabricante.setText("");
		textStock.setText("");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonGuardar) {

			try {
				ArticuloVo miArticulo = new ArticuloVo();
				miArticulo.setIdCodigoArt(Integer.parseInt(textCod.getText()));
				miArticulo.setNombreArt(textNombre.getText());
				miArticulo.setPrecioArt(Double.parseDouble(textPrecio.getText()));
				miArticulo.setFabricanteCodArt(Integer.parseInt(textFabricante.getText()));
				miArticulo.setStockArt(Integer.parseInt(textStock.getText()));







				miArticuloDao.registrarArticulo(miArticulo);
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
