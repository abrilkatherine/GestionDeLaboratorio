package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.SucursalYUsuarioController;
import main.uade.edu.ar.dto.UsuarioDto;
import main.uade.edu.ar.dto.SucursalDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class AgregarSucursal extends JDialog {
    private JPanel contentPane;
    private JTextField numeroSucursalTextField;
    private JTextField direccionTextField;
    private JComboBox<String> responsableComboBox;
    private List<UsuarioDto> usuarios;
    private JButton guardarButton;
    private SucursalYUsuarioController sucursalYUsuarioController;

    private SucursalTodas sucursalTodas;

    public AgregarSucursal(SucursalYUsuarioController sucursalYUsuarioController, SucursalTodas sucursalTodas) {
        this.sucursalTodas = sucursalTodas;
        this.sucursalYUsuarioController = sucursalYUsuarioController;
        cargarUsuarios();
        initializeUI();
        setListeners();
    }

    private void cargarUsuarios() {
        usuarios = sucursalYUsuarioController.getAllUsuarios();
    }

    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Numero de Sucursal
        JLabel numeroSucursalLabel = new JLabel("Número de Sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(numeroSucursalLabel, gbc);

        numeroSucursalTextField = createPlaceholderTextField("Ingrese el número de sucursal");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(numeroSucursalTextField, gbc);

        // Dirección
        JLabel direccionLabel = new JLabel("Dirección:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        contentPane.add(direccionLabel, gbc);

        direccionTextField = createPlaceholderTextField("Ingrese la dirección");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        contentPane.add(direccionTextField, gbc);

        // Responsable
        JLabel responsableLabel = new JLabel("Responsable:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        contentPane.add(responsableLabel, gbc);

        responsableComboBox = new JComboBox<>();
        for (UsuarioDto usuario : usuarios) {
            String displayText = usuario.getNombre() + " - " + usuario.getRol();
            responsableComboBox.addItem(displayText);
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(responsableComboBox, gbc);

        // Botón Guardar/Agregar
        guardarButton = new JButton("Agregar");
        gbc.gridx = 1;
        gbc.gridy = 3;
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

        setSize(400, 300);
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
        String numeroSucursal = numeroSucursalTextField.getText();
        String direccion = direccionTextField.getText();
        String responsableText = (String) responsableComboBox.getSelectedItem();
        UsuarioDto responsable = findUsuarioByDisplayText(responsableText);
        Random random = new Random();
        int randomId = random.nextInt(1, 900);
        SucursalDto nuevaSucursal = new SucursalDto(randomId, Integer.parseInt(numeroSucursal), direccion, responsable);
        try {
            sucursalYUsuarioController.crearSucursal(nuevaSucursal);
            sucursalTodas.actualizarTablaSucursales();
            dispose();
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace(); // Imprimir información de la excepción
            // Opcional: Mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private UsuarioDto findUsuarioByDisplayText(String displayText) {
        for (UsuarioDto usuario : usuarios) {
            String usuarioDisplayText = usuario.getNombre() + " - " + usuario.getRol();
            if (usuarioDisplayText.equals(displayText)) {
                return usuario;
            }
        }
        return null;
    }

    private void onCancel() {
        dispose();
    }
}
