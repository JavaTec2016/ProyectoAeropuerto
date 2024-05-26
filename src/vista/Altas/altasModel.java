package vista.Altas;

import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class altasModel extends Ventana {
    Panelo panelAgregar;
    lblFont lblAgregar;
    public altasModel(){
        w = 800;
        h = 600;
        title = "Agregar Modelo";
        ras = new InternalRasLayout(this, title, w, h);
        lblAgregar = new lblFont("DATOS DEL MODELO", "Arial", Font.BOLD, 30, 0,0,0);
        panelAgregar = new Panelo();
        panelAgregar.w = w;
        panelAgregar.h = h/6;
        panelAgregar.x = 0;
        panelAgregar.y = 0;
        panelAgregar.ras = new PanelRasLayout(panelAgregar, 0, 0, w, h/6);
        panelAgregar.setBackground(new Color(94, 189, 94));

        Wrap wPanel = new Wrap(panelAgregar);
        Wrap wLblAgregar = new Wrap(lblAgregar);

        ras.agregarRelativo(wPanel, panelAgregar.x, panelAgregar.y, panelAgregar.w, panelAgregar.h);
        panelAgregar.ras.agregarRelativo(wLblAgregar, 30, 30, panelAgregar.w/2, panelAgregar.h/2);
        wLblAgregar.centerOffset(1,1);

        panelAgregar.salida.add(wLblAgregar);
        salida.add(wPanel);

        panelAgregar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(panelAgregar.salida, panelAgregar.ras);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                InternalRasLayout.refrescar(salida, ras);
            }
        });

    }
}
