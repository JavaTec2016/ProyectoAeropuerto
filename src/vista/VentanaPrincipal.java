package vista;

import modelo.Registrable;
import vista.Altas.*;
import vista.Bajas.*;
import vista.Cambios.*;
import vista.MenuPrincipal.*;
import vista.login.VentanaLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static controlador.DAO.d;

public class VentanaPrincipal extends JFrame {
    RasLayout ras;
    ArrayList<Wrap> partes;
    VentanaLogin vl;
    JPanel panelFondo;
    PanelLateral barraTablas;
    PanelSuperior barraOpciones;
    PanelTabla panelTabla;

    JDesktopPane desk;

    VentanaExterna ventanaAltas;
    VentanaExterna ventanaBajas;
    VentanaExterna ventanaCambios;
    VentanaExterna ventanaConsultas;


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
        wDesk.resize = false;
        ras.agregarRelativo(wDesk, desk.getX(), desk.getY(), desk.getWidth(), desk.getHeight());
        partes.add(wDesk);


        vl.setResizable(false);
        vl.setMaximizable(false);
        vl.setIconifiable(false);
        vl.setClosable(false);

        //INICIALIZACION DE PANTALLAS ABCC



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

        //acciones ABCC
        barraOpciones.btnAltas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cambiar la ventana y mover el desk
                if(barraTablas.btnActual == null){
                    JOptionPane.showMessageDialog(ref, "Seleccione una opcion del panel lateral", "Agregar registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(barraTablas.btnActual == barraTablas.btnModels){
                    ventanaAltas = new altasModel();
                }else if(barraTablas.btnActual == barraTablas.btnAirplanes){
                    ventanaAltas = new altasAirplane();
                }else if(barraTablas.btnActual == barraTablas.btnEmployees){
                    ventanaAltas = new altasEmployee();
                }else if(barraTablas.btnActual == barraTablas.btnExpertises){
                    ventanaAltas = new altasExpertises();
                }else if(barraTablas.btnActual == barraTablas.btnAirplaneTests){
                    ventanaAltas = new AltasAirplane_Technician_Test();
                }else if(barraTablas.btnActual == barraTablas.btnAviationTests){
                    ventanaAltas = new AltasAviation_Test();
                }else if(barraTablas.btnActual == barraTablas.btnTrafficController){
                    ventanaAltas = new AltasTraffic_Controller();
                }
            }
        });
        barraOpciones.btnBajas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barraTablas.btnActual == null){
                    JOptionPane.showMessageDialog(ref, "Seleccione una opcion del panel lateral", "Eliminar registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(ventanaBajas != null) ventanaBajas.dispose();

                if(barraTablas.btnActual == barraTablas.btnModels){
                    ventanaBajas = new bajasModel();
                }else if(barraTablas.btnActual == barraTablas.btnAirplanes){
                    ventanaBajas = new bajasAirplane();
                }else if(barraTablas.btnActual == barraTablas.btnEmployees){
                    ventanaBajas = new bajasEmployee();
                }else if(barraTablas.btnActual == barraTablas.btnExpertises){
                    ventanaBajas = new bajasExpertise();
                }else if(barraTablas.btnActual == barraTablas.btnAirplaneTests){
                    ventanaBajas = new bajasAirplane_Technician_Test();
                }else if(barraTablas.btnActual == barraTablas.btnAviationTests){
                    ventanaBajas = new bajasAviation_Test();
                }else if(barraTablas.btnActual == barraTablas.btnTrafficController){
                    ventanaBajas = new bajasTraffic_Controller();
                }
            }
        });
        barraOpciones.btnCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barraTablas.btnActual == null){
                    JOptionPane.showMessageDialog(ref, "Seleccione una opcion del panel lateral", "Modificar registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(ventanaCambios != null) ventanaCambios.dispose();

                if(barraTablas.btnActual == barraTablas.btnModels){
                    ventanaCambios = new CambiosModel();
                }else if(barraTablas.btnActual == barraTablas.btnAirplanes){
                    ventanaCambios = new CambiosAirplane();
                }else if(barraTablas.btnActual == barraTablas.btnEmployees){
                    ventanaCambios = new CambiosEmployee();
                }else if(barraTablas.btnActual == barraTablas.btnExpertises){
                    ventanaCambios = new CambiosExpertises();
                }else if(barraTablas.btnActual == barraTablas.btnAirplaneTests){
                    ventanaCambios = new CambiosAirplane_Technician_Test();
                }else if(barraTablas.btnActual == barraTablas.btnAviationTests){
                    ventanaCambios = new CambiosAviation_Test();
                }else if(barraTablas.btnActual == barraTablas.btnTrafficController){
                    ventanaCambios = new CambiosTraffic_Controller();
                }
            }
        });
    }
    public void prepararTabla(String[] tuplas){
        panelTabla.prepararTabla(tuplas);
    }
    public static byte n(){
        return (byte)(Math.random()*10);
    }
    public static void main(String[] args) {
        /**
         *for(int i = 0; i < 20; i++){
         *             String n = "";
         *             while (n.length() < 20) n += n();
         *             Employee e = new Employee(
         *                     n, "Jua"+i, "Nin"+i, "st"+i,  "city"+i, ""+(int)(Math.random()*Math.pow(10,5)), ""+(int)(Math.random()*Math.pow(10,10)),
         *                     (int)(Math.random()*Math.pow(10,10)), (int)(Math.random()*Math.pow(10,6)), "Technician");
         *
         *             d.agregarUniversal(e);
         *         }
         */

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
    public void setRegistros(){
        if(barraTablas.btnActual == null){
            JOptionPane.showMessageDialog(this, "Seleccione una tabla a consultar", "Consulta indefinida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<Registrable> rs = d.consultarUniversal(barraTablas.obtenerTabla());
        System.out.println("Se consulto la tabla " + barraTablas.obtenerTabla() + " con " + rs.size() + " registros.");
        panelTabla.agregarRegistros(rs);
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
