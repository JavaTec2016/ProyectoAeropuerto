package vista.Consultas;

import modelo.Employee;
import modelo.Technician_Model_Expertise;
import vista.RasLayout;
import vista.VentanaExterna;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultasExpertises extends VentanaExterna {
    public ConsultasExpertises() {
        ref = this;
        celh=20;
        celw=20;
        w = 1500;
        h = 800;
        title = "Consultar Experiencias";
        btnAccion = "BUSCAR";

        lbls = Technician_Model_Expertise.obtenerLabels();
        cps = Technician_Model_Expertise.obtenerComponentes();

        tipos = Technician_Model_Expertise.obtenerTipoDato();
        lgs = Technician_Model_Expertise.obtenerLongitudes();
        nnl = Technician_Model_Expertise.obtenerNoNulos();
        props = new Technician_Model_Expertise().propiedades();

        generarConsulta("Technician_Model_Expertise", "BUSCAR EXPERIENCIAS", h/5, 2, 1, 9);
        panel.setBackground(new Color(168, 168, 168));
        //activarBotonModificar("Operacion exitosa", "Registro duplicado", "Numero de modelo no existe", "Modificacion", 1);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });
    }
}
