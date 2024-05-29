package vista.Consultas;

import modelo.Aviation_Test;
import modelo.Traffic_Controller;
import vista.RasLayout;
import vista.VentanaExterna;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultasTraffic_Controller extends VentanaExterna {

    public ConsultasTraffic_Controller() {
        ref = this;
        celh=20;
        celw=20;
        w = 1500;
        h = 800;
        title = "Consultar Examenes de controladores";
        btnAccion = "BUSCAR";

        lbls = Traffic_Controller.obtenerLabels();
        cps = Traffic_Controller.obtenerComponentes();

        tipos = Traffic_Controller.obtenerTipoDato();
        lgs = Traffic_Controller.obtenerLongitudes();
        nnl = Traffic_Controller.obtenerNoNulos();
        props = new Traffic_Controller().propiedades();

        generarConsulta("Traffic_Controller", "BUSCAR EXAMENES DE SALUD, CONTROLADORES", h/5, 2, 1, 9);
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
