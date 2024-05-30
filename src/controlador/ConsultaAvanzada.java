package controlador;

import modelo.Registrable;
import vista.VentanaExterna;

import javax.swing.*;
import java.util.ArrayList;

public class ConsultaAvanzada extends Thread {
    VentanaExterna v;
    public ConsultaAvanzada(VentanaExterna vn){
        v = vn;
    }

    @Override
    public void run() {

        String campo = v.props[v.opciones.getSelectedIndex()];

        String valor = v.extraerInput(v.inputs[1]);
        System.out.println(valor);
        if(valor.isEmpty()){
            ArrayList<Registrable> res = v.dao.consultarUniversal(v.objetivo);

            while (v.model.getRowCount() > 0) v.model.removeRow(0);
            for (Registrable r : res) {
                v.model.addRow(r.obtenerValores());
            }
            return;
        }
        if(v.tipos[v.opciones.getSelectedIndex()].equalsIgnoreCase("char") || v.tipos[v.opciones.getSelectedIndex()].equalsIgnoreCase("varchar")){
            if(!v.tipos[v.opciones.getSelectedIndex()].equalsIgnoreCase("null"))  valor = "'"+valor+"'";
        }
        if(v.validarInput(valor, v.tipos[v.opciones.getSelectedIndex()], v.nnl[v.opciones.getSelectedIndex()], v.lgs[v.opciones.getSelectedIndex()], v.lbls[v.opciones.getSelectedIndex()], true) != 0) return;
        ArrayList<Registrable> res = v.dao.consultarUniversal(v.objetivo, campo+"="+valor);
        while (v.model.getRowCount() > 0) v.model.removeRow(0);
        if(res == null){
            JOptionPane.showMessageDialog(v, "Sin resultados", "Consulta vacia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Registrable r : res) {
            v.model.addRow(r.obtenerValores());
        }
    }
}
