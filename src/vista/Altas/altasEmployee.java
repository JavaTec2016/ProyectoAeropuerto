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

                int codigo = dao.agregarUniversal(emp);
                notificarSQL(codigo, "Operacion exitosa", "Registro duplicado", "Error de relacion", "Registro agregado");
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
