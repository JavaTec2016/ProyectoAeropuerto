package vista.Altas;

import modelo.Aviation_Test;
import modelo.Traffic_Controller;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.Wrap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltasTraffic_Controller extends Ventana {
    AltasTraffic_Controller ref;
    public AltasTraffic_Controller(){
        ref = this;
        w = 900;
        h = 350;
        celh = 20;
        celw = 20;
        title = "Agregar Examen de Controlador de trafico";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);

        String[] lbls = Traffic_Controller.obtenerLabels();
        String[] cps = Traffic_Controller.obtenerComponentes();

        String[] tipos = Traffic_Controller.obtenerTipoDato();
        int[] lgs = Traffic_Controller.obtenerLongitudes();
        boolean[] noNulos = Traffic_Controller.obtenerNoNulos();

        btnAccion = "AGREGAR";
        autoGenerar("AGREGAR EXAMEN DE CONTROLADOR DE TRAFICO", h/8, lbls, cps, tipos, lgs, noNulos, 2);

        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] inps = recibirInputs(tipos, noNulos, lgs, lbls);
                if(inps == null){

                    return;
                }
                Traffic_Controller att = new Traffic_Controller(inps[0].toString(), inps[1].toString());
                if( dao.agregarUniversal(att) != 0){
                    JOptionPane.showMessageDialog(ref, "Error en los datos ingresados", "Error de datos", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(ref, "Operacion exitosa", "Nuevo registro", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
