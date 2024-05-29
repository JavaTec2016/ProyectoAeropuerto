package vista.Altas;

import modelo.Technician_Model_Expertise;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class altasExpertises extends VentanaExterna {
    altasExpertises ref;

    public altasExpertises() {
        ref = this;
        w = 900;
        h = 400;
        celw = 20;
        celh = 20;
        title = "Agregar Experiencia de Tecnico";
        btnAccion = "AGREGAR";

        lbls = Technician_Model_Expertise.obtenerLabels();
        cps = Technician_Model_Expertise.obtenerComponentes();

        tipos = Technician_Model_Expertise.obtenerTipoDato();
        lgs = Technician_Model_Expertise.obtenerLongitudes();
        nnl = Technician_Model_Expertise.obtenerNoNulos();

        autoGenerar("Technician_Model_Expertise", "AGREGAR EXPERIENCIA DE TECNICO", h/6, 2, 1, 10);
        activarBotonValidar("Operacion exitosa", "Registro duplicado", "El Tecnico o numero de modelo son incorrectos", "Registro agregado");

    }
}
