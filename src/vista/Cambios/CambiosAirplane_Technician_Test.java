package vista.Cambios;

import controlador.ConsultaFiltro;
import modelo.Airplane;
import modelo.Airplane_Technician_Test;
import modelo.ModeloBD;
import vista.RasLayout;
import vista.VentanaExterna;
import vista.lblFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CambiosAirplane_Technician_Test extends VentanaExterna {

    CambiosAirplane_Technician_Test ref;
    public CambiosAirplane_Technician_Test() {
        ref = this;
        w = 900;
        h = 600;
        celh = 20;
        celw = 20;
        title = "Modificar Prueba";
        btnAccion = "MODIFICAR";

        lbls = Airplane_Technician_Test.obtenerLabels();
        cps = Airplane_Technician_Test.obtenerComponentes();

        tipos = Airplane_Technician_Test.obtenerTipoDato();
        lgs = Airplane_Technician_Test.obtenerLongitudes();
        nnl = Airplane_Technician_Test.obtenerNoNulos();

        autoGenerar("Airplane_Technician_Test", "MODIFICAR PRUEBA", h / 5, 1, 1, 10);
        panel.setBackground(new Color(184, 197, 251));
        especialBotonModificar("Operacion exitosa", "Registro duplicado", "Las credenciales no coinciden, revise las experiencias y el numero de avion", "Modificacion", 3);

        lblFont lblVacios = new lblFont("los espacios en blanco no sufriran cambios", "Arial", Font.ITALIC, 13, 0, 0, 0);
        panel.salida.add(panel.ras.encuadrarRelativo(lblVacios, 0, (int) (panel.h / 3), panel.w, panel.h));
        salida.get(salida.size() - 1).centerOffset(1, 1);
        lblVacios.setVerticalAlignment(SwingConstants.CENTER);
        lblVacios.setHorizontalAlignment(SwingConstants.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

    }
    public void especialBotonModificar(String mensajeExito, String mensajeDupe, String mensajeRelacion, String tipo, int primarias){
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(!filtroCambio()) return;
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

                //String RN = extraerInput(inputs[3]);
                String MN = extraerInput(inputs[4]);

                if(inps[0] != null){
                    ConsultaFiltro regPrueba = new ConsultaFiltro("Airplane_Technician_Test", "Test_Id="+inps[0]);
                    ConsultaFiltro regNum;
                    regPrueba.start();
                    try {
                        //toma los datos de avion de la prueba a modificar y verifica que aun coincidan
                        regPrueba.join();
                        int reg = ((Airplane_Technician_Test)regPrueba.res.get(0)).getRegistration_Number();
                        String mod = ((Airplane_Technician_Test)regPrueba.res.get(0)).getModel_Number();

                        //si se especifica un nuevo avion y modelo, entonces solo revisa que coincidan
                        //si se especifica solo uno de los dos, entonces revisa que coincida con su pareja anterior
                        //si ninguno se especifica entonces los anteriores aun coinciden y no hay problemas

                        if(inps[3] != null) reg = (Integer)inps[3];
                        if(inps[4] != null) mod = inps[4].toString();
                        regNum = new ConsultaFiltro("Airplane", "Registration_Number="+reg+" AND Model_Number='"+mod+"'");
                        regNum.start();
                        regNum.join();
                        System.out.println(regNum.res.size());

                    } catch (InterruptedException ie) {
                        System.out.println("Hilo de procesos interrumpido");
                        ie.printStackTrace();
                    }catch (NullPointerException nl){
                        JOptionPane.showMessageDialog(ref, "Denegado: los datos del avion no coinciden.", "Error de integridad de datos", JOptionPane.ERROR_MESSAGE);
                        System.out.println("La consulta no existe");
                        nl.printStackTrace();
                        return;
                    }
                }

                System.out.println(inps.length);
                int codigo = dao.actualizarUniversal(ModeloBD.instanciar(inps, objetivo), 1);
                notificarSQL(codigo, mensajeExito, mensajeDupe, mensajeRelacion, tipo);
            }
        });
    }
}
