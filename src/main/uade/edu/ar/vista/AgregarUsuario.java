package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.SucursalYUsuarioController;
import main.uade.edu.ar.dto.UsuarioDto;
import main.uade.edu.ar.enums.Roles;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AgregarUsuario extends JDialog {

    private JPanel contentPane;

    private JTextField nombreTextField;

    private JTextField contraseniaTextField;

    private JTextField fechanacimientoTextField;

    private JComboBox<Roles> rolComboBox;


    private JButton guardarButton;

    private SucursalYUsuarioController sucursalYUsuarioController;

    private UsuariosTodos usuariosTodos;

    public AgregarUsuario(SucursalYUsuarioController sucursalYUsuarioController, UsuariosTodos usuariosTodos) {
        this.sucursalYUsuarioController = sucursalYUsuarioController;
        this.usuariosTodos = usuariosTodos;
        initializeUI();
        setListeners();
    }

    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre del usuario
        JLabel nombreUsuarioLabel = new JLabel("Nombre del usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(nombreUsuarioLabel, gbc);

        nombreTextField = createPlaceholderTextField("Ingrese el nombre del usuario");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(nombreTextField, gbc);

        // Apellido del usuario
        JLabel contraseniaLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        contentPane.add(contraseniaLabel, gbc);

        contraseniaTextField = createPlaceholderTextField("Ingrese la contraseña");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        contentPane.add(contraseniaTextField, gbc);

        // email
        JLabel fechanacimientoLabel = new JLabel("Fecha de nacimiento:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        contentPane.add(fechanacimientoLabel, gbc);

        fechanacimientoTextField = createPlaceholderTextField("MM/DD/AAAA");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(fechanacimientoTextField, gbc);

        JLabel rolLabel = new JLabel("Rol:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        contentPane.add(rolLabel, gbc);

        rolComboBox = new JComboBox<>(Roles.values());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        contentPane.add(rolComboBox, gbc);

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
        String nombreUsuario = nombreTextField.getText();
        String contraseniaUsuario = contraseniaTextField.getText();
        String fechanacimientoUsuario = fechanacimientoTextField.getText();
        Roles rolUsuario = (Roles) rolComboBox.getSelectedItem();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = dateFormat.parse(fechanacimientoUsuario);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int id = random.nextInt();

        UsuarioDto nuevoUsuario = new UsuarioDto(id, nombreUsuario, contraseniaUsuario, fechaNacimiento, rolUsuario);

        try {
            sucursalYUsuarioController.crearUsuario(nuevoUsuario);
            usuariosTodos.actualizarTablaUsuarios();
            dispose();
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace(); // Imprimir información de la excepción
            // Opcional: Mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(this, "Error al crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}