package vista;

import controlador.DAO;

import javax.swing.*;
import java.util.ArrayList;

import static controlador.DAO.d;

public class Ventana extends JInternalFrame{
    public InternalRasLayout ras;
    public ArrayList<Wrap> salida;
    public String title;
    public int w;
    public int h;
    public String tipo;

    public DAO dao = d;

}
