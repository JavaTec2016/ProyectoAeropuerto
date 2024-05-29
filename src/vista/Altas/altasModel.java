package vista.Altas;

import controlador.DAO;
import modelo.Aviation_Test;
import modelo.Model;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class altasModel extends VentanaExterna {
    altasModel ref;

    public altasModel(){
        ref = this;
        w = 800;
        h = 350;
        celw = 20;
        celh = 20;
        title = "Agregar Modelo";
        btnAccion = "AGREGAR";

        lbls = Model.obtenerLabels();
        cps = Model.obtenerComponentes();

        tipos = Model.obtenerTipoDato();
        lgs = Model.obtenerLongitudes();
        nnl = Model.obtenerNoNulos();

        autoGenerar("Model", "AGREGAR MODELO DE AVION", h/10, 1, 1, 10);
        activarBotonValidar("Operacion exitosa", "Registro duplicado", "Error de relacion", "Registro agregado");

    }
}
