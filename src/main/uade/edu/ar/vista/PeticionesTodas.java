package main.uade.edu.ar.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.uade.edu.ar.controller.PeticionController;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.model.Peticion;
import java.lang.reflect.Type;
import java.util.List;
import main.uade.edu.ar.dao.PeticionDao;

public class PeticionesTodas {

    private  PeticionController peticionController;
    private PeticionesTodas peticionesTodas;

    private DefaultTableModel tableModel;

    private List<PeticionDto> peticionesLista;

    public  PeticionesTodas(PeticionController peticionController){
        this.peticionController = peticionController;
        this.peticionesTodas = this;
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
        JLabel titleLabel = new JLabel("Peticiones");
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
            AgregarPeticion agregarPeticion = new AgregarPeticion(peticionController, this);
            agregarPeticion.setVisible(true);
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
        tableModel.addColumn("ID");
        tableModel.addColumn("Practicas");
        tableModel.addColumn("Editar");
        tableModel.addColumn("Eliminar");

        // Agregar filas de ejemplo a la tabla
            peticionesLista = peticionController.getAllPeticiones();

            for (PeticionDto peticion : peticionesLista) {
                tableModel.addRow(new Object[]{peticion.getId(),"Ver", "Info", "Eliminar"});
            }

        // Crear la tabla y configurar el modelo
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(2).setPreferredWidth(50); // Ancho de la columna "Editar"
        //table.getColumnModel().getColumn(3).setPreferredWidth(70); // Ancho de la columna "Eliminar"

        // Agregar MouseListener a la tabla para detectar clics en las columnas "Editar" y "Eliminar"
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                // Verificar si se hizo clic en la columna "Editar"
                if (column == 2 && row < table.getRowCount()) {
                    int valorColumnaId = (int) tableModel.getValueAt(row, 0);


                    PeticionDto peticion = null;
                    for (PeticionDto p : peticionesLista) {
                        if (p.getId() == valorColumnaId) {
                            peticion = p;
                            break;
                        }
                    }
                    // Crear y mostrar el diálogo de editar sucursal
                    if (peticion != null) {
                        // Crear y mostrar el diálogo de editar sucursal, pasando la sucursal correspondiente
                        EditarPeticion editarPeticion = new EditarPeticion(peticion, peticionController, peticionesTodas);
                        editarPeticion.setVisible(true);
                    }
                }

                if(column == 1 && row < table.getRowCount()){
                    int valorColumnaId = (int) tableModel.getValueAt(row, 0);

                    PeticionDto peticion = null;
                    for (PeticionDto p : peticionesLista) {
                        if (p.getId() == valorColumnaId) {
                            peticion = p;
                            break;
                        }
                    }
                    // Crear y mostrar el diálogo de editar sucursal
                    if (peticion != null) {
                        // Crear y mostrar el diálogo de editar sucursal, pasando la sucursal correspondiente
                        PracticasXPeticion vistaPracticas = new PracticasXPeticion(peticion.getPracticas(), peticion.getId(), peticionController);
                        vistaPracticas.setVisible(true);
                    }
                }


                // Verificar si se hizo clic en la columna "Eliminar"
                if (column == 3 && row < table.getRowCount()) {
                    int confirm = JOptionPane.showConfirmDialog(table, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Eliminar la fila correspondiente
                        tableModel.removeRow(row);
                    }
                }

            }
        });

        return table;
    }

    public void actualizarTablaPeticiones() {
        tableModel.setRowCount(0); // Elimina todas las filas existentes en el modelo
        peticionesLista = peticionController.getAllPeticiones();
        for (PeticionDto peticion : peticionesLista) {
            tableModel.addRow(new Object[]{peticion.getId(),"Ver", "Info", "Eliminar"});
        }
    }
}
