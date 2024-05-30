package vista.Cambios;

import controlador.ConsultaFiltro;
import modelo.Airplane;
import modelo.Airplane_Technician_Test;
import modelo.ModeloBD;
import modelo.Registrable;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class CambiosAirplane extends VentanaExterna {
    private String objetivo = "Airplane";
    public CambiosAirplane(){
        ref = this;
        celh=20;
        celw=20;
        w = 800;
        h = 400;
        title = "Modificar Avion";
        btnAccion = "MODIFICAR";

        lbls = Airplane.obtenerLabels();
        cps = Airplane.obtenerComponentes();

        tipos = Airplane.obtenerTipoDato();
        lgs = Airplane.obtenerLongitudes();
        nnl = Airplane.obtenerNoNulos();

        autoGenerar("Airplane", "MODIFICAR AVION", h/5, 2, 1, 9);
        panel.setBackground(new Color(184, 197, 251));
        filtroCambio("Operacion exitosa", "Registro duplicado", "Numero de modelo no existe", "Modificacion", 1);

        lblFont lblVacios = new lblFont("los espacios en blanco no sufriran cambios", "Arial", Font.ITALIC, 13, 0,0,0);
        panel.salida.add(panel.ras.encuadrarRelativo(lblVacios, 0, (int)(panel.h/3), panel.w, panel.h));
        salida.get(salida.size()-1).centerOffset(1,1);
        lblVacios.setVerticalAlignment(SwingConstants.CENTER);
        lblVacios.setHorizontalAlignment(SwingConstants.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

    }

    public void filtroCambio(String mensajeExito, String mensajeDupe, String mensajeRelacion, String tipo, int primarias) {



        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int j = 0;
                //prepara los argumentos para el objeto
                Object[] inps = new Object[inputs.length];
                for(JComponent input : inputs){
                    String in = extraerInput(input);

                    if(validarInput(in, tipos[j], nnl[j], lgs[j], lbls[j], true) != 0){
                        System.out.println("lamomba");
                        return;
                    }
                    if(in.isBlank() || in.equalsIgnoreCase("NULL")) inps[j] = null;
                    else inps[j] = extraerInput(input, tipos[j]);
                    j++;
                }

                String RN = extraerInput(inputs[0]);
                String MN = extraerInput(inputs[1]);


                ConsultaFiltro regNum = new ConsultaFiltro("Airplane_Technician_Test", "Registration_Number="+RN);
                regNum.start();
                try {
                    regNum.join();
                    Airplane_Technician_Test ap = (Airplane_Technician_Test) regNum.res.get(0);
                    //si hay pruebas del avion, pero con otro modelo, entonces el registro de la prueba puede quedar corrompido
                    if(!MN.equals(ap.getModel_Number())){
                        JOptionPane.showMessageDialog(ref, "Denegado: hay pruebas del avion con el modelo anterior.", "Error de integridad de datos", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (InterruptedException ie) {
                    System.out.println("Hilo de procesos interrumpido");
                    ie.printStackTrace();
                }catch (NullPointerException nl){
                    System.out.println("La consulta no existe");
                    nl.printStackTrace();
                }

                int codigo = dao.actualizarUniversal(ModeloBD.instanciar(inps, objetivo), primarias);
                notificarSQL(codigo, mensajeExito, mensajeDupe, mensajeRelacion, tipo);
            }
        });
    }
}
