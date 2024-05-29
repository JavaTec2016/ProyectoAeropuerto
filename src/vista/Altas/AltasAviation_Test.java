package vista.Altas;

import modelo.Airplane_Technician_Test;
import modelo.Aviation_Test;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.VentanaExterna;
import vista.Wrap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltasAviation_Test extends VentanaExterna {
    AltasAviation_Test ref;

    public AltasAviation_Test() {
        ref = this;
        w = 900;
        h = 400;
        celh = 20;
        celw = 20;
        title = "Agregar Tipo de Prueba";
        btnAccion = "AGREGAR";
        

        lbls = Aviation_Test.obtenerLabels();
        cps = Aviation_Test.obtenerComponentes();

        tipos = Aviation_Test.obtenerTipoDato();
        lgs = Aviation_Test.obtenerLongitudes();
        nnl = Aviation_Test.obtenerNoNulos();

        autoGenerar("Aviation_Test", "AGREGAR TIPO DE PRUEBA", h/10, 1, 1, 10);
        activarBotonValidar("Operacion exitosa", "Registro duplicado", "Error de relacion", "Registro agregado");

    }
}
