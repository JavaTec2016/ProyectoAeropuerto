package vista.MenuPrincipal;

import vista.PanelRasLayout;
import vista.Panelo;
import vista.Wrap;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class PanelSuperior extends Panelo {
    JFrame vn;
    lblFont lblBuscar;
    JTextField txtBuscar;
    JButton btnBuscar;

    //los 4 fantasticos

    JButton btnAltas;
    JButton btnBajas;
    JButton btnCambios;
    JButton btnConsultas;



    public PanelSuperior(JFrame v, Panelo lateral){
        vn = v;
        x = lateral.w;
        y = 0;
        w = vn.getWidth()-x;
        h = vn.getHeight()/6;
        //System.out.println(""+x+" "+y+" "+w+" "+h);
        salida = new ArrayList<Wrap>();
        ras = new PanelRasLayout(this, x, y, w, h);
        Color panelColor = new Color(255,255,255);
        setBackground(panelColor);

        lblBuscar = new lblFont("Ingrese un ID", "Arial", Font.BOLD, 15, 0,0,0);
        txtBuscar = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(0, 85, 62));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));

        btnAltas = new JButton("Agregar..");
        btnBajas = new JButton("Eliminar..");
        btnAltas = new JButton("Modificar..");
        btnAltas = new JButton("Consultar..");

        Wrap wLblBuscar = new Wrap(lblBuscar);
        Wrap wTxtBuscar = new Wrap(txtBuscar);
        Wrap wBtnBuscar = new Wrap(btnBuscar);

        Wrap wbtnAltas = new Wrap(btnAltas);
        Wrap wbtnBajas = new Wrap(btnBajas);
        Wrap wbtnCambios = new Wrap(btnCambios);
        Wrap wbtnConsultas = new Wrap(btnConsultas);

        ras.agregarRelativo(wLblBuscar, 20, 35, 100, 20);
        wLblBuscar.centerOffset(0,1);
        wLblBuscar.posicionarRelativo(this);
        ras.agregarRelativo(wTxtBuscar, wLblBuscar.xFrom+wLblBuscar.widthFrom+20, 20, 160, 30);
        wLblBuscar.centerOffset(0,1);
        ras.agregarRelativo(wBtnBuscar, wTxtBuscar.xFrom+wTxtBuscar.widthFrom+20, 20, 120, 30);
        wLblBuscar.centerOffset(0,1);


        salida.add(wLblBuscar);
        salida.add(wTxtBuscar);
        salida.add(wBtnBuscar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(salida, ras);
            }
        });
    }
}
