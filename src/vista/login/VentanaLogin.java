package vista.login;

import vista.RasLayout;
import vista.Wrap;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaLogin extends Ventana{
    JPanel panelLogin, panelCampos;
    lblFont lblIniciarSesion;

    public VentanaLogin(){
        salida = new ArrayList<Wrap>();
        w = 800;
        h = 600;
        ras = new RasLayout(new JFrame(), "Iniciar Sesion", w, h);
        ras.setVisible(false);
        panelLogin = new JPanel();
        panelLogin.setBackground(new Color(0, 149, 84));
        panelCampos = new JPanel();
        panelCampos.setBackground(new Color(241, 241, 241));
        lblIniciarSesion = new lblFont("Inicie sesion", "Arial", Font.BOLD, 20, 0,0,0);

        Wrap wPanelLogin = new Wrap(panelLogin);
        Wrap wPanelCampos = new Wrap(panelCampos);
        Wrap wLblSesion = new Wrap(lblIniciarSesion).chainCenterOffset(0, 1);

        ras.prepararRelativo(wPanelLogin, 0, 0, 800, 600);
        ras.prepararRelativo(wPanelCampos, 200, 0, 600, 600);
        ras.prepararRelativo(wLblSesion, 300, 100, 200, 25);

        salida.add(wLblSesion);

        salida.add(wPanelCampos);
        salida.add(wPanelLogin);


        //removeAll();
        //revalidate();
        //repaint();
    }
}
