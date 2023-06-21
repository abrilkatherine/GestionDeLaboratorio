package main.uade.edu.ar.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditarPaciente extends JDialog {
    private JPanel contentPane;
    private JTextField nombrePacienteTextField;
    private JTextField dniTextField;
    private JTextField emailTextField;
    private JTextField apellidoTextField;
    private JTextField edadTextField;
    private JTextField generoTextField;
    private JButton guardarButton;
    private JButton cancelarCambiosButton;

    public EditarPaciente() {
        initializeUI();
        setListeners();
        cargarDatos();
    }

    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nombrePaciente = new JLabel("Nombre de Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(nombrePaciente, gbc);

        nombrePacienteTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(nombrePacienteTextField, gbc);

        JLabel apellidoPaciente = new JLabel("Apellido de Paciente:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(nombrePaciente, gbc);

        apellidoTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(apellidoTextField, gbc);

        JLabel dniLabel = new JLabel("DNI:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        contentPane.add(dniLabel, gbc);

        dniTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(dniTextField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        contentPane.add(emailLabel, gbc);

        emailTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(emailTextField, gbc);

        JLabel generoLabel = new JLabel("genero:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        contentPane.add(generoLabel, gbc);

        generoTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(generoTextField, gbc);


        JLabel edadLabel = new JLabel("Edad:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        contentPane.add(edadLabel, gbc);

        edadTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(edadTextField, gbc);


        // Botones
        guardarButton = new JButton("Guardar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(guardarButton, gbc);

        cancelarCambiosButton = new JButton("Cancelar Cambios");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(cancelarCambiosButton, gbc);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setSize(400, 300); // Establecer el tamaño personalizado aquí
    }

    private void setListeners() {
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onGuardar();
            }
        });

        cancelarCambiosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void cargarDatos() {
        // Cargar los datos pre-cargados
        nombrePacienteTextField.setText("123");
        dniTextField.setText("Calle 123");
        apellidoTextField.setText("Juan Pérez");
        emailTextField.setText("email@email.com");
        generoTextField.setText("MASC");
        edadTextField.setText("15");

    }

    private void onGuardar() {
        // Acciones de guardar
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EditarPaciente dialog = new EditarPaciente();
                dialog.setVisible(true);
            }
        });
    }
}
