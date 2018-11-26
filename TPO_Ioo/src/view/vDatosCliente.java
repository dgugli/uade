package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class vDatosCliente extends JFrame {
	public JTextField txtNombre;
	public JTextField txtApellido;
	public JTextField txtDni;
	public JTextField txtDireccion;
	public JButton btnGuardar;
	public JButton btnCerrar;
	
	public vDatosCliente() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 316, 198);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(23, 43, 60, 14);
		panel.add(lblNewLabel);
		
		JLabel lblDatosDelCliente = new JLabel("Datos del Cliente");
		lblDatosDelCliente.setBounds(84, 11, 140, 20);
		lblDatosDelCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblDatosDelCliente);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(23, 68, 60, 14);
		panel.add(lblApellido);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(23, 93, 60, 14);
		panel.add(lblDni);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccin.setBounds(23, 118, 60, 14);
		panel.add(lblDireccin);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(93, 40, 177, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(93, 65, 177, 20);
		panel.add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(93, 90, 177, 20);
		panel.add(txtDni);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(93, 115, 177, 20);
		panel.add(txtDireccion);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(53, 164, 89, 23);
		panel.add(btnGuardar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(167, 164, 89, 23);
		panel.add(btnCerrar);
	}

}
