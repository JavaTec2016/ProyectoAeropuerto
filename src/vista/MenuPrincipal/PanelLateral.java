package vista.MenuPrincipal;

import vista.PanelRasLayout;
import vista.Panelo;
import vista.Wrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PanelLateral extends Panelo {
    JButton btnAirplanes, btnAirplaneTests, btnAviationTests, btnEmployees, btnModels, btnExpertises, btnTrafficController,
        btnActual;
    JFrame vn;
    public PanelLateral(JFrame v){
        vn = v;
        x = 0;
        y = 0;
        w = vn.getWidth()/5;
        h = vn.getHeight();
        salida = new ArrayList<Wrap>();
        ras = new PanelRasLayout(this, x, y, w, h);
        Color panelColor = new Color(50, 50, 50);
        setBackground(panelColor);

        //inicializar y preparar cada boton

        btnModels = new JButton("Modelos de avion");
        btnAirplanes = new JButton("Aviones");
        btnAirplaneTests = new JButton("Aviones Probados");
        btnAviationTests = new JButton("Pruebas de Aviacion");
        btnEmployees = new JButton("Empleados");
        btnExpertises = new JButton("Experiencia de Tecnicos");
        btnTrafficController = new JButton("Controladores de Trafico");

        btnModels.setBackground(new Color(0,0,0,0));
        btnModels.setForeground(new Color(255,255,255));
        btnModels.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,100)));

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

        for(Wrap comp : salida){
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
                    comp.getComponente().setBackground(panelColor);
                }
            });
            JButton accioner = (JButton)comp.getComponente();
            accioner.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnActual = accioner;
                }
            });
            //setear cada boton
            ras.agregarRelativo(comp, x, yBoton, w, 50);
            comp.posicionarRelativo(this);
            yBoton += 50;
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(salida, ras);
            }
        });
    }
}
