package vista.Altas;
import modelo.Traffic_Controller;
import vista.VentanaExterna;


public class AltasTraffic_Controller extends VentanaExterna {
    AltasTraffic_Controller ref;
    public AltasTraffic_Controller(){
        ref = this;
        w = 900;
        h = 350;
        celh = 20;
        celw = 20;
        title = "Agregar Examen de Controlador de trafico";
        btnAccion = "AGREGAR";

        lbls = Traffic_Controller.obtenerLabels();
        cps = Traffic_Controller.obtenerComponentes();

        tipos = Traffic_Controller.obtenerTipoDato();
        lgs = Traffic_Controller.obtenerLongitudes();
        nnl = Traffic_Controller.obtenerNoNulos();


        autoGenerar("Traffic_Controller", "AGREGAR EXAMEN DE CONTROLADOR DE TRAFICO", h/6, 2, 1, 11);
        activarBotonValidar("Operacion exitosa", "Registro duplicado", "El SSN de empleado es incorrecto", "Registro agregado");

    }
}
