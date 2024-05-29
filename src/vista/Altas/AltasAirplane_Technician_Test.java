package vista.Altas;

import modelo.Airplane_Technician_Test;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.VentanaExterna;
import vista.Wrap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltasAirplane_Technician_Test extends VentanaExterna {

    AltasAirplane_Technician_Test ref;
    public AltasAirplane_Technician_Test() {
        ref = this;
        w = 900;
        h = 600;
        celh = 20;
        celw = 20;
        title = "Agregar Prueba";
        btnAccion = "AGREGAR";

        lbls = Airplane_Technician_Test.obtenerLabels();
        cps = Airplane_Technician_Test.obtenerComponentes();

        tipos = Airplane_Technician_Test.obtenerTipoDato();
        lgs = Airplane_Technician_Test.obtenerLongitudes();
        nnl = Airplane_Technician_Test.obtenerNoNulos();

        autoGenerar("Airplane_Technician_Test", "AGREGAR PRUEBA", h/10, 1, 1, 10);
        activarBotonValidar("Operacion exitosa", "Registro duplicado", "los datos de experiencia o registro de avion son incorrectos", "Registro agregado");

    }
}
