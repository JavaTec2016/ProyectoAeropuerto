package vista.Cambios;

import modelo.Employee;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosEmployee extends VentanaExterna {
    CambiosEmployee ref;

    public CambiosEmployee(){
        ref = this;
        w = 900;
        h = 900;
        celh = 20;
        celw = 20;
        title = "Modificar Empleado";
        btnAccion = "MODIFICAR";

        lbls = Employee.obtenerLabels();
        cps = Employee.obtenerComponentes();

        tipos = Employee.obtenerTipoDato();
        lgs = Employee.obtenerLongitudes();
        nnl = Employee.obtenerNoNulos();

        autoGenerar("Employee", "MODIFICAR EMPLEADO", h / 5, 1, 2, 11);
        panel.setBackground(new Color(184, 197, 251));
        activarBotonModificar("Operacion exitosa", "Registro duplicado", "el SSN no existe", "Modificacion", 1);

        lblFont lblVacios = new lblFont("los espacios en blanco no sufriran cambios", "Arial", Font.ITALIC, 13, 0, 0, 0);
        panel.salida.add(panel.ras.encuadrarRelativo(lblVacios, 0, (int) (panel.h / 3), panel.w, panel.h));
        salida.get(salida.size() - 1).centerOffset(1, 1);
        lblVacios.setVerticalAlignment(SwingConstants.CENTER);
        lblVacios.setHorizontalAlignment(SwingConstants.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

    }
}
