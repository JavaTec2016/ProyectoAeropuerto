package vista.Altas;

import modelo.Employee;
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

public class altasEmployee extends Ventana {
    Panelo panelAgregar;
    lblFont lblAgregar;

    JTextField txtSSN;
    JTextField txtFirstName;
    JTextField txtLastName;
    JTextField txtAddressStreet;
    JTextField txtAddressCity;
    JTextField txtAddressPostal;
    JTextField txtPhoneNumber;
    JTextField txtUnionMembership;
    JTextField txtSalary;
    JTextField txtCharge;

    JButton btnLimpiar;
    JButton btnCancelar;

    JButton btnValidar;
    int celw = 20;
    int celh = 20;
    Ventana ref;

    public altasEmployee(){
        ref = this;
        w = 800;
        h = 600;
        title = "Agregar Empleado";
        ras = new InternalRasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(false);
        setMaximizable(false);


        lblAgregar = new lblFont("DATOS DEL EMPLEADO", "Arial", Font.BOLD, 30, 0,0,0);
        panelAgregar = new Panelo();
        panelAgregar.w = w;
        panelAgregar.h = h/6;
        panelAgregar.x = 0;
        panelAgregar.y = 0;
        panelAgregar.ras = new PanelRasLayout(panelAgregar, 0, 0, w, h/6);
        panelAgregar.salida = new ArrayList<Wrap>();
        panelAgregar.setBackground(new Color(184, 251, 184));

        txtSSN = new JTextField(10);
        txtFirstName = new JTextField(10);
        txtLastName = new JTextField(10);

        btnCancelar = new JButton("CANCELAR");
        btnLimpiar = new JButton("LIMPIAR");
        btnValidar = new JButton("AGREGAR");


        Wrap wPanel = new Wrap(panelAgregar);
        Wrap wLblAgregar = new Wrap(lblAgregar);

        int xi = 5, yi = 8;
        ras.agregarRelativo(wPanel, panelAgregar.x, panelAgregar.y, panelAgregar.w, panelAgregar.h);
        Wrap wLblSSN = ras.encuadrarRelativo(new lblFont("SSN", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi, 8, 2);
        Wrap wtxtSSN = ras.encuadrarRelativo(txtSSN, xi+8+2, yi, 12, 2);
        Wrap wLblFirstName = ras.encuadrarRelativo(new lblFont("Primer Nombre", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+3, 8, 2);
        Wrap wtxtFirstName = ras.encuadrarRelativo(txtFirstName, xi+8+2, yi+3, 12, 2);
        Wrap wLblLastName = ras.encuadrarRelativo(new lblFont("Ultimo Nombre", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+6, 8, 2);
        Wrap wtxtLastName = ras.encuadrarRelativo(txtLastName, xi+8+2, yi+6, 12, 2);

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
        salida.add(wLblSSN);
        salida.add(wtxtSSN);
        salida.add(wLblFirstName);
        salida.add(wtxtFirstName);
        salida.add(wLblLastName);
        salida.add(wtxtLastName);
        salida.add(wBtnValidar);
        salida.add(wBtnCancelar);
        salida.add(wBtnLimpiar);


        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFirstName.setText("");
                txtLastName.setText("");
                txtSSN.setText("");
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
                if(txtSSN.getText().length() != 5){
                    JOptionPane.showMessageDialog(ref,"Longitud del numero de modelo incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean ccap = false, cweight = false;
                try{

                    int cap = Integer.parseInt(txtFirstName.getText());
                    ccap = true;
                    int weight = Integer.parseInt(txtLastName.getText());
                    cweight = true;

                    Model m = new Model(txtSSN.getText(), cap, weight);

                    //cosas de DAO y sql
                    JOptionPane.showMessageDialog(ref, "Operacion exitosa", "Agregado", JOptionPane.INFORMATION_MESSAGE);
                    dao.agregarUniversal(m);

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
    public Employee verificar(){
        String ssn = txtSSN.getText();
        if(ssn.length() != 20){
            JOptionPane.showMessageDialog(ref, "El SSN debe ser de 20 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtFirstName.getText().length() > 30){
            JOptionPane.showMessageDialog(ref, "El primer nombre no debe exceder 30 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtLastName.getText().length() > 30){
            JOptionPane.showMessageDialog(ref, "El segundo nombre no debe exceder 30 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtAddressStreet.getText().length() > 30){
            JOptionPane.showMessageDialog(ref, "La calle no debe exceder 30 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtAddressCity.getText().length() > 30){
            JOptionPane.showMessageDialog(ref, "La ciudad no debe exceder 30 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtAddressPostal.getText().length() > 10){
            JOptionPane.showMessageDialog(ref, "El codigo postal no debe exceder 10 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtPhoneNumber.getText().length() > 10){
            JOptionPane.showMessageDialog(ref, "El numero telefonico no debe exceder 10 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int um = -1;
        try{
            um = Integer.parseInt(txtUnionMembership.getText());
            if(um < 0) Integer.parseInt("L");
        }catch (NumberFormatException unionE){
            JOptionPane.showMessageDialog(ref, "La membresia de sindicato debe ser un entero positivo", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtSalary.getText().length() > 10){
            JOptionPane.showMessageDialog(ref, "El salario no debe exceder 10 digitos", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }else if(txtSalary.getText().indexOf('.') != 8){
            JOptionPane.showMessageDialog(ref, "El salario debe tener 2 decimales", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(txtCharge.getText().length() > 30){
            JOptionPane.showMessageDialog(ref, "El cargo no puede exceder 30 caracteres", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }
}
