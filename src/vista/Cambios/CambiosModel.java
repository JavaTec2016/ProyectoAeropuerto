package vista.Cambios;

import modelo.Model;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosModel extends VentanaExterna {
    CambiosModel ref;

    public CambiosModel(){
        ref = this;
        celh=20;
        celw=20;
        w = 800;
        h = 400;
        title = "Modificar Modelo de Avion";
        btnAccion = "MODIFICAR";

        lbls = Model.obtenerLabels();
        cps = Model.obtenerComponentes();

        tipos = Model.obtenerTipoDato();
        lgs = Model.obtenerLongitudes();
        nnl = Model.obtenerNoNulos();

        autoGenerar("Model", "MODIFICAR MODELO DE AVION", h/5, 2, 0, 9);
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
