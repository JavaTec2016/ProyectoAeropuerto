package vista.login;

import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class VentanaLogin extends Ventana {
    JPanel panelLogin, panelCampos;
    lblFont lblIniciarSesion;
    //public mientras se prueba
    public JTextField txtUsuario;
    public JPasswordField txtPass;
    public JButton btnValidar;
    public VentanaLogin(){
        w = 800;
        h = 500;
        tipo = "JInternalFrame";
        salida = new ArrayList<Wrap>();
        ras = new InternalRasLayout(this, "Iniciar Sesion", w, h);

        panelLogin = new JPanel();
        panelLogin.setBackground(new Color(0, 149, 84));
        panelCampos = new JPanel();
        panelCampos.setBackground(new Color(241, 241, 241));
        lblIniciarSesion = new lblFont("Inicie Sesión", "Arial", Font.BOLD, 40, 0,0,0);

        txtPass = new JPasswordField(10);
        txtUsuario = new JTextField(10);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
        txtPass.setFont(new Font("Arial", Font.PLAIN, 20));

        btnValidar = new JButton("INGRESAR");
        btnValidar.setFont(new Font("Arial", Font.BOLD, 15));
        btnValidar.setBackground(new Color(0, 85, 62));
        btnValidar.setForeground(new Color(255,255,255));

        Wrap wPanelLogin = new Wrap(panelLogin);
        Wrap wPanelCampos = new Wrap(panelCampos);
        Wrap wLblSesion = new Wrap(lblIniciarSesion).chainCenterOffset(0, 1);
        Wrap wLblUsiario = new Wrap(new lblFont("Ingrese su Usuario: ", "Arial", Font.ITALIC, 25, 20,40,20));
        Wrap wTxtUsuario = new Wrap(txtUsuario);
        Wrap wLblPass = new Wrap(new lblFont("Ingrese su Contraseña: ", "Arial", Font.ITALIC, 25, 20,40,20));
        Wrap wTxtPass = new Wrap(txtPass);
        Wrap wBtnValidar = new Wrap(btnValidar);

        ras.prepararRelativo(wPanelLogin, 0, 0, 800, 600);
        ras.prepararRelativo(wPanelCampos, 200, 0, 600, 600);
        ras.agregarRelativo(wLblSesion, 250, 100, 300, 45);

        ras.agregarRelativo(wLblUsiario, wLblSesion.xFrom, wLblSesion.yFrom+80, 280, 30);
        ras.agregarRelativo(wTxtUsuario, wLblUsiario.xFrom, wLblUsiario.yFrom+40, 400, 30);

        ras.agregarRelativo(wLblPass, wTxtUsuario.xFrom, wTxtUsuario.yFrom+60, 280, 30);
        ras.agregarRelativo(wTxtPass, wLblPass.xFrom, wLblPass.yFrom+40, 400, 30);

        ras.agregarRelativo(wBtnValidar, wTxtPass.xFrom+wTxtPass.widthFrom/2, wTxtPass.yFrom + 40, 300, 50);
        wBtnValidar.centerOffset(1, 0);
        wBtnValidar.posicionarRelativo(this);
        //wBtnValidar.xOffset = -wBtnValidar.getComponente().getWidth()/2;
        add(panelCampos);
        add(panelLogin);

        salida.add(wLblSesion);

        salida.add(wLblUsiario);
        salida.add(wTxtUsuario);

        salida.add(wLblPass);
        salida.add(wTxtPass);

        salida.add(wBtnValidar);

        salida.add(wPanelCampos);
        salida.add(wPanelLogin);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                InternalRasLayout.refrescar(salida, ras);
            }
        });

    }
    public byte verificarCredenciales(){
        if(txtUsuario.getText().isBlank() || txtPass.getPassword().length == 0) return 11;
        //else if credenciales incorrectas return 12
        return 10;
    }
}
/*
class ventanaLogin extends Ventana{
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
*/