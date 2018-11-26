package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;

public class Abm extends JFrame {
	public JTextField txtBuscar;
	public JPanel panel;
	public JButton btnNuevo;
	public JButton btnCerrar;
	public JButton btnVerDatos;
	public JButton btnEliminar;
	public JList lstClientes;
	public JComboBox cmbBuscarFiltro;

	public Abm() {
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 381, 314);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblAdministracinDeClientes = new JLabel("Administraci\u00F3n de Clientes");
		lblAdministracinDeClientes.setBounds(106, 11, 188, 14);
		lblAdministracinDeClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblAdministracinDeClientes);

		lstClientes = new JList();
		lstClientes.setBounds(35, 87, 201, 179);
		panel.add(lstClientes);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(35, 56, 201, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblBuscarCliente = new JLabel("Buscar Cliente");
		lblBuscarCliente.setBounds(35, 41, 79, 14);
		panel.add(lblBuscarCliente);

		JLabel lblBuscarPor = new JLabel("Buscar Por:");
		lblBuscarPor.setBounds(245, 41, 79, 14);
		panel.add(lblBuscarPor);

		cmbBuscarFiltro = new JComboBox();
		cmbBuscarFiltro.setBounds(246, 56, 118, 20);
		panel.add(cmbBuscarFiltro);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(88, 277, 89, 23);
		panel.add(btnNuevo);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(205, 277, 89, 23);
		panel.add(btnCerrar);

		btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.setBounds(246, 87, 89, 23);
		panel.add(btnVerDatos);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(246, 121, 89, 23);
		panel.add(btnEliminar);

		cmbBuscarFiltro.removeAllItems();
		cmbBuscarFiltro.addItem("Nombre");
		cmbBuscarFiltro.addItem("Apellido");
		cmbBuscarFiltro.addItem("DNI");
	}
}
