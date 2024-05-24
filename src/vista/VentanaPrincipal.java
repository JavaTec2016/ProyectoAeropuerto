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

    VentanaLogin login;
    JInternalFrame frameLogin = new JInternalFrame();

    public VentanaPrincipal(){
        login = new VentanaLogin();
        ras = new RasLayout(this, "Aplicacion", 1000, 800);

        partes = new ArrayList<Wrap>();

        login = new VentanaLogin();
        setForeground(new Color(43, 43, 43));
        Wrap wLogin = new Wrap(frameLogin);

        //swapInternal(frameLogin, login);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(partes, ras);
            }
        });


    }
    //construye la ventana especificada
    public void swap(Ventana v){
        removeAll();
        revalidate();
        repaint();
        partes = v.salida;
        for(Wrap parte : partes){
            add(parte.componente);
        }
    }
    public void swapInternal(JInternalFrame ji, Ventana v){
        ji.removeAll();
        ji.revalidate();
        ji.repaint();

        partes = v.salida;
        for(Wrap parte : partes){
            ji.add(parte.componente);
        }

        frameLogin.getContentPane().setLayout(null);
        frameLogin.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        frameLogin.setTitle(v.title);
        frameLogin.setSize(v.w, v.h);
        frameLogin.setVisible(true);
        frameLogin.setClosable(true);
        frameLogin.setMaximizable(true);
        frameLogin.setIconifiable(true);
        frameLogin.setResizable(true);

        frameLogin.addComponentListener(new ComponentAdapter() {
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
