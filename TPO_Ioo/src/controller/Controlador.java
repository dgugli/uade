package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Cliente;
import view.Abm;
import view.vDatosCliente;

public class Controlador implements ActionListener, KeyListener {

	private Cliente cliente;
	private Abm abm;
	private vDatosCliente vDatosCli;
	DefaultListModel listModel = null;
	List<Cliente> clientes;
	int maxID;

	public Controlador(Cliente cliente, Abm abm) {
		this.cliente = cliente;
		this.abm = abm;
		this.vDatosCli = new vDatosCliente();
		this.abm.btnNuevo.addActionListener(this);
		this.abm.btnCerrar.addActionListener(this);
		this.abm.cmbBuscarFiltro.addActionListener(this);
		this.abm.btnVerDatos.addActionListener(this);
		this.abm.btnEliminar.addActionListener(this);
		this.abm.txtBuscar.addActionListener(this);
		this.abm.txtBuscar.addKeyListener(this);
		this.clientes = new ArrayList<>();
		this.vDatosCli.btnGuardar.addActionListener(this);
		this.vDatosCli.btnCerrar.addActionListener(this);
		listModel = new DefaultListModel();
		this.abm.lstClientes.setModel(listModel);
		maxID = 1;
	}

	public void IniciarVista() {
		this.abm.setTitle("ABM Clientes");
		this.abm.pack();
		this.abm.setBounds(0, 0, 389, 387);
		this.abm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.abm.setLocationRelativeTo(null);
		this.abm.setVisible(true);
	}

	public void buscar(String buscar) {
		DefaultListModel listabuscar = new DefaultListModel<>();
		String filtro = String.valueOf(this.abm.cmbBuscarFiltro.getSelectedItem());
		for (Cliente cli : clientes) {
			if (filtro.equals("Nombre")) {
				if (cli.getNombre().toLowerCase().contains(buscar.toLowerCase()))
					listabuscar.addElement(cli.getDni() + " - " + cli.getApellido() + ", " + cli.getNombre());
			} else if (filtro.equals("Apellido")) {
				if (cli.getApellido().toLowerCase().contains(buscar.toLowerCase()))
					listabuscar.addElement(cli.getDni() + " - " + cli.getApellido() + ", " + cli.getNombre());
			} else {
				if (cli.getDni().toLowerCase().contains(buscar.toLowerCase()))
					listabuscar.addElement(cli.getDni() + " - " + cli.getApellido() + ", " + cli.getNombre());
			}
		}
		this.abm.lstClientes.setModel(listabuscar);
		this.abm.lstClientes.repaint();
	}
	
	public void abrirDatosCli() {
		this.vDatosCli.setTitle("Datos Cliente");
		this.vDatosCli.pack();
		this.vDatosCli.cliente = null;
		this.vDatosCli.setBounds(0, 0, 316, 258);
		this.vDatosCli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.vDatosCli.setLocationRelativeTo(null);
		this.vDatosCli.setVisible(true);
	}

	public void abrirDatosCli(Cliente cli) {
		this.vDatosCli.setTitle("Datos Cliente");
		this.vDatosCli.pack();
		this.vDatosCli.cliente = cli;
		this.vDatosCli.setBounds(0, 0, 316, 258);
		this.vDatosCli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.vDatosCli.setLocationRelativeTo(null);
		this.vDatosCli.setVisible(true);
	}

	public void limpiarCamposVDatosCli() {
		this.vDatosCli.txtApellido.setText("");
		this.vDatosCli.txtNombre.setText("");
		this.vDatosCli.txtDni.setText("");
		this.vDatosCli.txtDireccion.setText("");
	}

	public void actualizarLista() {
		listModel.removeAllElements();
		for (Cliente c : clientes)
			listModel.addElement(c.getDni() + " - " + c.getApellido() + ", " + c.getNombre());
		this.abm.lstClientes.setModel(listModel);
		this.abm.lstClientes.repaint();
	}

	public Cliente seleccionarCliente(String cliente) {
		Cliente encontrado = null;
		for (Cliente cl : clientes)
			if (cl.getDni().equals(cliente))
				encontrado = cl;
		return encontrado;
	}

