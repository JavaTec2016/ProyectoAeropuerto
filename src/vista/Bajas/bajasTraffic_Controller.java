package vista.Bajas;

import modelo.Employee;
import modelo.Traffic_Controller;
import vista.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class bajasTraffic_Controller extends VentanaExterna {

    public bajasTraffic_Controller(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Eliminar controlador de trafico";
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] lbl = {Traffic_Controller.obtenerLabels()[0]};
        String[] cps = {Traffic_Controller.obtenerComponentes()[0]};

        String[] tipo = {Traffic_Controller.obtenerTipoDato()[0]};
        int[] lgs = {Traffic_Controller.obtenerLongitudes()[0]};
        boolean[] nnl = {Traffic_Controller.obtenerNoNulos()[0]};
        btnAccion = "ELIMINAR";
        autoGenerar("ELIMINAR CONTROLADOR DE TRAFICO", h/6, lbl, cps, tipo, lgs, nnl, 3, 2, 7);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });
        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipo, nnl, lgs, lbl);
                String campo = new Employee().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipo[0].equals("CHAR") || tipo[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                int codigo = dao.EliminarUniversal("Employee", campo+"="+valor);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "Este empleado es utilizado en otros registros", "Registro eliminado");

            }
        });

    }
}
