package vista.MenuPrincipal;

import modelo.Registrable;
import vista.PanelRasLayout;
import vista.Panelo;
import vista.VentanaPrincipal;
import vista.Wrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class PanelLateral extends Panelo {
    public JButton btnAirplanes, btnAirplaneTests, btnAviationTests, btnEmployees, btnModels, btnExpertises, btnTrafficController,
        btnActual;
    VentanaPrincipal vn;
    PanelSuperior sp;

    String[] tablas = {
            "Model","Airplane","Airplane_Technician_Test","Aviation_Test", "Employee", "Technician_Model_Expertise", "Traffic_Controller"
    };

    public PanelLateral(VentanaPrincipal v, PanelSuperior superior){
        vn = v;
        x = 0;
        y = 0;
        w = vn.getWidth()/5;
        h = vn.getHeight();
        salida = new ArrayList<Wrap>();
        ras = new PanelRasLayout(this, x, y, w, h);
        sp = superior;
        Color panelColor = new Color(50, 50, 50);
        setBackground(panelColor);

        //inicializar y preparar cada boton

        btnModels = new JButton("Modelos de avion");
        btnAirplanes = new JButton("Aviones");
        btnAirplaneTests = new JButton("Aviones Probados");
        btnAviationTests = new JButton("Pruebas de Aviacion");
        btnEmployees = new JButton("Empleados");
        btnExpertises = new JButton("Experiencia de Tecnicos");
        btnTrafficController = new JButton("Examenes de Controladores");



        Wrap wBtnModels = new Wrap(btnModels);
        Wrap wBtnAirplanes = new Wrap(btnAirplanes);
        Wrap wBtnAirplaneTests = new Wrap(btnAirplaneTests);
        Wrap wBtnAviationTests = new Wrap(btnAviationTests);
        Wrap wBtnEmployees = new Wrap(btnEmployees);
        Wrap wBtnExpertises = new Wrap(btnExpertises);
        Wrap wBtnTrafficController = new Wrap(btnTrafficController);

        salida.add(wBtnModels);
        salida.add(wBtnAirplanes);
        salida.add(wBtnAirplaneTests);
        salida.add(wBtnAviationTests);
        salida.add(wBtnEmployees);
        salida.add(wBtnExpertises);
        salida.add(wBtnTrafficController);

        //CONFIGURACION DE BOTONES

        int yBoton = y+50*salida.size();
        int hBoton = (getHeight()-yBoton)/salida.size();
        int i = 0;
        for(Wrap comp : salida){
            comp.getComponente().setName(tablas[i]);
            comp.getComponente().setBackground(panelColor);
            comp.getComponente().setForeground(new Color(255,255,255));
            //comp.getComponente().setBorder(BorderFactory.createLineBorder(new Color(255,255,255,100)));
            comp.getComponente().setBorder(BorderFactory.createMatteBorder(comp.widthFrom, 0, comp.widthFrom, 0, new Color(200,200,200)));
            comp.getComponente().setFont(new Font("Arial", Font.PLAIN, 15));
            comp.getComponente().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    comp.getComponente().setBackground(new Color(panelColor.getRed()+20, panelColor.getGreen()+20, panelColor.getBlue()+20));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(btnActual != comp.getComponente()) comp.getComponente().setBackground(panelColor);
                }
            });
            JButton accioner = (JButton)comp.getComponente();
            accioner.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(btnActual != null && btnActual != accioner) btnActual.setBackground(panelColor);
                    btnActual = accioner;

                    if(accioner == btnAirplanes){
                        String[] s = {"a","b","c"};
                        vn.prepararTabla(s);
                    }else if(accioner == btnAirplaneTests){
                        String[] s = {"a","b","c","d","e"};
                        vn.prepararTabla(s);
                    }
                    //cambios al panel superior
                    //obtener la clase del modelo relacionado al boton
                    try {
                        String[] tuplas;
                        Field[] campos;
                        Class<?> modelo = Class.forName("modelo."+accioner.getName());
                        //obtener las tuplas del modelo
                        campos = modelo.getDeclaredFields();
                        tuplas = new String[campos.length];
                        int i = 0;
                        for(Field campo : campos){
                            tuplas[i] = campo.getName();
                            i++;
                        }
                        //y preparar la tabla
                        vn.prepararTabla(tuplas);
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Ningun modelo con el nomnbre: " + accioner.getName());
                    }

                }
            });
            //setear cada boton
            ras.agregarRelativo(comp, x, yBoton, w, 50);
            comp.posicionarRelativo(this);
            yBoton += 50;

            i++;
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(salida, ras);
            }
        });
    }
}