	public String getDNI(String val) {
		return val.substring(0, val.indexOf("-")).trim();
	}

	public boolean actualizarDatosCliente(Cliente cli) {
		boolean resultado = false;
		for (Cliente c : clientes) {
			if (c.getDni().equals(cli.getDni())) {
				c = cli;
				resultado = true;
			}
		}
		return resultado;
	}

	public boolean eliminarCliente(String dni) {
		boolean resultado = false;
		Cliente eliminar = null;
		for (Cliente c : clientes) {
			if (c.getDni().equals(dni)) {
				eliminar = c;
				resultado = true;
			}
		}
		if (eliminar != null)
			clientes.remove(eliminar);
		return resultado;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	   
	  if (e.getSource()==this.abm.txtBuscar)
	   {
		  buscar(this.abm.txtBuscar.getText());
	   }
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		// Eventos ABM
		if (abm.btnNuevo == evento.getSource()) {
			abrirDatosCli();
			limpiarCamposVDatosCli();
		}

		if (abm.btnCerrar == evento.getSource()) {
			this.abm.setVisible(false);
		}

		if (abm.btnEliminar == evento.getSource()) {
			if (abm.lstClientes.getSelectedIndex() != -1) {
				if (JOptionPane.showConfirmDialog(null, "Desea eliminar el cliente de la base de datos?",
						"ABM Clientes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String cc = getDNI(String.valueOf(abm.lstClientes.getSelectedValue()));
					if (eliminarCliente(cc)) {
						actualizarLista();
					}
				}
			}
		}

		if (abm.btnVerDatos == evento.getSource()) {
			if (abm.lstClientes.getSelectedIndex() != -1) {
				Cliente seleccionado = seleccionarCliente(getDNI(String.valueOf(abm.lstClientes.getSelectedValue())));
				if (seleccionado != null) {
					abrirDatosCli(seleccionado);
				} else {
					JOptionPane.showMessageDialog(null,
							"Se produjo un error al intentar recuperar datos del cliente seleccionado.", "ABM Clientes",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		// Eventos Datos Cliente
		if (vDatosCli.btnCerrar == evento.getSource()) {
			this.vDatosCli.setVisible(false);
		}

		if (vDatosCli.btnGuardar == evento.getSource()) {
			if (this.vDatosCli.cliente == null) { // Nuevo Cliente
				if (JOptionPane.showConfirmDialog(null, "Desea agregar el cliente a la base de datos?", "ABM Clientes",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					Cliente nuevo = new Cliente();
					nuevo.setId(maxID + 1);
					nuevo.setNombre(vDatosCli.txtNombre.getText());
					nuevo.setApellido(vDatosCli.txtApellido.getText());
					nuevo.setDni(vDatosCli.txtDni.getText());
					try {
						clientes.add(nuevo);
						maxID++;
						vDatosCli.setVisible(false);
						actualizarLista();
						JOptionPane.showMessageDialog(null, "Se ha guardado el cliente correctamente", "ABM Clientes",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								"Se produjo un error al intentar guardar el nuevo Cliente en la base de datos",
								"ABM Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else { // Actualizar Cliente existente
				if (JOptionPane.showConfirmDialog(null, "Desea actualizar los datos del cliente en la base de datos?",
						"ABM Clientes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						vDatosCli.cliente.setApellido(vDatosCli.txtApellido.getText());
						vDatosCli.cliente.setNombre(vDatosCli.txtNombre.getText());
						vDatosCli.cliente.setDni(vDatosCli.txtDni.getText());
						vDatosCli.cliente.setDireccion(vDatosCli.txtDireccion.getText());
						if (actualizarDatosCliente(this.vDatosCli.cliente)) {
							JOptionPane.showMessageDialog(null, "Se han actualizado los datos del cliente correctamente",
									"ABM Clientes", JOptionPane.INFORMATION_MESSAGE);
						vDatosCli.setVisible(false);
						actualizarLista();
					} else {
						JOptionPane.showMessageDialog(null, "No se han podido actualizar los datos del cliente.",
								"ABM Clientes", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
