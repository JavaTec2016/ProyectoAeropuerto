package vista;

import vista.Altas.altasModel;
import vista.MenuPrincipal.PanelLateral;
import vista.MenuPrincipal.PanelSuperior;
import vista.MenuPrincipal.PanelTabla;
import vista.login.VentanaLogin;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    RasLayout ras;
    ArrayList<Wrap> partes;
    VentanaLogin vl;
    JPanel panelFondo;
    PanelLateral barraTablas;
    PanelSuperior barraOpciones;
    PanelTabla panelTabla;

    JDesktopPane desk;
    Ventana ventanaAltas;
    Ventana ventanaBajas;
    Ventana ventanaCambios;
    Ventana ventanaConsultas;
    Ventana ventanaActual;

    Wrap wAltas;
    Wrap wBajas;
    Wrap wCambios;
    Wrap wConsultas;
    Wrap wActual;
    VentanaPrincipal ref;
    public VentanaPrincipal(){
        ref = this;
        partes = new ArrayList<Wrap>();
        ras = new RasLayout(this, "Aplicacion", 1200, 800);
        vl = new VentanaLogin();
        panelFondo = new JPanel();

        barraTablas = new PanelLateral(this, barraOpciones);
        barraOpciones = new PanelSuperior(this, barraTablas);
        panelTabla = new PanelTabla(this, barraTablas.w, barraOpciones.h, getWidth()-barraTablas.w, getHeight()-barraOpciones.h);

        //un desk que se utilizara para forzar pantallas ABCC a aparecer en frente
        desk = new JDesktopPane();
        //desk.setBackground(new Color(0, 250, 0, 0));

        //desk.setBounds(0, 0, getWidth(), getHeight());
        //desk.setVisible(false);
        add(desk);

        Wrap wDesk = new Wrap(desk);
        ras.agregarRelativo(wDesk, desk.getX(), desk.getY(), desk.getWidth(), desk.getHeight());
        partes.add(wDesk);

        ventanaAltas = new altasModel();
        ventanaBajas = new Ventana();
        ventanaCambios = new Ventana();
        ventanaConsultas = new Ventana();
        ventanaActual = new Ventana();

        ventanaAltas.setVisible(true);
        ventanaBajas.setVisible(false);
        ventanaCambios.setVisible(false);
        ventanaConsultas.setVisible(false);
        ventanaActual.setVisible(false);

        ventanaAltas.setEnabled(true);
        ventanaBajas.setEnabled(false);
        ventanaCambios.setEnabled(false);
        ventanaConsultas.setEnabled(false);
        ventanaActual.setEnabled(false);

        vl.setResizable(false);
        vl.setMaximizable(false);
        vl.setIconifiable(false);
        vl.setClosable(false);

        //INICIALIZACION DE PANTALLAS ABCC

        ventanaAltas = new VentanaLogin();
        Wrap wk = new Wrap(ventanaAltas);
        wk.resize = false;
        ras.agregarRelativo(wk, 0,0,600,600);
        remove(ventanaAltas);
        desk.add(ventanaAltas);

        panelFondo.setBackground(new Color(100, 100, 100));
        panelFondo.setForeground(new Color(255,255,255));

        Wrap wvl = new Wrap(vl);
        wvl.resize = false;
        ras.agregarRelativo(wvl, getWidth()/2, getHeight()/2, vl.w, vl.h);
        wvl.centerOffset(1,1);

        Wrap wFondo = new Wrap(panelFondo);
        ras.agregarRelativo(wFondo, 0, 0, getWidth(), getHeight());

        Wrap wBarraTablas = new Wrap(barraTablas);
        barraTablas.setEnabled(false);
        barraTablas.setVisible(false);
        ras.agregarRelativo(wBarraTablas, barraTablas.x, barraTablas.y, barraTablas.w, barraTablas.h);

        Wrap wBarraOpciones = new Wrap(barraOpciones);
        barraOpciones.setEnabled(false);
        barraOpciones.setVisible(false);
        ras.agregarRelativo(wBarraOpciones, barraOpciones.x, barraOpciones.y, barraOpciones.w, barraOpciones.h);

        Wrap wPanelTabla = new Wrap(panelTabla);
        panelTabla.setVisible(false);
        panelTabla.setEnabled(false);
        ras.agregarRelativo(wPanelTabla, panelTabla.x, panelTabla.y, panelTabla.w, panelTabla.h);

        //ventanaAltas = new VentanaLogin();
        wAltas = new Wrap(ventanaAltas);
        wBajas = new Wrap(ventanaBajas);
        wCambios = new Wrap(ventanaCambios);
        wConsultas = new Wrap(ventanaConsultas);

        //System.out.println(ventanaAltas.w);
       // ras.agregarRelativo(wAltas, 0, 0, 700, 700);

        //System.out.println(wAltas.widthFrom);
        //wAltas.centerOffset(1,1);

        partes.add(wk);
        partes.add(wvl);
        partes.add(wFondo);
        partes.add(wPanelTabla);
        partes.add(wBarraOpciones);
        partes.add(wBarraTablas);

        //desk.add(ventanaAltas);
        partes.add(wAltas);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(partes, ras);
            }
        });
        vl.btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ocultar la pantalla login
                if(identificarError(vl.verificarCredenciales())) return;
                vl.setVisible(false);
                vl.setEnabled(false);
                panelFondo.setVisible(false);

                barraTablas.setEnabled(true);
                barraTablas.setVisible(true);

                barraOpciones.setEnabled(true);
                barraOpciones.setVisible(true);

                panelTabla.setEnabled(true);
                panelTabla.setVisible(true);

                setTitle("Panel de control - Aeropuerto");
            }
        });

        //acciones ABCC
        barraOpciones.btnAltas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cambiar la ventana y mover el desk
                desk.setVisible(true);
                desk.remove(ventanaAltas);
                ventanaAltas = new altasModel();
                ras.prepararRelativo(wAltas, 0, 0, ventanaAltas.w, ventanaAltas.h);
                wAltas.centerOffset(0,0);
                ras.actualizarRelativo(wAltas);

                desk.setBounds(ventanaAltas.getBounds());
                ras.prepararRelativo(wDesk, getWidth()/2, getHeight()/2, desk.getWidth(), desk.getHeight());
                wDesk.centerOffset(1,1);

                wAltas.posicionarRelativo(ref);
                wDesk.posicionarRelativo(ref);

                desk.add(ventanaAltas);
                ventanaAltas.addInternalFrameListener(new InternalFrameAdapter() {
                    @Override
                    public void internalFrameClosing(InternalFrameEvent e) {
                        desk.setVisible(false);
                    }
                });
                ventanaAltas.setEnabled(true);
                ventanaAltas.setVisible(true);
            }
        });
    }
    public void prepararTabla(String[] tuplas){
        panelTabla.prepararTabla(tuplas);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
    public void copiarInternal(Ventana in, Ventana n){
        Component[] cs = n.getComponents();
        in.removeAll();
        in.revalidate();
        in.repaint();
        in.w = n.w;
        in.h = n.h;
        in.ras = n.ras;
        in.salida = n.salida;

        in.setBounds(n.getX(), n.getY(), n.getWidth(), n.getHeight());
        for (Component c : cs) {
            in.add(c);
        }
        for(ComponentListener ca : in.getComponentListeners()){
            in.removeComponentListener(ca);
        }
        for(ComponentListener ca : n.getComponentListeners()){
            in.addComponentListener(ca);
        }

    }
    public boolean identificarError(int err){

        switch (err){
            case 11:
                JOptionPane.showMessageDialog(this, "Usuario o contraseña vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            default: return false;
        }
    }
}
