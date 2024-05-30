package vista.Altas;

import controlador.ConsultaFiltro;
import modelo.Airplane;
import modelo.Airplane_Technician_Test;
import modelo.ModeloBD;
import vista.InternalRasLayout;
import vista.Ventana;
import vista.VentanaExterna;
import vista.Wrap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AltasAirplane_Technician_Test extends VentanaExterna {

    AltasAirplane_Technician_Test ref;
    public AltasAirplane_Technician_Test() {
        ref = this;
        w = 900;
        h = 600;
        celh = 20;
        celw = 20;
        title = "Agregar Prueba";
        btnAccion = "AGREGAR";

        lbls = Airplane_Technician_Test.obtenerLabels();
        cps = Airplane_Technician_Test.obtenerComponentes();

        tipos = Airplane_Technician_Test.obtenerTipoDato();
        lgs = Airplane_Technician_Test.obtenerLongitudes();
        nnl = Airplane_Technician_Test.obtenerNoNulos();

        autoGenerar("Airplane_Technician_Test", "AGREGAR PRUEBA DE AVION", h/10, 1, 1, 10);
        especialBotonActivar("Operacion exitosa", "Registro duplicado", "los datos de experiencia o registro de avion son incorrectos", "Registro agregado");

    }
    void especialBotonActivar(String mensajeExito, String mensajeDupe, String mensajeRelacion, String tipo){
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int j = 0;
                Object[] inps = new Object[inputs.length];
                for(JComponent input : inputs){
                    String in = extraerInput(input);

                    if(validarInput(in, tipos[j], nnl[j], lgs[j], lbls[j], false) != 0) return;

                    inps[j] = extraerInput(input, tipos[j]);
                    j++;
                }

                String RN = extraerInput(inputs[3]);
                String MN = extraerInput(inputs[4]);


                ConsultaFiltro regNum = new ConsultaFiltro("Airplane", "Registration_Number="+RN);
                regNum.start();
                try {
                    regNum.join();
                    Airplane ap = (Airplane) regNum.res.get(0);
                    //si el avion existe pero no coincidel el modelo de la prueba, erorr
                    if(!MN.equals(ap.getModel_Number())){
                        JOptionPane.showMessageDialog(ref, "Denegado: el modelo del avion es incorrecto.", "Error de integridad de datos", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (InterruptedException ie) {
                    System.out.println("Hilo de procesos interrumpido");
                    ie.printStackTrace();
                }catch (NullPointerException nl){
                    System.out.println("La consulta no existe");
                    nl.printStackTrace();
                }


                int codigo = dao.agregarUniversal(ModeloBD.instanciar(inps, objetivo));
                notificarSQL(codigo, mensajeExito, mensajeDupe, mensajeRelacion, tipo);
            }
        });
    }
}
