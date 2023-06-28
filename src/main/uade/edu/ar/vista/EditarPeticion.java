package main.uade.edu.ar.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.controller.PeticionController;
import main.uade.edu.ar.controller.SucursalYUsuarioController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.dto.UsuarioDto;

import static main.uade.edu.ar.util.DateUtil.getFecha;

public class EditarPeticion extends JDialog {
    private JPanel contentPane;

    private JTextField obraSocial;

    private JTextField fechaCarga;

    private JTextField fechaEntrega;

    private JComboBox<String> sucursalComboBox;

    private JComboBox<String> pacienteComboBox;


    private JButton guardarButton;
    private JButton cancelarCambiosButton;
    private PeticionDto peticionDto;
    private List<SucursalDto> sucursales;
    private SucursalYUsuarioController sucursalYUsuarioController;

    private PacienteController pacienteController;

    private List<PacienteDto> pacientes;
    private PeticionController peticionController;
    private PeticionesTodas peticionesTodas;

    public EditarPeticion(PeticionDto peticionDto, PeticionController peticionController, PeticionesTodas peticionesTodas,  SucursalYUsuarioController sucursalYUsuarioController, PacienteController pacienteController) {
        this.peticionesTodas = peticionesTodas;
        this.sucursalYUsuarioController = sucursalYUsuarioController;
        this.pacienteController = pacienteController;
        this.peticionDto = peticionDto;
        this.peticionController = peticionController;
        cargarSucursales();
        cargarPacientes();
        initializeUI();
        setListeners();
        cargarDatos();
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

        obraSocial = new JTextField();
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

        fechaCarga = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        contentPane.add(fechaCarga, gbc);

        JLabel fechaFin = new JLabel("Fecha entrega:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(fechaFin, gbc);

        fechaEntrega = new JTextField();
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

        cancelarCambiosButton = new JButton("Cancelar");
        gbc.gridx = 2;
        gbc.gridy = 5;
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
        obraSocial.setText(peticionDto.getObraSocial());
        fechaCarga.setText(String.valueOf(peticionDto.getFechaCarga()));
        fechaEntrega.setText(String.valueOf(peticionDto.getFechaEntrega()));
        sucursalComboBox.setSelectedItem(getDisplayText(peticionDto.getSucursal()));
        pacienteComboBox.setSelectedItem(getDisplayTextPacientes(peticionDto.getPaciente()));
    }

    private String getDisplayText(SucursalDto sucursalDto) {
        return sucursalDto.getNumero() + " - " + sucursalDto.getId();
    }
    private String getDisplayTextPacientes(PacienteDto pacienteDto) {
        return pacienteDto.getNombre() + " - " + pacienteDto.getApellido();
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date fechaCarga = null;
        Date fechaEntrega = null;

        if (!fechaC.isEmpty() && !fechaC.equals("MMM dd, yyyy, hh:mm:ss a")) {
            try {
                fechaCarga = dateFormat.parse(fechaC);
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (!fechaI.isEmpty() && !fechaI.equals("MMM dd, yyyy, hh:mm:ss a")) {
            try {
                fechaEntrega = dateFormat.parse(fechaI);
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        PeticionDto nuevaPeticion = new PeticionDto(peticionDto.getId(), obra, fechaCarga, fechaEntrega, sucursal, paciente, peticionDto.getPracticas());
        try {
            peticionController.modificarPeticion(nuevaPeticion);
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
