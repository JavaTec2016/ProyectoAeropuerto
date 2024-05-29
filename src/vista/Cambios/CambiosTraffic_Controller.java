package vista.Cambios;
import modelo.Traffic_Controller;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class CambiosTraffic_Controller extends VentanaExterna {
    CambiosTraffic_Controller ref;
    public CambiosTraffic_Controller(){
        ref = this;
        w = 900;
        h = 350;
        celh = 20;
        celw = 20;
        title = "Modificar Examen de Controlador de trafico";
        btnAccion = "MODIFICAR";

        lbls = Traffic_Controller.obtenerLabels();
        cps = Traffic_Controller.obtenerComponentes();

        tipos = Traffic_Controller.obtenerTipoDato();
        lgs = Traffic_Controller.obtenerLongitudes();
        nnl = Traffic_Controller.obtenerNoNulos();

        autoGenerar("Traffic_Controller", "MODIFICAR EXAMEN DE SALUD", h/5, 2, 1, 11);
        panel.setBackground(new Color(184, 197, 251));
        activarBotonModificar("Operacion exitosa", "Registro duplicado", "el SSN no existe", "Modificacion", 1);

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
