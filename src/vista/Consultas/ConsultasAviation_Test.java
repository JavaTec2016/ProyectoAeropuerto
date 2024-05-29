package vista.Consultas;

import modelo.Aviation_Test;
import modelo.Technician_Model_Expertise;
import vista.RasLayout;
import vista.VentanaExterna;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultasAviation_Test extends VentanaExterna {
    public ConsultasAviation_Test() {
        ref = this;
        celh=20;
        celw=20;
        w = 1500;
        h = 800;
        title = "Consultar Pruebas de Aviacion";
        btnAccion = "BUSCAR";

        lbls = Aviation_Test.obtenerLabels();
        cps = Aviation_Test.obtenerComponentes();

        tipos = Aviation_Test.obtenerTipoDato();
        lgs = Aviation_Test.obtenerLongitudes();
        nnl = Aviation_Test.obtenerNoNulos();
        props = new Aviation_Test().propiedades();

        generarConsulta("Aviation_Test", "BUSCAR PRUEBAS DE AVIACION", h/5, 2, 1, 9);
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
