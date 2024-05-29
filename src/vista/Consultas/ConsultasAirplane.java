package vista.Consultas;

import modelo.Airplane;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultasAirplane extends VentanaExterna {
    private String objetivo = "Airplane";
    public ConsultasAirplane(){
        ref = this;
        celh=20;
        celw=20;
        w = 1500;
        h = 800;
        title = "Consultar Aviones";
        btnAccion = "BUSCAR";

        lbls = Airplane.obtenerLabels();
        cps = Airplane.obtenerComponentes();

        tipos = Airplane.obtenerTipoDato();
        lgs = Airplane.obtenerLongitudes();
        nnl = Airplane.obtenerNoNulos();
        props = new Airplane().propiedades();

        generarConsulta("Airplane", "BUSCAR AVIONES", h/5, 2, 1, 9);
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
