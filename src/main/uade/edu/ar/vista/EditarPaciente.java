package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.enums.Genero;

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
    private JRadioButton generoRadioButtonFemenino;
    private JRadioButton generoRadioButtonMasculino;
    private JTextField domicilioTextField;
    private JButton guardarButton;
    private JButton cancelarCambiosButton;

    private PacienteDto paciente;

    private PacienteController pacienteController;

    private PacientesTodas pacientesTodas;

    public EditarPaciente(PacienteDto paciente,PacienteController pacienteController, PacientesTodas pacientesTodas) {
        this.paciente = paciente;
        this.pacientesTodas = pacientesTodas;
        this.pacienteController = pacienteController;
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
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(apellidoPaciente, gbc);

        apellidoTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
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
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        contentPane.add(emailTextField, gbc);

        JLabel generoLabel = new JLabel("Género:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        contentPane.add(generoLabel, gbc);

        ButtonGroup generoButtonGroup = new ButtonGroup();

        generoRadioButtonFemenino = new JRadioButton("Femenino");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        contentPane.add(generoRadioButtonFemenino, gbc);
        generoButtonGroup.add(generoRadioButtonFemenino);

        generoRadioButtonMasculino = new JRadioButton("Masculino");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        contentPane.add(generoRadioButtonMasculino, gbc);
        generoButtonGroup.add(generoRadioButtonMasculino);

        JLabel edadLabel = new JLabel("Edad:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        contentPane.add(edadLabel, gbc);

        edadTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        contentPane.add(edadTextField, gbc);

        JLabel domicilioLabel = new JLabel("Domicilio:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.0;
        contentPane.add(domicilioLabel, gbc);

        domicilioTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        contentPane.add(domicilioTextField, gbc);

        guardarButton = new JButton("Guardar");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(guardarButton, gbc);

        cancelarCambiosButton = new JButton("Cancelar Cambios");
        gbc.gridx = 1;
        gbc.gridy = 7;
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

        setSize(400, 300);
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
        nombrePacienteTextField.setText(paciente.getNombre());
        dniTextField.setText(String.valueOf(paciente.getDni()));
        apellidoTextField.setText(paciente.getApellido());
        emailTextField.setText(paciente.getEmail());
        edadTextField.setText(String.valueOf(paciente.getEdad()));
        domicilioTextField.setText(paciente.getDomicilio());
        if (paciente.getGenero() == Genero.FEMENINO) {
            System.out.print(paciente.getGenero());
            generoRadioButtonFemenino.setSelected(true);
        } else if (paciente.getGenero() == Genero.MASCULINO) {
            generoRadioButtonMasculino.setSelected(true);
        }
    }

    private void onGuardar() {
        String nombrePaciente = nombrePacienteTextField.getText();
        String apellidoPaciente = apellidoTextField.getText();
        String emailPaciente = emailTextField.getText();
        String dniPaciente = dniTextField.getText();
        String edadPaciente = edadTextField.getText();
        String domicilioPaciente = domicilioTextField.getText();
        Genero generoPaciente;
        if(generoRadioButtonMasculino.isSelected()){
            generoPaciente = Genero.MASCULINO;
        } else {
            generoPaciente = Genero.FEMENINO;
        }
        PacienteDto pacienteEditado = new PacienteDto(paciente.getId(), Integer.parseInt(edadPaciente), generoPaciente, nombrePaciente, Integer.parseInt(dniPaciente), domicilioPaciente, emailPaciente, apellidoPaciente);
        try {
            pacienteController.modificarPaciente(pacienteEditado);
            pacientesTodas.actualizarTablaPacientes();
            dispose();
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace(); // Imprimir información de la excepción
            // Opcional: Mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(this, "Error al crear el paciente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
