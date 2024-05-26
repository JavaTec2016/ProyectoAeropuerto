package vista.Altas;

import modelo.Model;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class altasAirplane extends Ventana{
    Panelo panelAgregar;
    lblFont lblAgregar;

    JTextField txtRegistrationNumber;
    JTextField txtModelNumber;

    JButton btnLimpiar;
    JButton btnCancelar;

    JButton btnValidar;
    int celw = 20;
    int celh = 20;
    Ventana ref;

    public altasAirplane(){
        ref = this;
        w = 800;
        h = 600;
        title = "Agregar Avion";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);


        lblAgregar = new lblFont("DATOS DEL AVION", "Arial", Font.BOLD, 30, 0,0,0);
        panelAgregar = new Panelo();
        panelAgregar.w = w;
        panelAgregar.h = h/6;
        panelAgregar.x = 0;
        panelAgregar.y = 0;
        panelAgregar.ras = new PanelRasLayout(panelAgregar, 0, 0, w, h/6);
        panelAgregar.salida = new ArrayList<Wrap>();
        panelAgregar.setBackground(new Color(184, 251, 184));

        txtRegistrationNumber = new JTextField(10);
        txtModelNumber = new JTextField(10);

        btnCancelar = new JButton("CANCELAR");
        btnLimpiar = new JButton("LIMPIAR");
        btnValidar = new JButton("AGREGAR");


        Wrap wPanel = new Wrap(panelAgregar);
        Wrap wLblAgregar = new Wrap(lblAgregar);

        int xi = 5, yi = 10;
        ras.agregarRelativo(wPanel, panelAgregar.x, panelAgregar.y, panelAgregar.w, panelAgregar.h);
        Wrap wLblRegistro = ras.encuadrarRelativo(new lblFont("Numero de Modelo", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi, 8, 2);
        Wrap wtxtRegistro = ras.encuadrarRelativo(txtRegistrationNumber, xi+8+2, yi, 12, 2);
        Wrap wLblModelo = ras.encuadrarRelativo(new lblFont("Capacidad", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+3, 8, 2);
        Wrap wtxtModelo = ras.encuadrarRelativo(txtModelNumber, xi+8+2, yi+3, 12, 2);

        Wrap wBtnValidar = ras.encuadrarRelativo(btnValidar, xi-2, yi+14, 12, 2);
        Wrap wBtnCancelar = ras.encuadrarRelativo(btnCancelar, xi+12+2, yi+14, 12,2);
        Wrap wBtnLimpiar = ras.encuadrarRelativo(btnLimpiar, xi+14+9, yi+5, 10,2);

        wBtnValidar.centerOffset(1,1);
        wBtnCancelar.centerOffset(1,1);
        wBtnLimpiar.centerOffset(1,1);

        panelAgregar.ras.agregarRelativo(wLblAgregar, panelAgregar.w/2, panelAgregar.h/2, 400, 30);
        wLblAgregar.centerOffset(1,1);
        wLblAgregar.posicionarRelativo(panelAgregar);


        panelAgregar.salida.add(wLblAgregar);

        salida.add(wPanel);
        salida.add(wLblRegistro);
        salida.add(wtxtRegistro);
        salida.add(wLblModelo);
        salida.add(wtxtModelo);
        salida.add(wBtnValidar);
        salida.add(wBtnCancelar);
        salida.add(wBtnLimpiar);


        
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
