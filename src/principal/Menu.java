package principal;

import javax.swing.*;


import ventanas.Vender;
import vo.ArticuloVo;
import dao.ArticuloDao;

import java.awt.event.*;
import java.util.ArrayList;


public class Menu extends JFrame implements ActionListener {

	private ArticuloDao miArticuloDao;

	private JButton boton1,boton2,boton3,boton4;
	public Menu() {
		miArticuloDao = new ArticuloDao();

		inicializarVista();
	}

	private void inicializarVista() {
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//	boton1=new JButton("Editar Articulo");
		boton1=new JButton("Editar datos de un cliente");
		boton1.setBounds(30,40,190,30);
		add(boton1);
		boton1.addActionListener(this);

		boton2=new JButton("Consultar un Articulo");
		boton2.setBounds(30,100,190,30);
		add(boton2);
		boton2.addActionListener(this);

		boton3=new JButton("Vender articulos");
		boton3.setBounds(30,160,190,30);
		add(boton3);
		boton3.addActionListener(this);


	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==boton1) {
			setTitle("Selecciono opcion 1");
			modificarCliente();
		}
		if (e.getSource()==boton2) {
			setTitle("Selecciono opcion 2");

			int cod=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero" +
					" de codigo del Articulo"));
			buscarArticulo(cod);	

		}
		if (e.getSource()==boton3) {
			setTitle("Selecciono opcion 3");
		//	obtenerRegistros();
			Vender venderform = new Vender();
			venderform.setBounds(300,300,410,410);
			venderform.setTitle("VENDER");
			venderform.setVisible(true);
		}    
		if (e.getSource()==boton4) {
			//setTitle("Selecciono opcion 4");
			System.exit(1);
		}  

	}

	private void modificarCliente() {
		ArticuloVo miArticulo=new ArticuloVo();

		String mensajeIngreso="Ingrese\n\n";

		String datosSolicitados[] = {"Codigo : ","Nombre : ",
				"Precio ","fabricantes_codigo: "};
		String datosArticulo[] = new String[5];
		for (int i = 0; i < datosSolicitados.length; i++) {
			//solicita el ingreso del dato y se almacena en el arreglo de datosArticulo
			datosArticulo[i]=JOptionPane.showInputDialog(null, mensajeIngreso+
					datosSolicitados[i],"Datos Articulo",JOptionPane.INFORMATION_MESSAGE);

			System.out.println(datosSolicitados[i]+datosArticulo[i]);
		}

		miArticulo.setIdCodigoArt(Integer.parseInt(datosArticulo[0]));
		miArticulo.setNombreArt(datosArticulo[1]);
		miArticulo.setPrecioArt(Double.parseDouble(datosArticulo[2]));
		miArticulo.setFabricanteCodArt(Integer.parseInt(datosArticulo[3]));
		miArticulo.setStockArt(Integer.parseInt(datosArticulo[4]));

		miArticuloDao.registrarArticulo(miArticulo);

	}

	/**
	 * Permite obtener la lista de Articulos almacenada en la tabla Articulo
	 * si la lista se encuentra vacia quiere decir que no hay Articulos registradas
	 * acto seguido se presenta un mensaje en pantalla, sino se imprime la lista de 
	 * todas las Articulos registradas en la BD
	 */
	private void obtenerRegistros() {
		ArticuloVo miArticulo;

		//Se obtiene la lista de Articulos
	
		ArrayList< ArticuloVo> listaArticulos = miArticuloDao.buscarArticulosConMatriz();

		//se valida si se obtuvo o no informacion
		if (listaArticulos.size()>0) {
			int numeroArticulo=0;
			//se recorre la lista de Articulos asignandose cada posicion en un objeto Articulo
			for (int i = 0; i < listaArticulos.size(); i++) {
				numeroArticulo++;
				miArticulo=listaArticulos.get(i);
				System.out.println("****************Articulo "+numeroArticulo+"**********************");
				System.out.println("Id Articulo: "+miArticulo.getIdCodigoArt());
				System.out.println("Nombre Articulo: "+miArticulo.getNombreArt());
				System.out.println("Precio Articulo: "+miArticulo.getPrecioArt());
				System.out.println("Fabricante Articulo: "+miArticulo.getFabricanteCodArt());
				System.out.println("Stock Articulo: "+miArticulo.getStockArt());
				System.out.println("*************************************************\n");
			}
		}else{
			JOptionPane.showMessageDialog(null,"Actualmente no " +
					"existen registros de Articulos","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/**
	 * Permite la consulta de un Articulo en especifico mediante el envio de
	 * su documento de identidad como parametro, en caso de que no se retorne
	 * informacion se presenta un mensaje en pantalla, sino entonces se imprimen los
	 * datos de la Articulo encontrada
	 * @param documento
	 */
	private void buscarArticulo(int documento) {
		ArticuloVo miArticulo;
		ArrayList< ArticuloVo> articulosEncontrada = miArticuloDao.consultarArticulo(documento);
		//se valida que se encuentre la Articulo
		if (articulosEncontrada.size()>0) {
			//se recorre la lista y se asignan los datos al objeto para imprimir los valores
			for (int i = 0; i < articulosEncontrada.size(); i++) {
				miArticulo=articulosEncontrada.get(i);
				System.out.println("****************Articulo*************************");
				System.out.println("Id Articulo: "+miArticulo.getIdCodigoArt());
				System.out.println("Nombre Articulo: "+miArticulo.getNombreArt());
				System.out.println("Edad Articulo: "+miArticulo.getPrecioArt());
				System.out.println("Profesión Articulo: "+miArticulo.getFabricanteCodArt());
				System.out.println("Stock Articulo: "+miArticulo.getStockArt());
				
				System.out.println("*************************************************\n");
			}
		}else{
			JOptionPane.showMessageDialog(null,"El documento ingresado " +
					"no corresponde a ninguna Articulo","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
		}
	}


}


