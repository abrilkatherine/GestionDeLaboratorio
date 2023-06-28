package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.enums.Genero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AgregarPaciente extends JDialog {
    private JPanel contentPane;

    private JTextField nombreTextField;

    private JTextField apellidoTextField;

    private JTextField dniTextField;

    private JTextField emailTextField;

    private JTextField domicilioTextField;

    private JTextField edadTextField;

    private JButton guardarButton;

    private ButtonGroup generoButtonGroup;

    private JRadioButton generoRadioButtonFemenino;
    private JRadioButton generoRadioButtonMasculino;

    private PacienteController pacienteController;

    private PacientesTodas pacientesTodas;


    public AgregarPaciente(PacienteController pacienteController, PacientesTodas pacientesTodas) {
        this.pacienteController = pacienteController;
        this.pacientesTodas = pacientesTodas;
        initializeUI();
        setListeners();
    }

    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre del paciente
        JLabel nombrePacienteLabel = new JLabel("Nombre del paciente:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(nombrePacienteLabel, gbc);

        nombreTextField = createPlaceholderTextField("Ingrese el nombre del paciente");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(nombreTextField, gbc);

        // Apellido del paciente
        JLabel apellidoLabel = new JLabel("Apellido:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        contentPane.add(apellidoLabel, gbc);

        apellidoTextField = createPlaceholderTextField("Ingrese el apellido");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        contentPane.add(apellidoTextField, gbc);

        // email
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        contentPane.add(emailLabel, gbc);

        emailTextField = createPlaceholderTextField("Ingrese el mail");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(emailTextField, gbc);

        JLabel dniLabel = new JLabel("Dni:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        contentPane.add(dniLabel, gbc);

        dniTextField = createPlaceholderTextField("Ingrese el dni");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        contentPane.add(dniTextField, gbc);

        JLabel edadLabel = new JLabel("Edad:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        contentPane.add(edadLabel, gbc);

        edadTextField = createPlaceholderTextField("Ingrese la edad");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        contentPane.add(edadTextField, gbc);

        JLabel generoLabel = new JLabel("Genero:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        contentPane.add(generoLabel, gbc);

        generoRadioButtonMasculino = new JRadioButton("Masculino");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        contentPane.add(generoRadioButtonMasculino, gbc);

        generoRadioButtonFemenino = new JRadioButton("Femenino");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        contentPane.add(generoRadioButtonFemenino, gbc);

        generoButtonGroup = new ButtonGroup();
        generoButtonGroup.add(generoRadioButtonMasculino);
        generoButtonGroup.add(generoRadioButtonFemenino);

        JLabel domicilioLabel = new JLabel("Domicilio:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.0;
        contentPane.add(domicilioLabel, gbc);

        domicilioTextField = createPlaceholderTextField("Ingrese el domicilio");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        contentPane.add(domicilioTextField, gbc);

        // Botón Guardar
        guardarButton = new JButton("Guardar");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(guardarButton, gbc);

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

    private JTextField createPlaceholderTextField(String placeholderText) {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        textField.setForeground(Color.GRAY);

        textField.setText(placeholderText);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        return textField;
    }

    private void setListeners() {
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onGuardar();
            }
        });
    }

    private void onGuardar() {
        // Acciones de guardar
        String nombrePaciente = nombreTextField.getText();
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
        PacienteDto nuevoPaciente = new PacienteDto(2, Integer.parseInt(edadPaciente), generoPaciente, nombrePaciente, Integer.parseInt(dniPaciente), domicilioPaciente, emailPaciente, apellidoPaciente);
        try {
            pacienteController.crearPaciente(nuevoPaciente);
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

