package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Cliente;
import view.Abm;
import view.vDatosCliente;

public class Controlador implements ActionListener {

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
		this.abm.setBounds(0, 0, 400, 385);
		this.abm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.abm.setLocationRelativeTo(null);
		this.abm.setVisible(true);
	}

	public void abrirDatosCli() {
		this.vDatosCli.setTitle("Datos Cliente");
		this.vDatosCli.pack();
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

	public boolean eliminarCliente(String dni) {
		boolean resultado = false;
		for (Cliente c : clientes) {
			if (c.getDni().equals(dni)) {
				clientes.remove(c);
				resultado = true;
			}
		}
		return resultado;
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
					if (eliminarCliente(getDNI(String.valueOf(abm.lstClientes.getSelectedValue())))) {
						actualizarLista();
					}
				}
			}
		}

		if (abm.btnVerDatos == evento.getSource()) {
			if (abm.lstClientes.getSelectedIndex() != -1) {
				Cliente seleccionado = seleccionarCliente(getDNI(String.valueOf(abm.lstClientes.getSelectedValue())));
				if (seleccionado != null) {
					vDatosCli.setVisible(true);
					vDatosCli.txtDni.setText("");
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
							"Se produjo un error al intentar guardar el nuevo Cliente en la base de datos", "ABM Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
