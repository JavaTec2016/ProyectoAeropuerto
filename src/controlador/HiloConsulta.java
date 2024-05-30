package controlador;

import modelo.Registrable;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.util.ArrayList;

import static controlador.DAO.d;

public class HiloConsulta extends Thread {
    VentanaPrincipal v;

    public HiloConsulta(VentanaPrincipal vn){
        v = vn;
    }
    @Override
    public void run() {
        if(v.barraTablas.btnActual == null){
            JOptionPane.showMessageDialog(v, "Seleccione una tabla a consultar", "Consulta indefinida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<Registrable> rs = d.consultarUniversal(v.barraTablas.obtenerTabla());
        if(rs == null){
            JOptionPane.showMessageDialog(v, "Sin resultados", "Consulta vacia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        System.out.println("Se consulto la tabla " + v.barraTablas.obtenerTabla() + " con " + rs.size() + " registros.");
        v.panelTabla.agregarRegistros(rs);
    }
}
