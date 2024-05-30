package controlador;

import java.util.ArrayList;

import static controlador.DAO.d;

public class ConsultaFiltro extends Thread{
    String tabla;
    String filtro;
    public ArrayList res;
    public ConsultaFiltro(String t, String b){
        tabla = t;
        filtro = b;
    }
    public ConsultaFiltro(){}

    @Override
    public void run() {
        res = d.consultarUniversal(tabla, filtro);
    }
}
