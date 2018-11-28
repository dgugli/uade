package app;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controlador;
import model.Cliente;
import view.Abm;

public class Inicio {

	public static void main(String[] args) {
		try {
			   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			  }
			  catch (UnsupportedLookAndFeelException e) {
			  }
			  catch (ClassNotFoundException e) {
			  }
			  catch (InstantiationException e) {
			  }
			  catch (IllegalAccessException e) {
			  }
		
		Cliente cliente = new Cliente(); // Modelo
		Abm abm = new Abm(); // vista
		Controlador controlador = new Controlador(cliente,abm);
		controlador.IniciarVista();

	}

}
