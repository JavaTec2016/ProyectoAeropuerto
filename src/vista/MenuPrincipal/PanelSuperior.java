package vista.MenuPrincipal;

import modelo.Registrable;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import static controlador.DAO.d;

public class PanelSuperior extends Panelo {
    JFrame vn;
    lblFont lblBuscar;
    JTextField txtBuscar;
    JButton btnBuscar;

    //los 4 fantasticos

    public JButton btnAltas;
    public JButton btnBajas;
    public JButton btnCambios;
    public JButton btnConsultas;

    Class<?> modelo;

    public PanelSuperior(JFrame v, Panelo lateral){
        vn = v;
        x = lateral.w;
        y = 0;
        w = vn.getWidth()-x;
        h = vn.getHeight()/6;
        //System.out.println(""+x+" "+y+" "+w+" "+h);
        salida = new ArrayList<Wrap>();
        ras = new PanelRasLayout(this, x, y, w, h);
        Color panelColor = new Color(255,255,255);
        setBackground(panelColor);

        lblBuscar = new lblFont("Ingrese un ID", "Arial", Font.BOLD, 15, 0,0,0);
        txtBuscar = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(0, 85, 62));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));

        btnAltas = new JButton("AGREGAR..");
        btnBajas = new JButton("ELIMINAR..");
        btnCambios = new JButton("MODIFICAR..");
        btnConsultas = new JButton("Consultar..");

        btnAltas.setBackground(new Color(210,210,210));
        btnAltas.setForeground(new Color(0, 85, 62));
        btnAltas.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 15));

        btnBajas.setBackground(new Color(133, 36, 36));
        btnBajas.setForeground(new Color(210,210,210));
        btnBajas.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 15));

        btnCambios.setBackground(new Color(0, 78, 120));
        btnCambios.setForeground(new Color(210,210,210));
        btnCambios.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 15));

        btnConsultas.setBackground(new Color(210,210,210));
        btnConsultas.setForeground(new Color(0, 78, 120));
        btnConsultas.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 15));

        Wrap wLblBuscar = new Wrap(lblBuscar);
        Wrap wTxtBuscar = new Wrap(txtBuscar);
        Wrap wBtnBuscar = new Wrap(btnBuscar);

        Wrap wbtnAltas = new Wrap(btnAltas);
        Wrap wbtnBajas = new Wrap(btnBajas);
        Wrap wbtnCambios = new Wrap(btnCambios);
        Wrap wbtnConsultas = new Wrap(btnConsultas);

        ras.agregarRelativo(wLblBuscar, 20, 55, 100, 20);
        wLblBuscar.centerOffset(0,1);
        wLblBuscar.posicionarRelativo(this);
        ras.agregarRelativo(wTxtBuscar, wLblBuscar.xFrom+wLblBuscar.widthFrom+20, 40, 160, 30);
        wLblBuscar.centerOffset(0,1);
        ras.agregarRelativo(wBtnBuscar, wTxtBuscar.xFrom+wTxtBuscar.widthFrom-120, wTxtBuscar.yFrom+wTxtBuscar.heightFrom+30, 120, 30);
        wBtnBuscar.centerOffset(0,1, this);

        ras.agregarRelativo(wbtnAltas, wBtnBuscar.xFrom+wBtnBuscar.widthFrom+160, 45, 130, 30);
        wbtnAltas.centerOffset(1,1, this);
        ras.agregarRelativo(wbtnBajas, wbtnAltas.xFrom+wbtnAltas.widthFrom+40, 45, 130, 30);
        wbtnBajas.centerOffset(1,1, this);
        ras.agregarRelativo(wbtnCambios, wbtnBajas.xFrom+wbtnBajas.widthFrom+40, 45, 130, 30);
        wbtnCambios.centerOffset(1,1, this);
        ras.agregarRelativo(wbtnConsultas, wbtnAltas.xFrom, wbtnAltas.yFrom+wbtnAltas.heightFrom+30, 130, 30);
        wbtnConsultas.centerOffset(1,1, this);

        salida.add(wLblBuscar);
        salida.add(wTxtBuscar);
        salida.add(wBtnBuscar);

        salida.add(wbtnAltas);
        salida.add(wbtnBajas);
        salida.add(wbtnCambios);
        salida.add(wbtnConsultas);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelLateral p = ((PanelLateral)lateral);
                if(p.btnActual == null) {
                    JOptionPane.showMessageDialog(vn, "Seleccione una tabla a consultar", "Consulta indefinida", JOptionPane.ERROR_MESSAGE);
                    return;
                };
                ((VentanaPrincipal)vn).setRegistros();

            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(salida, ras);
            }
        });
    }
}
