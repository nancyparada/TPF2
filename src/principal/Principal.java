package principal;



import ventanas.VentanaArticulos;
import ventanas.VentanaClientes;
import ventanas.VentanaFabricantes;
import ventanas.VentanaCompras;
import ventanas.VentanaVender;


public class Principal {

	/**
	 * Llama la ventana principal
	 * @param args
	 */
	public static void main(String[] args) {
		VentanaArticulos miVentana1;
		miVentana1 = new VentanaArticulos();
		miVentana1.setBounds(260,50,500,500);
		miVentana1.setVisible(true);

		VentanaFabricantes miVentana2;
		miVentana2 = new VentanaFabricantes();
		miVentana2.setBounds(770,50,500,500);
		miVentana2.setVisible(true);
		
		VentanaClientes miVentana3;
		miVentana3 = new VentanaClientes();
		miVentana3.setBounds(260,380,500,550);
		miVentana3.setVisible(true);
		
		VentanaCompras miVentana4;
		miVentana4 = new VentanaCompras();
		miVentana4.setBounds(770,380,500,550);
		miVentana4.setVisible(true);
		
		Menu formulario1 = new Menu();
		formulario1.setBounds(50,50,200, 200);
		formulario1.setTitle("MENU PRINCIPAL");
		formulario1.setVisible(true);

		

	}
}
