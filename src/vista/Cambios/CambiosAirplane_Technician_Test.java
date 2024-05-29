package vista.Cambios;

import modelo.Airplane_Technician_Test;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosAirplane_Technician_Test extends VentanaExterna {

    CambiosAirplane_Technician_Test ref;
    public CambiosAirplane_Technician_Test() {
        ref = this;
        w = 900;
        h = 600;
        celh = 20;
        celw = 20;
        title = "Modificar Prueba";
        btnAccion = "MODIFICAR";

        lbls = Airplane_Technician_Test.obtenerLabels();
        cps = Airplane_Technician_Test.obtenerComponentes();

        tipos = Airplane_Technician_Test.obtenerTipoDato();
        lgs = Airplane_Technician_Test.obtenerLongitudes();
        nnl = Airplane_Technician_Test.obtenerNoNulos();

        autoGenerar("Airplane_Technician_Test", "MODIFICAR PRUEBA", h / 5, 1, 1, 10);
        panel.setBackground(new Color(184, 197, 251));
        activarBotonModificar("Operacion exitosa", "Registro duplicado", "Las credenciales no coinciden, revise las experiencias y el numero de avion", "Modificacion", 3);

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
