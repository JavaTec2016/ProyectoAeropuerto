package vista.Altas;

import controlador.DAO;
import modelo.Model;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class altasModel extends Ventana {
    Panelo panelAgregar;
    lblFont lblAgregar;

    JTextField txtModelNumber;
    JTextField txtCapacity;
    JTextField txtWeight;

    JButton btnLimpiar;
    JButton btnCancelar;

    JButton btnValidar;
    int celw = 20;
    int celh = 20;
    Ventana ref;

    public altasModel(){
        ref = this;
        w = 800;
        h = 600;
        title = "Agregar Modelo";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);


        lblAgregar = new lblFont("DATOS DEL MODELO", "Arial", Font.BOLD, 30, 0,0,0);
        panelAgregar = new Panelo();
        panelAgregar.w = w;
        panelAgregar.h = h/6;
        panelAgregar.x = 0;
        panelAgregar.y = 0;
        panelAgregar.ras = new PanelRasLayout(panelAgregar, 0, 0, w, h/6);
        panelAgregar.salida = new ArrayList<Wrap>();
        panelAgregar.setBackground(new Color(184, 251, 184));

        txtModelNumber = new JTextField(10);
        txtCapacity = new JTextField(10);
        txtWeight = new JTextField(10);

        btnCancelar = new JButton("CANCELAR");
        btnLimpiar = new JButton("LIMPIAR");
        btnValidar = new JButton("AGREGAR");


        Wrap wPanel = new Wrap(panelAgregar);
        Wrap wLblAgregar = new Wrap(lblAgregar);

        int xi = 5, yi = 8;
        ras.agregarRelativo(wPanel, panelAgregar.x, panelAgregar.y, panelAgregar.w, panelAgregar.h);
        Wrap wLblModelo = ras.encuadrarRelativo(new lblFont("Numero de Modelo", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi, 8, 2);
        Wrap wtxtModelo = ras.encuadrarRelativo(txtModelNumber, xi+8+2, yi, 12, 2);
        Wrap wLblCap = ras.encuadrarRelativo(new lblFont("Capacidad", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+3, 8, 2);
        Wrap wtxtCap = ras.encuadrarRelativo(txtCapacity, xi+8+2, yi+3, 12, 2);
        Wrap wLblWeight = ras.encuadrarRelativo(new lblFont("Peso", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+6, 8, 2);
        Wrap wtxtWeight = ras.encuadrarRelativo(txtWeight, xi+8+2, yi+6, 12, 2);

        Wrap wBtnValidar = ras.encuadrarRelativo(btnValidar, xi-2, yi+14, 12, 2);
        Wrap wBtnCancelar = ras.encuadrarRelativo(btnCancelar, xi+12+2, yi+14, 12,2);
        Wrap wBtnLimpiar = ras.encuadrarRelativo(btnLimpiar, xi+14+9, yi+5, 10,2);

        wBtnValidar.centerOffset(1,1);
        wBtnCancelar.centerOffset(1,1);
        wBtnLimpiar.centerOffset(1,1);

        panelAgregar.ras.agregarRelativo(wLblAgregar, panelAgregar.w/2, panelAgregar.h/2, 400, 30);
        wLblAgregar.centerOffset(1,1);
        wLblAgregar.posicionarRelativo(panelAgregar);


        panelAgregar.salida.add(wLblAgregar);

        salida.add(wPanel);
        salida.add(wLblModelo);
        salida.add(wtxtModelo);
        salida.add(wLblCap);
        salida.add(wtxtCap);
        salida.add(wLblWeight);
        salida.add(wtxtWeight);
        salida.add(wBtnValidar);
        salida.add(wBtnCancelar);
        salida.add(wBtnLimpiar);


        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCapacity.setText("");
                txtWeight.setText("");
                txtModelNumber.setText("");
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ref.setClosed(true);
                } catch (PropertyVetoException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtModelNumber.getText().length() != 5){
                    JOptionPane.showMessageDialog(ref,"Longitud del numero de modelo incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean ccap = false, cweight = false;
                try{

                    int cap = Integer.parseInt(txtCapacity.getText());
                    ccap = true;
                    int weight = Integer.parseInt(txtWeight.getText());
                    cweight = true;

                    Model m = new Model(txtModelNumber.getText(), cap, weight);

                    //cosas de DAO y sql
                    int codigo = dao.agregarUniversal(m);
                    notificarSQL(codigo, "Operacion exitosa", "Registro duplicado", "Error de relacion", "Registro agregado");

                }catch (NumberFormatException exc){
                    if(ccap) JOptionPane.showMessageDialog(ref, "Formato del peso incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                    else JOptionPane.showMessageDialog(ref, "Formato de capacidad incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelAgregar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(panelAgregar.salida, panelAgregar.ras);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                InternalRasLayout.refrescar(salida, ras);
            }
        });

    }
}
