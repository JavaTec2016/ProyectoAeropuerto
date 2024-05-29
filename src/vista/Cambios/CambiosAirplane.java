package vista.Cambios;

import modelo.Airplane;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosAirplane extends VentanaExterna {
    private String objetivo = "Airplane";
    public CambiosAirplane(){
        ref = this;
        celh=20;
        celw=20;
        w = 800;
        h = 400;
        title = "Modificar Avion";
        btnAccion = "MODIFICAR";

        lbls = Airplane.obtenerLabels();
        cps = Airplane.obtenerComponentes();

        tipos = Airplane.obtenerTipoDato();
        lgs = Airplane.obtenerLongitudes();
        nnl = Airplane.obtenerNoNulos();

        autoGenerar("Airplane", "MODIFICAR AVION", h/5, 2, 1, 9);
        panel.setBackground(new Color(184, 197, 251));
        activarBotonModificar("Operacion exitosa", "Registro duplicado", "Numero de modelo no existe", "Modificacion", 1);

        lblFont lblVacios = new lblFont("los espacios en blanco no sufriran cambios", "Arial", Font.ITALIC, 13, 0,0,0);
        panel.salida.add(panel.ras.encuadrarRelativo(lblVacios, 0, (int)(panel.h/3), panel.w, panel.h));
        salida.get(salida.size()-1).centerOffset(1,1);
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
