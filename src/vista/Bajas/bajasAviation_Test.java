package vista.Bajas;

import modelo.Airplane;
import modelo.Aviation_Test;
import modelo.Employee;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.VentanaExterna;
import vista.Wrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class bajasAviation_Test extends VentanaExterna {

    public bajasAviation_Test(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Agregar Avion";
        lbls = new String[]{Aviation_Test.obtenerLabels()[0]};
        cps = new String[]{Aviation_Test.obtenerComponentes()[0]};

        tipos = new String[]{Aviation_Test.obtenerTipoDato()[0]};
        lgs = new int[]{Aviation_Test.obtenerLongitudes()[0]};
        nnl = new boolean[]{Aviation_Test.obtenerNoNulos()[0]};

        btnAccion = "ELIMINAR";
        autoGenerar("Aviation_Test","ELIMINAR TIPO DE PRUEBA DE AVIACION", h/6, 3, 3, 10);

        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, nnl, lgs, lbls, false);
                String campo = new Aviation_Test().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipos[0].equals("CHAR") || tipos[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                int codigo = dao.EliminarUniversal("Aviation_Test", campo+"="+valor);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "este tipo de prueba es utilizado en otros registros", "Registro eliminado");
            }
        });
    }
}
