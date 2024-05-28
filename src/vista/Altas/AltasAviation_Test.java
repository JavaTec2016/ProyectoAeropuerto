package vista.Altas;

import modelo.Airplane_Technician_Test;
import modelo.Aviation_Test;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.Wrap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltasAviation_Test extends Ventana {
    AltasAviation_Test ref;

    public AltasAviation_Test() {
        ref = this;
        w = 900;
        h = 600;
        celh = 20;
        celw = 20;
        title = "Agregar Tipo de Prueba";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);

        String[] lbls = Aviation_Test.obtenerLabels();
        String[] cps = Aviation_Test.obtenerComponentes();

        String[] tipos = Aviation_Test.obtenerTipoDato();
        int[] lgs = Aviation_Test.obtenerLongitudes();
        boolean[] noNulos = Aviation_Test.obtenerNoNulos();

        btnAccion = "AGREGAR";
        autoGenerar("AGREGAR TIPO DE PRUEBA", h/10, lbls, cps, tipos, lgs, noNulos, 1);

        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, noNulos, lgs, lbls);
                if(inps == null){

                    return;
                }
                Aviation_Test att = new Aviation_Test(Integer.parseInt(inps[0]), inps[1], Byte.parseByte(inps[2]));
                if( dao.agregarUniversal(att) != 0){
                    JOptionPane.showMessageDialog(ref, "Error en los datos, verifique las experiencias", "Error de datos", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(ref, "Operacion exitosa", "Nuevo registro", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
