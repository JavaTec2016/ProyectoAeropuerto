package vista.Bajas;

import modelo.Airplane;
import modelo.Model;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.VentanaExterna;
import vista.Wrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class bajasModel extends VentanaExterna {

    public bajasModel(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Agregar Avion";
        btnAccion = "ELIMINAR";

        lbls = new String[]{Model.obtenerLabels()[0]};
        cps = new String[]{Model.obtenerComponentes()[0]};

        tipos = new String[]{Model.obtenerTipoDato()[0]};
        lgs = new int[]{Model.obtenerLongitudes()[0]};
        nnl = new boolean[]{Model.obtenerNoNulos()[0]};

        autoGenerar("Model","ELIMINAR MODELO DE AVION", h/6, 3, 3, 10);
        panel.setBackground(new Color(255, 164, 164));

        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, nnl, lgs, lbls);
                String campo = new Model().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipos[0].equals("CHAR") || tipos[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                int codigo = dao.EliminarUniversal("Model", campo+"="+valor);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "Este modelo es utilizado en otros registros", "Registro eliminado");
            }
        });
    }
}
