package vista.Altas;

import modelo.Employee;
import modelo.Model;
import modelo.ModeloBD;
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
        w = 900;
        h = 800;
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
        panelAgregar.h = h/10;
        panelAgregar.x = 0;
        panelAgregar.y = 0;
        panelAgregar.ras = new PanelRasLayout(panelAgregar, 0, 0, panelAgregar.w, panelAgregar.h);
        panelAgregar.salida = new ArrayList<Wrap>();
        panelAgregar.setBackground(new Color(184, 251, 184));

        //generacion automatica del formulario

        String[] lbls = Employee.obtenerLabels();
        String[] cps = Employee.obtenerComponentes();

        String[] tipos = Employee.obtenerTipoDato();
        int[] lgs = Employee.obtenerLongitudes();
        boolean[] noNulos = Employee.obtenerNoNulos();

        System.out.println("obtenidos " + lbls.length + " labels");
        System.out.println("obtenidos " + cps.length + " componentes");

        inputs = new JComponent[cps.length];
        int i = 0,  yi = (int)(celh/h*0.5), xi = 4;
        int lblWidth = 13, lblHeight = 2;
        int txtWidth = 9, txtHeight = 2;

        int totalHeight = lblHeight*lbls.length; //celdas totales abarcadas por el formulario

        System.out.println("altura de formulario " + totalHeight + "\naltura de pantalla"+ (h/celh));
        int halfDiff = (h/celh - totalHeight)/2/celh + (panelAgregar.h/celh/2); //margen para centrar el formulario

        System.out.println("margen vertical de " + halfDiff + "celdas");

        for(String cmp : cps){
            inputs[i] = identificarComponente(cmp);
            //caso especial
            if(inputs[i] instanceof JComboBox){
                ((JComboBox<String>) inputs[i]).addItem("Technician");
                ((JComboBox<String>) inputs[i]).addItem("Traffic Controller");
            }
            //agregar al ras
            salida.add(ras.encuadrarRelativo(inputs[i], xi+lblWidth+2, halfDiff+(txtHeight+1)*(i+1), txtWidth, txtHeight));
            i++;
        }

        i = 0;
        for(String label : lbls){
            lblFont f = new lblFont("Ingrese el " + label, "Arial", Font.PLAIN, 15,0,0, 0);
            salida.add(ras.encuadrarRelativo(f, xi, halfDiff+(txtHeight+1)*(i+1), lblWidth, lblHeight));
            i++;
        }
        Wrap wPanel = new Wrap(panelAgregar);
        Wrap wLblAgregar = new Wrap(lblAgregar);

        btnCancelar = new JButton("CANCELAR");
        btnLimpiar = new JButton("LIMPIAR");
        btnValidar = new JButton("AGREGAR");
        /**
         * txtSSN = new JTextField(10);
         *         txtFirstName = new JTextField(10);
         *         txtLastName = new JTextField(10);
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *         int xi = 5, yi = 8;
         *
         *         Wrap wLblSSN = ras.encuadrarRelativo(new lblFont("SSN", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi, 8, 2);
         *         Wrap wtxtSSN = ras.encuadrarRelativo(txtSSN, xi+8+2, yi, 12, 2);
         *         Wrap wLblFirstName = ras.encuadrarRelativo(new lblFont("Primer Nombre", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+3, 8, 2);
         *         Wrap wtxtFirstName = ras.encuadrarRelativo(txtFirstName, xi+8+2, yi+3, 12, 2);
         *         Wrap wLblLastName = ras.encuadrarRelativo(new lblFont("Ultimo Nombre", "Arial", Font.PLAIN, 15, 0,0,0), xi, yi+6, 8, 2);
         *         Wrap wtxtLastName = ras.encuadrarRelativo(txtLastName, xi+8+2, yi+6, 12, 2);
         */


        ras.agregarRelativo(wPanel, panelAgregar.x, panelAgregar.y, panelAgregar.w, panelAgregar.h);
        Wrap wBtnLimpiar = ras.encuadrarRelativo(btnLimpiar, xi+27, (int) (halfDiff+(txtHeight+1)*(i+1)*0.25), 10,2);

        Wrap wBtnValidar = ras.encuadrarRelativo(btnValidar, xi+26, (int)(halfDiff+(txtHeight+1)*(i+1)*0.5), 12, 2);
        Wrap wBtnCancelar = ras.encuadrarRelativo(btnCancelar, xi+26, (int)(halfDiff+(txtHeight+1)*(i+1)*0.75), 12,2);


        wBtnValidar.centerOffset(1,1);
        wBtnCancelar.centerOffset(1,1);
        wBtnLimpiar.centerOffset(1,1);

        panelAgregar.ras.agregarRelativo(wLblAgregar, panelAgregar.w/2, panelAgregar.h/2, 400, 30);
        wLblAgregar.centerOffset(1,1);
        wLblAgregar.posicionarRelativo(panelAgregar);
        panelAgregar.salida.add(wLblAgregar);
        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *         salida.add(wLblSSN);
         *         salida.add(wtxtSSN);
         *         salida.add(wLblFirstName);
         *         salida.add(wtxtFirstName);
         *         salida.add(wLblLastName);
         *         salida.add(wtxtLastName);
         */
        salida.add(wPanel);

        salida.add(wBtnValidar);
        salida.add(wBtnCancelar);
        salida.add(wBtnLimpiar);


        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JComponent input : inputs) {
                    if(input instanceof JComboBox) ((JComboBox<String>)input).setSelectedIndex(0);
                    else ((JTextField)input).setText("");
                }
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
                int j = 0;
                String[] inps = new String[inputs.length];
                for(JComponent input : inputs){
                    String in = extraerInput(input);
                    if(validarInput(in, tipos[j], noNulos[j], lgs[j], lbls[j]) != 0) return;
                    inps[j] = in;
                    j++;
                }

                Employee emp = new Employee(inps[0], inps[1], inps[2], inps[3], inps[4], inps[5], inps[6], Integer.parseInt(inps[7]), Double.parseDouble(inps[8]), inps[9]);

                dao.agregarUniversal(emp);
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
