package vista.Bajas;

import modelo.Airplane;
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

public class bajasEmployee extends VentanaExterna {

    public bajasEmployee(){
        ref = this;
        celh = 20;
        celw = 20;
        w = 900;
        h = 400;
        title = "Eliminar empleado";

        lbls = new String[]{Employee.obtenerLabels()[0]};
        cps = new String[]{Employee.obtenerComponentes()[0]};

        tipos = new String[]{Employee.obtenerTipoDato()[0]};
        lgs = new int[]{Employee.obtenerLongitudes()[0]};
        nnl = new boolean[]{Employee.obtenerNoNulos()[0]};

        btnAccion = "ELIMINAR";
        autoGenerar("Employee","ELIMINAR EMPLEADO", h/6, 3, 3, 7);

        panel.setBackground(new Color(255, 164, 164));
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, nnl, lgs, lbls, false);
                String campo = new Employee().propiedades()[0];
                if(inps == null) return;
                String valor = inps[0];
                if(tipos[0].equals("CHAR") || tipos[0].equals("VARCHAR")) valor = "'"+inps[0]+"'";
                int codigo = dao.EliminarUniversal("Employee", campo+"="+valor);
                notificarSQL(codigo, "Eliminacion exitosa", "Registro duplicado", "Este empleado es utilizado en otros registros", "Registro eliminado");

            }
        });
    }
}
