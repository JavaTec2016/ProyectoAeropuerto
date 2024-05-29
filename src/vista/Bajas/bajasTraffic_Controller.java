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

        lbls = new String[]{Traffic_Controller.obtenerLabels()[0]};
        cps = new String[]{Traffic_Controller.obtenerComponentes()[0]};

        tipos = new String[]{Traffic_Controller.obtenerTipoDato()[0]};
        lgs = new int[]{Traffic_Controller.obtenerLongitudes()[0]};
        nnl = new boolean[]{Traffic_Controller.obtenerNoNulos()[0]};
        btnAccion = "ELIMINAR";
        autoGenerar("Traffic_Controller","ELIMINAR CONTROLADOR DE TRAFICO", h/6, 3, 2, 7);
        panel.setBackground(new Color(255, 164, 164));


        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, nnl, lgs, lbls, false);
                String campo = new Employee().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipos[0].equals("CHAR") || tipos[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                int codigo = dao.EliminarUniversal("Traffic_Controller", campo+"="+valor);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "Este empleado es utilizado en otros registros", "Registro eliminado");

            }
        });

    }
}
