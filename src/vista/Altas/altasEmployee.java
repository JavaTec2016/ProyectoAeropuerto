package vista.Altas;

import modelo.Employee;
import modelo.Model;
import modelo.ModeloBD;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class altasEmployee extends VentanaExterna {
    altasEmployee ref;

    public altasEmployee(){
        ref = this;
        celh=20;
        celw=20;
        w = 900;
        h = 900;
        title = "Agregar Empleado";
        btnAccion = "AGREGAR";
        //generacion automatica del formulario

        lbls = Employee.obtenerLabels();
        cps = Employee.obtenerComponentes();

        tipos = Employee.obtenerTipoDato();
        lgs = Employee.obtenerLongitudes();
        nnl = Employee.obtenerNoNulos();

        autoGenerar("Employee", "AGREGAR EMPLEADO", h/6, 1, 2, 13);
        panel.setBackground(new Color(184, 251, 184));

        activarBotonValidar("Operacion exitosa", "Registro duplicado", "Error de relacion", "Registro agregado");

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

    }
}
