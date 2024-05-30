package vista.Bajas;

import modelo.Airplane_Technician_Test;
import modelo.Employee;
import modelo.Technician_Model_Expertise;
import vista.VentanaExterna;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bajasAirplane_Technician_Test extends VentanaExterna {

    public bajasAirplane_Technician_Test(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Eliminar Prueba de Avion";
        btnAccion = "ELIMINAR";

        lbls = new String[]{Airplane_Technician_Test.obtenerLabels()[0]};
        cps = new String[]{Airplane_Technician_Test.obtenerComponentes()[0]};

        tipos = new String[]{Airplane_Technician_Test.obtenerTipoDato()[0]};
        lgs = new int[]{Airplane_Technician_Test.obtenerLongitudes()[0]};
        nnl = new boolean[]{Airplane_Technician_Test.obtenerNoNulos()[0]};

        autoGenerar("Airplane_Technician_Test","ELIMINAR PRUEBA DE AVION", h/6, 2, 0, 10);

        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, nnl, lgs, lbls, false);
                if(inps == null) return;
                String[] outs = new String[inps.length];
                String[] campos = new Airplane_Technician_Test().propiedades();

                int i = 0;
                for (String tipo : tipos) {
                    if(tipo.equals("CHAR") || tipo.equals("VARCHAR")) outs[i] = "'"+inps[i]+"'";
                    else outs[i] = inps[i];
                    i++;
                }
                int codigo = dao.EliminarUniversal("Airplane_Technician_Test", campos[0]+"="+outs[0]);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "No se encontro a id de prueba", "Registro eliminado");

            }
        });
    }
}
