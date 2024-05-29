package vista.Cambios;

import modelo.Technician_Model_Expertise;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosExpertises extends VentanaExterna {
    CambiosExpertises ref;

    public CambiosExpertises() {
        ref = this;
        w = 900;
        h = 400;
        celh = 20;
        celw = 20;
        title = "Modificar Experiencia de Tecnico";
        btnAccion = "MODIFICAR";

        lbls = Technician_Model_Expertise.obtenerLabels();
        cps = Technician_Model_Expertise.obtenerComponentes();

        tipos = Technician_Model_Expertise.obtenerTipoDato();
        lgs = Technician_Model_Expertise.obtenerLongitudes();
        nnl = Technician_Model_Expertise.obtenerNoNulos();

        autoGenerar("Technician_Model_Expertise", "MODIFICAR EXPERIENCIA DE TECNICO", h / 5, 2, 1, 10);
        panel.setBackground(new Color(184, 197, 251));
        activarBotonModificar("Operacion exitosa", "Registro duplicado", "el SSN no existe", "Modificacion", 0);

        lblFont lblVacios = new lblFont("los espacios en blanco no sufriran cambios", "Arial", Font.ITALIC, 13, 0, 0, 0);
        panel.salida.add(panel.ras.encuadrarRelativo(lblVacios, 0, (int) (panel.h / 3), panel.w, panel.h));
        salida.get(salida.size() - 1).centerOffset(1, 1);
        lblVacios.setVerticalAlignment(SwingConstants.CENTER);
        lblVacios.setHorizontalAlignment(SwingConstants.CENTER);

    }
}
