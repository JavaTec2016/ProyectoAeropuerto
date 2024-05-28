package vista.Bajas;

import modelo.Airplane;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.Wrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class bajasAirplane extends Ventana {

    public bajasAirplane(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Agregar Avion";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);


        String[] lbl = {Airplane.obtenerLabels()[0]};
        String[] cps = {Airplane.obtenerComponentes()[0]};

        String[] tipo = {Airplane.obtenerTipoDato()[0]};
        int[] lgs = {Airplane.obtenerLongitudes()[0]};
        boolean[] nnl = {Airplane.obtenerNoNulos()[0]};

        btnAccion = "ELIMINAR";
        autoGenerar("ELIMINAR AVION", h/6, lbl, cps, tipo, lgs, nnl, 4);
        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipo, nnl, lgs, lbl);
                String campo = new Airplane().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipo[0].equals("CHAR") || tipo[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                if(dao.EliminarUniversal("Airplane", campo+"="+valor)){
                    JOptionPane.showMessageDialog(ref, "Eliminacion exitosa", "Eliminacion", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(ref, "Error en la ID", "No encontrado", JOptionPane.ERROR_MESSAGE);
                };
            }
        });

    }
}
