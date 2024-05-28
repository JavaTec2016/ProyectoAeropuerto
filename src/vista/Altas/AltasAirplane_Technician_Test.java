package vista.Altas;

import modelo.Airplane_Technician_Test;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.Wrap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltasAirplane_Technician_Test extends Ventana {

    AltasAirplane_Technician_Test ref;
    public AltasAirplane_Technician_Test() {
        ref = this;
        w = 900;
        h = 800;
        celh = 20;
        celw = 20;
        title = "Agregar Prueba";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);

        String[] lbls = Airplane_Technician_Test.obtenerLabels();
        String[] cps = Airplane_Technician_Test.obtenerComponentes();

        String[] tipos = Airplane_Technician_Test.obtenerTipoDato();
        int[] lgs = Airplane_Technician_Test.obtenerLongitudes();
        boolean[] noNulos = Airplane_Technician_Test.obtenerNoNulos();


        autoGenerar("AGREGAR PRUEBA", h/10, lbls, cps, tipos, lgs, noNulos);

        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, noNulos, lgs, lbls);
                if(inps == null){

                    return;
                }
                Airplane_Technician_Test att = new Airplane_Technician_Test(inps[0], inps[1], Integer.parseInt(inps[2]), Integer.parseInt(inps[3]), inps[4], Short.parseShort(inps[5]), Short.parseShort(inps[5]));
                if( dao.agregarUniversal(att) != 0){
                    JOptionPane.showMessageDialog(ref, "Error en los datos, verifique las experiencias", "Error de datos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
