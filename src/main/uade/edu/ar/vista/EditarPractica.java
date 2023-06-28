package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.PeticionController;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.ResultadoDto;
import main.uade.edu.ar.dto.UsuarioDto;
import main.uade.edu.ar.enums.Roles;
import main.uade.edu.ar.enums.TipoResultado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class EditarPractica extends JDialog {
    private JPanel contentPane;
    private JButton guardarButton;
    private JTextField horasFaltantesTextField;
    private JTextField grupoPracticaTextField;
    private JTextField nombrePracticaTextField;
    private JTextField codigoPracticaTextField;
    private JTextField valorResultadoTextField;
    private JComboBox<TipoResultado> tipoResultadoComboBox;
    private PeticionController peticionController;
    private PracticasXPeticion practicasXPeticion;
    private PracticaDto practica;
    public EditarPractica(PeticionController peticionController, PracticaDto practica, PracticasXPeticion practicasXPeticion){
        this.peticionController = peticionController;
        this.practica = practica;
        this.practicasXPeticion = practicasXPeticion;
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

        // Nombre del usuario
        JLabel codigoPracticaLabel = new JLabel("Codigo de la Práctica:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(codigoPracticaLabel, gbc);

        codigoPracticaTextField = createPlaceholderTextField("Ingrese el código de la práctica");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPane.add(codigoPracticaTextField, gbc);

        // Apellido del usuario
        JLabel nombrePracticaLabel = new JLabel("Nombre de la práctica:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        contentPane.add(nombrePracticaLabel, gbc);

        nombrePracticaTextField = createPlaceholderTextField("Ingrese el nombre de la práctica");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        contentPane.add(nombrePracticaTextField, gbc);

        // email
        JLabel grupoPracticaLabel = new JLabel("Grupo de la Práctica:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        contentPane.add(grupoPracticaLabel, gbc);

        grupoPracticaTextField = createPlaceholderTextField("Ingrese el grupo de la práctica");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        contentPane.add(grupoPracticaTextField, gbc);

        JLabel horasFaltantesLabel = new JLabel("Horas Faltantes:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        contentPane.add(horasFaltantesLabel, gbc);

        horasFaltantesTextField = createPlaceholderTextField("Ingrese las horas Faltantes");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        contentPane.add(horasFaltantesTextField, gbc);

        JLabel ValorResultadoLabel = new JLabel("Valor del Resultado:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0;
        contentPane.add(ValorResultadoLabel, gbc);

        valorResultadoTextField = createPlaceholderTextField("Ingrese Valor del Resultado");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        contentPane.add(valorResultadoTextField, gbc);

        JLabel rolLabel = new JLabel("Tipo de Resultado:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0;
        contentPane.add(rolLabel, gbc);

        tipoResultadoComboBox = new JComboBox<>(TipoResultado.values());
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        contentPane.add(tipoResultadoComboBox, gbc);

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
    private void cargarDatos() {
        codigoPracticaTextField.setText(String.valueOf(practica.getCodigo()));
        nombrePracticaTextField.setText(practica.getNombre());
        grupoPracticaTextField.setText(String.valueOf(practica.getGrupo()));
        horasFaltantesTextField.setText(String.valueOf(practica.getHorasFaltantes()));

        ResultadoDto resultado = practica.getResultado();
        if (resultado != null) {
            valorResultadoTextField.setText(String.valueOf(resultado.getValor()));
            tipoResultadoComboBox.setSelectedItem(resultado.getTipoResultado());
        } else {
            valorResultadoTextField.setText("");
            tipoResultadoComboBox.setSelectedItem(null);
        }
    }

    private void onGuardar() {
        // Acciones de guardar
        String codigoPractica = codigoPracticaTextField.getText();
        String nombrePractica = nombrePracticaTextField.getText();
        String grupoPractica = grupoPracticaTextField.getText();
        String horasFaltantes = horasFaltantesTextField.getText();
        String valorResultado = valorResultadoTextField.getText();
        TipoResultado tipoResultado = (TipoResultado) tipoResultadoComboBox.getSelectedItem();

        ResultadoDto nuevoresultado = new ResultadoDto(valorResultado, tipoResultado);
        PracticaDto nuevaPractica = new PracticaDto(practica.getId(), Integer.parseInt(codigoPractica), nombrePractica, Integer.parseInt(grupoPractica), Float.parseFloat(horasFaltantes), nuevoresultado);

        try {
            peticionController.modificarPractica(nuevaPractica);
            practicasXPeticion.actualizarDatos();
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
