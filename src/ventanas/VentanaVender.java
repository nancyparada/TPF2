package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;


import dao.ArticuloDao;
import vo.ArticuloVo;

import dao.ClienteDao;
import vo.ClienteVo;

import dao.CompraDao;
import vo.CompraVo;

public class VentanaVender extends JFrame  implements ActionListener, ItemListener
{
	ArticuloDao articulos;
	ClienteDao clientes;
	CompraDao compras;
	
//	controles visuales
	JLabel titulo,stockTitulo,stockValor,precioTitulo,precioValor,codigoTitulo,codigoValor,cantidadTitulo,costoTotalTitulo,costoTotalValor;
	JComboBox listaArticulos,listaClientes;
	JButton botonVender;
	JTextField textCantidad;
	
	public VentanaVender() 
	{
		articulos=new ArticuloDao();
		clientes=new ClienteDao();
		compras=new CompraDao();
		inicializar();
	}
	
	private void inicializar() 
	{
		titulo=new JLabel();
		titulo.setText("VENTA DE ARTICULOS");
		titulo.setBounds(120, 10, 380, 30);
		add(titulo);
		
		stockTitulo=new JLabel();
		stockTitulo.setText("Stock");
		stockTitulo.setBounds(220, 50, 80 , 25);
		add(stockTitulo);
		
		stockValor=new JLabel();
		stockValor.setText("");
		stockValor.setBounds(260, 50, 80, 25);
		add(stockValor);
		
		precioTitulo=new JLabel();
		precioTitulo.setText("Precio");
		precioTitulo.setBounds(290, 50, 80, 25);
		add(precioTitulo);
		
		precioValor=new JLabel();
		precioValor.setText("");
		precioValor.setBounds(340, 50, 80, 25);
		add(precioValor);
		
		codigoTitulo=new JLabel();
		codigoTitulo.setText("Codigo");
		codigoTitulo.setBounds(220, 100, 80, 25);
		add(codigoTitulo);
		
		codigoValor=new JLabel();
		codigoValor.setText("");
		codigoValor.setBounds(300, 100, 80, 20);
		add(codigoValor);
		
		cantidadTitulo=new JLabel();
		cantidadTitulo.setText("Cantidad");
		cantidadTitulo.setBounds(20, 180, 80, 25);
		add(cantidadTitulo);
		
		costoTotalTitulo=new JLabel();
		costoTotalTitulo.setText("Costo Total");
		costoTotalTitulo.setBounds(20, 280, 80, 25);
		add(costoTotalTitulo);
		
		costoTotalValor=new JLabel();
		costoTotalValor.setText("$0,00");
		costoTotalValor.setBounds(250, 280, 80, 25);
		add(costoTotalValor);
		
		botonVender= new JButton();
		botonVender.setText("VENDER");
		botonVender.setBounds(10, 220, 120, 25);
		botonVender.addActionListener(this);
		add(botonVender);
		
		textCantidad= new JTextField();
		textCantidad.setText("0");
		textCantidad.setBounds(150, 180, 190, 25);
		add(textCantidad);
		
		ArrayList<ArticuloVo> articulosLista = articulos.buscarArticulosConMatriz();
		listaArticulos=new JComboBox();

		for(ArticuloVo articulo : articulosLista) {
			listaArticulos.addItem(articulo);
		}

		listaArticulos.setBounds(10,50,200,20);
		listaArticulos.addItemListener(this);
		add(listaArticulos);
		listaArticulos.setSelectedItem(0);
		ArticuloVo seleccionado=(ArticuloVo)listaArticulos.getSelectedItem();
		stockValor.setText(seleccionado.getStockArt().toString());
		precioValor.setText(seleccionado.getPrecioArt().toString());
		
		ArrayList<ClienteVo> clientesLista = clientes.buscarClientesConMatriz();
		listaClientes=new JComboBox();

		for(ClienteVo cliente : clientesLista) {
			listaClientes.addItem(cliente);
		}

		listaClientes.setBounds(10,100,200,20);
		listaClientes.addItemListener(this);
		add(listaClientes);
		listaClientes.setSelectedItem(0);
		ClienteVo seleccionado2=(ClienteVo)listaClientes.getSelectedItem();
		codigoValor.setText(seleccionado2.getCodigoCliente()+"");
		
		
		setSize(480, 480);
		setTitle("VENDER");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
	}
	
	public void itemStateChanged(ItemEvent e)
	{		
		if (e.getSource()==listaArticulos) {
			ArticuloVo seleccionado=(ArticuloVo)listaArticulos.getSelectedItem();
			stockValor.setText(seleccionado.getStockArt().toString());
			precioValor.setText(seleccionado.getPrecioArt().toString());
		} else 
		{
			ClienteVo seleccionado2=(ClienteVo)listaClientes.getSelectedItem();
			codigoValor.setText(seleccionado2.getCodigoCliente()+"");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonVender) {
			
			try {
				ArticuloVo miArticulo = (ArticuloVo)listaArticulos.getSelectedItem();
				ClienteVo miCliente = (ClienteVo)listaClientes.getSelectedItem();
				int cantidad= Integer.parseInt(textCantidad.getText());
				if(cantidad>miArticulo.getStockArt() || cantidad==0) 
				{
					JOptionPane.showMessageDialog(null,
							"Error cantidad a vender no puede ser cero o mayor al stock disponible", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//creo el registro de la compra
				CompraVo miCompra= new CompraVo();
				miCompra.setCodigoArticulo(miArticulo.getIdCodigoArt());
				miCompra.setCodigoCliente(miCliente.getCodigoCliente());
				miCompra.setUnidades(cantidad);
				java.util.Date fecha=new java.util.Date();
				java.text.SimpleDateFormat formato=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				miCompra.setFecha(formato.format(fecha));
				
				compras.registrarCompra(miCompra);
				
				// muestro el total de compra en el JLabel
				double total = cantidad*miArticulo.getPrecioArt();
				costoTotalValor.setText("$"+total);
				
				
				//actualizo el stock del producto en la BD
				miArticulo.setStockArt(miArticulo.getStockArt()-cantidad);
				
				articulos.actualizarArticulo(miArticulo);
				stockValor.setText(miArticulo.getStockArt().toString());
				 
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Error en el Ingreso de Datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
