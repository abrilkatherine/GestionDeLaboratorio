package main.uade.edu.ar.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import main.uade.edu.ar.dao.UsuarioDao;
import main.uade.edu.ar.model.Sucursal;
import main.uade.edu.ar.model.Usuario;

public class EditarSucursal extends JDialog {
    private JPanel contentPane;
    private JTextField numeroSucursalTextField;
    private JTextField direccionTextField;
    private JComboBox<String> responsableComboBox;
    private JButton guardarButton;
    private JButton cancelarCambiosButton;

    private Sucursal sucursal;
    private List<Usuario> usuarios; // Lista de usuarios existentes

    public EditarSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
        cargarUsuarios(); // Obtener la lista de usuarios existentes
        initializeUI();
        setListeners();
        cargarDatos();
    }

    private void cargarUsuarios() {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarios = usuarioDao.getAll(); // Obtener la lista de usuarios existentes
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        numeroSucursalTextField = new JTextField();
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

        direccionTextField = new JTextField();
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
        for (Usuario usuario : usuarios) {
            String displayText = usuario.getNombre() + " - " + usuario.getRol();
            responsableComboBox.addItem(displayText);
            if (usuario.equals(sucursal.getResponsableTecnico())) {
                responsableComboBox.setSelectedItem(usuario);
            }
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(responsableComboBox, gbc);

        // Botones
        guardarButton = new JButton("Guardar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(guardarButton, gbc);

        cancelarCambiosButton = new JButton("Cancelar Cambios");
        gbc.gridx = 1;
        gbc.gridy = 3;
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
        numeroSucursalTextField.setText(String.valueOf(sucursal.getNumero()));
        direccionTextField.setText(sucursal.getDireccion());
        responsableComboBox.setSelectedItem(getDisplayText(sucursal.getResponsableTecnico()));
    }

    private String getDisplayText(Usuario usuario) {
        return usuario.getNombre() + " - " + usuario.getRol();
    }

    private void onGuardar() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}