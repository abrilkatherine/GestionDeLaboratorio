package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.controller.PeticionController;
import main.uade.edu.ar.controller.SucursalYUsuarioController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.SucursalDto;
import static main.uade.edu.ar.util.DateUtil.getFecha;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AgregarPeticion extends JDialog {
    private JPanel contentPane;

    private JTextField obraSocial;

    private JTextField fechaCarga;

    private JTextField fechaEntrega;

    private JComboBox<String> sucursalComboBox;

    private JComboBox<String> pacienteComboBox;


    private JButton guardarButton;

    private PeticionController peticionController;

    private PeticionesTodas peticionesTodas;

    private List<SucursalDto> sucursales;

    private List<PacienteDto> pacientes;

    private PacienteController pacienteController;
    private SucursalYUsuarioController sucursalYUsuarioController;

    public AgregarPeticion(PeticionController peticionController, PeticionesTodas peticionesTodas) {
        this.peticionController = peticionController;
        this.peticionesTodas = peticionesTodas;
        try{
            this.sucursalYUsuarioController = SucursalYUsuarioController.getInstance();
            this.pacienteController = PacienteController.getInstance();
        }  catch (Exception e) {
            e.printStackTrace();
        }

        cargarSucursales();
        cargarPacientes();
        initializeUI();
        setListeners();
    }

    private void cargarSucursales() {
        sucursales = sucursalYUsuarioController.getAllSucursales();
    }

    private void cargarPacientes(){pacientes = pacienteController.getAllPacientes();}


    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel obraSocialLabel = new JLabel("Obra social:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(obraSocialLabel, gbc);

        obraSocial = createPlaceholderTextField("Ingrese la obra social");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(obraSocial, gbc);

        JLabel sucursalLabel = new JLabel("Sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        contentPane.add(sucursalLabel, gbc);

        sucursalComboBox = new JComboBox<>();
        for (SucursalDto sucursal : sucursales) {
            String displayText = sucursal.getNumero() + " - " + sucursal.getId();
            sucursalComboBox.addItem(displayText);
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        contentPane.add(sucursalComboBox, gbc);

        JLabel pacienteLabel = new JLabel("Paciente:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        contentPane.add(pacienteLabel, gbc);

        pacienteComboBox = new JComboBox<>();
        for (PacienteDto paciente : pacientes) {
            String displayText = paciente.getNombre() + " - " + paciente.getApellido();
            pacienteComboBox.addItem(displayText);
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(pacienteComboBox, gbc);

        JLabel fechaInicio = new JLabel("Fecha carga:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(fechaInicio, gbc);

        fechaCarga = createPlaceholderTextField("MM/DD/AAAA");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        contentPane.add(fechaCarga, gbc);

        JLabel fechaFin = new JLabel("Fecha entrega:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(fechaFin, gbc);

        fechaEntrega = createPlaceholderTextField("MM/DD/AAAA");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        contentPane.add(fechaEntrega, gbc);

        // Botón Guardar
        guardarButton = new JButton("Guardar");
        gbc.gridx = 1;
        gbc.gridy = 5;
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
        String obra = obraSocial.getText();
        String sucursalText = (String) sucursalComboBox.getSelectedItem();
        SucursalDto sucursal = findSucursalByDisplayText(sucursalText);
        String pacienteText = (String) pacienteComboBox.getSelectedItem();
        PacienteDto paciente = findPacienteByDisplayText(pacienteText);
        String fechaC = fechaCarga.getText();
        String fechaI = fechaEntrega.getText();


        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date fechaCarga = null;
        Date fechaEntrega = null;

        try {
            fechaCarga = dateFormat.parse(fechaC);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }

        try {
            fechaEntrega = dateFormat.parse(fechaI);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }

        Random random = new Random();
        PeticionDto nuevaPeticion = new PeticionDto(random.nextInt(), obra, fechaCarga, fechaEntrega, sucursal, paciente);
        try {
            peticionController.crearPeticion(nuevaPeticion);
            peticionesTodas.actualizarTablaPeticiones();
            dispose();
        } catch (Exception e) {
            // Manejo de la excepción
            e.printStackTrace(); // Imprimir información de la excepción
            // Opcional: Mostrar un mensaje de error al usuario
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private SucursalDto findSucursalByDisplayText(String displayText) {
        for (SucursalDto sucursal : sucursales) {
            String sucursalDisplayText = sucursal.getNumero() + " - " + sucursal.getId();
            if (sucursalDisplayText.equals(displayText)) {
                return sucursal;
            }
        }
        return null;
    }

    private PacienteDto findPacienteByDisplayText(String displayText) {
        for (PacienteDto pacienteDto : pacientes) {
            String sucursalDisplayText = pacienteDto.getNombre() + " - " + pacienteDto.getApellido();
            if (sucursalDisplayText.equals(displayText)) {
                return pacienteDto;
            }
        }
        return null;
    }

    private void onCancel() {
        dispose();
    }


}

