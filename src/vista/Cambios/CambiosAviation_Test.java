package vista.Cambios;

import modelo.Aviation_Test;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosAviation_Test extends VentanaExterna {
    CambiosAviation_Test ref;

    public CambiosAviation_Test() {
        ref = this;
        w = 900;
        h = 400;
        celh = 20;
        celw = 20;
        title = "Modificar Tipo de Prueba";
        btnAccion = "MODIFICAR";

        lbls = Aviation_Test.obtenerLabels();
        cps = Aviation_Test.obtenerComponentes();

        tipos = Aviation_Test.obtenerTipoDato();
        lgs = Aviation_Test.obtenerLongitudes();
        nnl = Aviation_Test.obtenerNoNulos();

        autoGenerar("Aviation_Test", "MODIFICAR TIPO DE PRUEBA", h / 5, 1, 1, 10);
        panel.setBackground(new Color(184, 197, 251));
        activarBotonModificar("Operacion exitosa", "Registro duplicado", "el numero FAA no existe", "Modificacion", 1);

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
