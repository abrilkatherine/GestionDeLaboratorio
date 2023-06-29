package main.uade.edu.ar.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.TableCellRenderer;

import main.uade.edu.ar.controller.PeticionController;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.UsuarioDto;

public class PracticasXPeticion extends JDialog {
    private DefaultTableModel tableModel;
    private JPanel contentPane;
    private JTable practicasTable;
    private JButton agregarButton;

    private List<PracticaDto> practicas;
    private int idPeticion;
    private PeticionController peticionController;

    public PracticasXPeticion(List<PracticaDto> practicas, int idPeticion, PeticionController peticionController) {
        this.practicas = practicas;
        this.idPeticion = idPeticion;
        this.peticionController = peticionController;
        initializeUI();
        cargarDatos();
    }

    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // Panel de título
        JLabel tituloLabel = new JLabel("Prácticas");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel tituloPanel = new JPanel();
        tituloPanel.add(tituloLabel);
        contentPane.add(tituloPanel, BorderLayout.NORTH);

        // Modelo de la tabla
        tableModel = new DefaultTableModel(new Object[]{"Id", "Nombre", "Info", "Eliminar"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3; // Columnas "Info" y "Eliminar" serán editables
            }
        };

        practicasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(practicasTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Renderizador de celda para el botón "Info"
        practicasTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());

        // Editor de celda para el botón "Info"
        practicasTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JTextField()));

        // Renderizador de celda para el botón "Eliminar"
        practicasTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());

        // Editor de celda para el botón "Eliminar"
        practicasTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));

        // Botón Agregar
        agregarButton = new JButton("Agregar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        contentPane.add(buttonPanel, BorderLayout.EAST);

        agregarButton.addActionListener(e ->{
            AgregarPracticaXPeticion agregarPracticaXPeticion = new AgregarPracticaXPeticion(peticionController, idPeticion, this);
            agregarPracticaXPeticion.setVisible(true);
        });

        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String buttonText;
        private boolean isPushed;

        public ButtonEditor(JTextField textField) {
            super(textField);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            buttonText = (value == null) ? "" : value.toString();
            button.setText(buttonText);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                int rowIndex = practicasTable.getSelectedRow();
                int columnIndex = practicasTable.getSelectedColumn();

                if (columnIndex == 2) {
                    // Botón "Info" - Obtener la información de la práctica correspondiente al botón "Info"
                    int practiceId = (int) practicasTable.getValueAt(rowIndex, 0); // Obtener el Id de la práctica
                    PracticaDto selectedPractica = obtenerPracticaPorId(practiceId);

                    // Redirigir a la clase "EditarPractica" con la información de la práctica seleccionada
                    if (selectedPractica != null) {
                        EditarPractica editarPractica = new EditarPractica(peticionController, selectedPractica, PracticasXPeticion.this);
                        editarPractica.setVisible(true);
                    }
                } else if (columnIndex == 3) {
                    // Botón "Eliminar" - Obtener el ID de la práctica correspondiente al botón "Eliminar"
                    int practiceId = (int) practicasTable.getValueAt(rowIndex, 0); // Obtener el Id de la práctica

                    int confirm = JOptionPane.showConfirmDialog(practicasTable, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            peticionController.borrarPractica(practiceId);
                            actualizarDatos();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
            isPushed = false;
            return buttonText;
        }

        private PracticaDto obtenerPracticaPorId(int practiceId) {
            for (PracticaDto practica : practicas) {
                if (practica.getId() == practiceId) {
                    return practica;
                }
            }
            return null;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    private void cargarDatos() {
        tableModel.setRowCount(0); // Limpiar las filas existentes

        List<PracticaDto> practicas = peticionController.getAllPracticasDePeticion(idPeticion);

        for (PracticaDto practica : practicas) {
            // Agregar la práctica a la tabla
            Object[] rowData = {practica.getId(), practica.getNombre(), "info", "Eliminar"};
            tableModel.addRow(rowData);
        }
    }

    public void actualizarDatos() {
        tableModel.setRowCount(0); // Limpiar las filas existentes

        List<PracticaDto> practicas = peticionController.getAllPracticasDePeticion(idPeticion);

        for (PracticaDto practica : practicas) {
            // Agregar la práctica a la tabla
            Object[] rowData = {practica.getId(), practica.getNombre(), "info", "Eliminar"};
            tableModel.addRow(rowData);
        }
    }
}
