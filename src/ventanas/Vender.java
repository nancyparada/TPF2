package ventanas;

import javax.swing.*;

import vo.ArticuloVo;

import java.sql.Statement;

import conexion.Conexion;
import dao.ArticuloDao;

import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Vender extends JFrame implements ItemListener{

	private ArticuloDao miArticuloDao;

	private Conexion conex;
	private JComboBox combo1;
	private JLabel stock;


	public Vender() {
		miArticuloDao = new ArticuloDao();
		ArrayList<ArticuloVo> articulos = miArticuloDao.buscarArticulosConMatriz();

		setLayout(null);
		//combo1=new JComboBox(articulos.toArray());
		combo1=new JComboBox();

		for(ArticuloVo articulo : articulos) {
			combo1.addItem(articulo);
		}

		combo1.setBounds(10,10,200,20);
		combo1.addItemListener(this);
		add(combo1);

		stock = new JLabel();
		stock.setBounds(250,10,80,20);
		add(stock);


	}
	

	public void itemStateChanged(ItemEvent e) {		
		if (e.getSource()==combo1) {
			ArticuloVo seleccionado=(ArticuloVo)combo1.getSelectedItem();
			stock.setText(seleccionado.getStockArt().toString());
		}
	}

}  



