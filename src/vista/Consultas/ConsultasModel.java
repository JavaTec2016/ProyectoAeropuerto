package vista.Consultas;

import modelo.Airplane;
import modelo.Model;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultasModel extends VentanaExterna {
    ConsultasModel ref;

    public ConsultasModel(){
        ref = this;
        celh=20;
        celw=20;
        w = 1500;
        h = 800;
        title = "Consultar Modelos de Avion";
        btnAccion = "BUSCAR";

        lbls = Model.obtenerLabels();
        cps = Model.obtenerComponentes();

        tipos = Model.obtenerTipoDato();
        lgs = Model.obtenerLongitudes();
        nnl = Model.obtenerNoNulos();
        props = new Model().propiedades();

        generarConsulta("Model", "BUSCAR MODELOS DE AVION", h/5, 2, 1, 9);
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
