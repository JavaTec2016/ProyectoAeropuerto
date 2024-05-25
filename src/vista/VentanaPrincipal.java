package vista;

import vista.MenuPrincipal.PanelLateral;
import vista.MenuPrincipal.PanelSuperior;
import vista.MenuPrincipal.PanelTabla;
import vista.login.VentanaLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    RasLayout ras;
    ArrayList<Wrap> partes;
    VentanaLogin vl;
    JPanel panelFondo;
    PanelLateral barraTablas;
    PanelSuperior barraOpciones;
    PanelTabla panelTabla;

    public VentanaPrincipal(){
        partes = new ArrayList<Wrap>();
        ras = new RasLayout(this, "Aplicacion", 1200, 800);
        vl = new VentanaLogin();
        panelFondo = new JPanel();

        barraTablas = new PanelLateral(this, barraOpciones);
        barraOpciones = new PanelSuperior(this, barraTablas);
        panelTabla = new PanelTabla(this, barraTablas.w, barraOpciones.h, getWidth()-barraTablas.w, getHeight()-barraOpciones.h);


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

        partes.add(wvl);
        partes.add(wFondo);
        partes.add(wPanelTabla);
        partes.add(wBarraOpciones);
        partes.add(wBarraTablas);

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

    public boolean identificarError(int err){

        switch (err){
            case 11:
                JOptionPane.showMessageDialog(this, "Usuario o contraseña vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                return true;
            default: return false;
        }
    }
}
