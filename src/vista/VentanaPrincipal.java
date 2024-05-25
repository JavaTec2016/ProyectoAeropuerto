package vista;

import vista.login.Ventana;
import vista.login.VentanaLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    RasLayout ras;
    ArrayList<Wrap> partes;
    VentanaLogin vl;
    JPanel panelFondo;
    public VentanaPrincipal(){
        partes = new ArrayList<Wrap>();
        ras = new RasLayout(this, "Aplicacion", 1000, 800);
        vl = new VentanaLogin();
        panelFondo = new JPanel();

        vl.setResizable(false);
        vl.setMaximizable(false);
        vl.setIconifiable(false);
        vl.setClosable(false);

        panelFondo.setBackground(new Color(100, 100, 100));
        panelFondo.setForeground(new Color(255,255,255));



        Wrap wvl = new Wrap(vl);
        wvl.resize = false;
        ras.agregarRelativo(wvl, getWidth()/2, getHeight()/2, vl.w, vl.h);
        wvl.centerOffset(1,1);

        Wrap wFondo = new Wrap(panelFondo);
        ras.agregarRelativo(wFondo, 0, 0, getWidth(), getHeight());


        partes.add(wvl);
        partes.add(wFondo);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(partes, ras);
            }
        });

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}
