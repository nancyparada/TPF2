package principal;

import javax.swing.*;


import ventanas.VentanaVender;


import java.awt.event.*;
import java.util.ArrayList;


public class Menu extends JFrame implements ActionListener {
	
	VentanaVender ventanaVender;

	private JButton boton1,boton2,boton3,boton4,boton5,boton6;
	public Menu() {

		inicializarVista();
	}

	private void inicializarVista() {
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boton5=new JButton("Vender");
		boton5.setBounds(20,50,140,30);
		add(boton5);
		boton5.addActionListener(this);
		
		

	}

	public void actionPerformed(ActionEvent e) {
	 
	  		if (e.getSource()==boton5) {
			ventanaVender = new VentanaVender();
			ventanaVender.setBounds(450,80,500,600);
			ventanaVender.setVisible(true);
	  		}
	}
}



