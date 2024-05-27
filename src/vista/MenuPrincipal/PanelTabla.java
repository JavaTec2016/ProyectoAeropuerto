package vista.MenuPrincipal;

import modelo.Registrable;
import vista.PanelRasLayout;
import vista.Panelo;
import vista.Wrap;
import vista.lblFont;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class PanelTabla extends Panelo {
    JFrame vn;
    String[] tuplas;
    JTable tablaUniversal;
    DefaultTableModel model;
    JScrollPane scroller;
    lblFont lblPlaceholder;

    public PanelTabla(JFrame v, int x, int y, int w, int h){
        vn = v;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        salida = new ArrayList<Wrap>();
        ras = new PanelRasLayout(this, x, y, w, h);
        Color panelColor = new Color(204, 194, 194);
        setBackground(panelColor);

        lblPlaceholder = new lblFont("Aquí se verán los datos", "Arial",Font.BOLD, 30, 100,100,100);
        tablaUniversal = new JTable();
        scroller = new JScrollPane(tablaUniversal);
        scroller.setVisible(false);
        tablaUniversal.setBackground(panelColor);

        Wrap wPlace = new Wrap(lblPlaceholder);
        ras.agregarRelativo(wPlace, w/2, h/2, lblPlaceholder.getWidth(), lblPlaceholder.getHeight());
        wPlace.centerOffset(1,1);

        Wrap wScroll = new Wrap(scroller);
        wScroll.resize = false;
        ras.agregarRelativo(wScroll, w/2, h/2, (int) (w*0.9), (int) (h*0.8));
        wScroll.centerOffset(1,1);
        wScroll.posicionarRelativo(this);

        salida.add(wScroll);
        salida.add(wPlace);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(salida, ras);
            }
        });
    }
    public void prepararTabla(String[] tp){
        tuplas = tp;
        model = new DefaultTableModel(null, tuplas);
        tablaUniversal = new JTable(model);
        scroller.setViewportView(tablaUniversal);
        scroller.setVisible(true);
    }

    public void agregarRegistro(Registrable r){
        Object[] datos = r.obtenerValores();
        model.addRow(datos);
    }
    public void agregarRegistros(ArrayList<Registrable> rs){
        for (Registrable r : rs) {
            agregarRegistro(r);
        }
    }
}
