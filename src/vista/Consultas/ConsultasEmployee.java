package vista.Consultas;

import modelo.Airplane;
import modelo.Employee;
import vista.RasLayout;
import vista.VentanaExterna;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultasEmployee extends VentanaExterna {

    public ConsultasEmployee() {
        ref = this;
        celh=20;
        celw=20;
        w = 1500;
        h = 800;
        title = "Consultar Empleados";
        btnAccion = "BUSCAR";

        lbls = Employee.obtenerLabels();
        cps = Employee.obtenerComponentes();

        tipos = Employee.obtenerTipoDato();
        lgs = Employee.obtenerLongitudes();
        nnl = Employee.obtenerNoNulos();
        props = new Employee().propiedades();

        generarConsulta("Employee", "BUSCAR EMPLEADOS", h/5, 2, 1, 9);
        panel.setBackground(new Color(168, 168, 168));
        //activarBotonModificar("Operacion exitosa", "Registro duplicado", "Numero de modelo no existe", "Modificacion", 1);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });
    }
}
