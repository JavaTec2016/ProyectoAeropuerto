package vista.Bajas;

import modelo.Employee;
import modelo.Technician_Model_Expertise;
import vista.VentanaExterna;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bajasExpertise extends VentanaExterna {

    public bajasExpertise(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Eliminar Experiencia de tecnico";
        btnAccion = "ELIMINAR";

        lbls = new String[]{Technician_Model_Expertise.obtenerLabels()[0], Technician_Model_Expertise.obtenerLabels()[1]};
        cps = new String[]{Technician_Model_Expertise.obtenerComponentes()[0], Technician_Model_Expertise.obtenerComponentes()[1]};

        tipos = new String[]{Technician_Model_Expertise.obtenerTipoDato()[0], Technician_Model_Expertise.obtenerTipoDato()[1]};
        lgs = new int[]{Technician_Model_Expertise.obtenerLongitudes()[0], Technician_Model_Expertise.obtenerLongitudes()[1]};
        nnl = new boolean[]{Technician_Model_Expertise.obtenerNoNulos()[0], Technician_Model_Expertise.obtenerNoNulos()[1]};


        autoGenerar("Technician_Model_Expertise","ELIMINAR EXPERIENCIA", h/6, 2, 2, 10);

        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, nnl, lgs, lbls);
                if(inps == null || inps.length < 2) return;
                String[] outs = new String[inps.length];
                String[] campos = new Technician_Model_Expertise().propiedades();
                int i = 0;
                for (String tipo : tipos) {

                    if(tipo.equals("CHAR") || tipo.equals("VARCHAR")) outs[i] = "'"+inps[i]+"'";
                    else outs[i] = inps[i];
                    i++;
                }
                int codigo = dao.EliminarUniversal("Technician_Model_Expertise", campos[0]+"="+outs[0], campos[1]+"="+outs[1]);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "la experiencia aparece en otros registros o los datos son incorrectos", "Registro eliminado");

            }
        });
    }
}
