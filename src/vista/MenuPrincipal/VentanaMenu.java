package vista.MenuPrincipal;

import vista.InternalRasLayout;
import vista.PanelRasLayout;
import vista.Ventana;

import javax.swing.*;

public class VentanaMenu extends Ventana {
    PanelRasLayout rasTablas;
    JButton btnAirplanes, btnAirplaneTests, btnAviationTests, btnEmployees, btnModels, btnExpertises, btnTrafficController;
    JPanel panelLateral;
    public VentanaMenu(){
        w = 1500;
        h = 900;
        ras = new InternalRasLayout(this, "Panel de control - Aeropuerto", w, h);
        panelLateral = new JPanel();


    }
}
