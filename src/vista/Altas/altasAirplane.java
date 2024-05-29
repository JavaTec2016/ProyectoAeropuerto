package vista.Altas;

import modelo.*;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.sql.Wrapper;
import java.util.ArrayList;

public class altasAirplane extends VentanaExterna {
    private String objetivo = "Airplane";
    public altasAirplane(){
        ref = this;
        celh=20;
        celw=20;
        w = 800;
        h = 400;
        title = "Agregar Avion";
        btnAccion = "AGREGAR";

        lbls = Airplane.obtenerLabels();
        cps = Airplane.obtenerComponentes();

        tipos = Airplane.obtenerTipoDato();
        lgs = Airplane.obtenerLongitudes();
        nnl = Airplane.obtenerNoNulos();

        autoGenerar("Airplane", "AGREGAR AVION", h/6, 2, 1, 9);
        panel.setBackground(new Color(184, 251, 184));

        activarBotonValidar("Operacion exitosa", "Registro duplicado", "Numero de modelo es incorrecto", "Registro agregado");


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

    }
}
