package main.uade.edu.ar.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import main.uade.edu.ar.controller.SucursalYUsuarioController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.SucursalDto;


public class SucursalTodas {

    private SucursalYUsuarioController sucursalYUsuarioController;

    private DefaultTableModel tableModel;

    private SucursalTodas sucursalTodas;

    private List<SucursalDto> sucursalDtoList;

    public SucursalTodas(SucursalYUsuarioController sucursalYUsuarioController) {
        this.sucursalYUsuarioController = sucursalYUsuarioController;
        this.sucursalTodas = this;
        this.tableModel = new DefaultTableModel();
    }

    public JPanel createPanel() {
        // Crear un JPanel para contener todos los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar margen superior de 10 píxeles

        // Crear un JPanel para el encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BorderLayout());

        // Crear el título "Sucursales" a la izquierda
        JLabel titleLabel = new JLabel("Sucursales");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Crear el botón "Agregar" a la derecha
        JButton addButton = new JButton("Agregar");
        addButton.setContentAreaFilled(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setBackground(Color.decode("#0080F0"));

        // Agregar ActionListener al botón "Agregar"
        addButton.addActionListener(e -> {
            AgregarSucursal agregarSucursal = new AgregarSucursal(sucursalYUsuarioController, this);
            agregarSucursal.setVisible(true);
        });

        // Agregar el botón "Agregar" al JPanel del encabezado
        headerPanel.add(addButton, BorderLayout.EAST);

        // Agregar el JPanel del encabezado al JPanel principal
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear la tabla de sucursales
        JTable table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JTable createTable() {
        // Crear un modelo de tabla personalizado que haga que todas las celdas sean no editables
        tableModel.addColumn("Numero");
        tableModel.addColumn("Editar");
        tableModel.addColumn("Eliminar");

        // Obtener la lista de sucursales mediante el controlador
        sucursalDtoList = sucursalYUsuarioController.getAllSucursales();
        for (SucursalDto sucursal : sucursalDtoList) {
            tableModel.addRow(new Object[]{sucursal.getNumero(), "Info", "Eliminar"});
        }

        // Crear la tabla y configurar el modelo
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(1).setPreferredWidth(50); // Ancho de la columna "Editar"
        table.getColumnModel().getColumn(2).setPreferredWidth(70); // Ancho de la columna "Eliminar"

        // Agregar MouseListener a la tabla para detectar clics en las columnas "Editar" y "Eliminar"
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ;
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                // Verificar si se hizo clic en la columna "Editar"
                if (column == 1 && row < table.getRowCount()) {
                    // Obtener la información de la sucursal
                    int numero = (int) tableModel.getValueAt(row, 0);

                    SucursalDto sucursal = null;
                    for (SucursalDto s : sucursalDtoList) {
                        if (s.getNumero() == numero) {
                            sucursal = s;
                            break;
                        }
                    }

                    // Crear y mostrar el diálogo de editar sucursal
                    if (sucursal != null) {
                        EditarSucursal editarSucursal = new EditarSucursal(sucursal, sucursalYUsuarioController, sucursalTodas);
                        editarSucursal.setVisible(true);
                    }
                }

                // Verificar si se hizo clic en la columna "Eliminar"
                if (column == 2 && row < table.getRowCount()) {
                    int confirm = JOptionPane.showConfirmDialog(table, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Eliminar la fila correspondiente
                        int numero = (int) tableModel.getValueAt(row, 0);
                        SucursalDto sucursal = null;
                        for (SucursalDto s : sucursalDtoList) {
                            if (s.getNumero() == numero) {
                                sucursal = s;
                                break;
                            }
                        }
                        if (sucursal != null) {
                            try {
                                sucursalYUsuarioController.borrarSucursal(sucursal.getId());
                                tableModel.removeRow(row);
                            } catch (Exception exception) {
                                exception.printStackTrace(); // Imprimir información de la excepción
                                // Opcional: Mostrar un mensaje de error al usuario

                                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }

                    }
                }
            }
        });

        return table;
    }

    public void actualizarTablaSucursales() {
        tableModel.setRowCount(0);
        sucursalDtoList = null;// Elimina todas las filas existentes en el modelo
        sucursalDtoList = sucursalYUsuarioController.getAllSucursales();
        for (SucursalDto suc : sucursalDtoList) {
            tableModel.addRow(new Object[]{suc.getNumero(), "Info", "Eliminar"});
        }
    }
}

