package vista.Bajas;

import modelo.Airplane;
import modelo.Aviation_Test;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.Wrap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class bajasAviation_Test extends Ventana {

    public bajasAviation_Test(){
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


        String[] lbl = {Aviation_Test.obtenerLabels()[0]};
        String[] cps = {Aviation_Test.obtenerComponentes()[0]};

        String[] tipo = {Aviation_Test.obtenerTipoDato()[0]};
        int[] lgs = {Aviation_Test.obtenerLongitudes()[0]};
        boolean[] nnl = {Aviation_Test.obtenerNoNulos()[0]};

        btnAccion = "ELIMINAR";
        autoGenerar("ELIMINAR PRUEBA DE AVIACION", h/6, lbl, cps, tipo, lgs, nnl, 4);
        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipo, nnl, lgs, lbl);
                String campo = new Aviation_Test().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipo[0].equals("CHAR") || tipo[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                int codigo = dao.EliminarUniversal("Aviation_Test", campo+"="+valor);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "este tipo de prueba es utilizado en otros registros", "Registro eliminado");
            }
        });
    }
}
