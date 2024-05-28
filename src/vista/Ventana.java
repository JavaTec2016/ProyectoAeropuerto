package vista;

import controlador.DAO;
import modelo.Employee;
import modelo.ModeloBD;
import modelo.Registrable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import static controlador.DAO.d;

public class Ventana extends JInternalFrame{
    public InternalRasLayout ras;
    public ArrayList<Wrap> salida;
    public String title;
    public int w;
    public int h;
    public int celh = 1;
    public int celw = 1;

    public String tipo;

    public DAO dao = d;

    public JComponent[] inputs;

    public JButton btnCancelar;
    public JButton btnValidar;
    public JButton btnLimpiar;
    public Ventana ref = this;
    public void autoGenerar(String label, int panelHeight, String[] lbls, String[] cps, String[] tipos, int[] lgs, boolean[] noNulos){

        Panelo panelAgregar = new Panelo();
        lblFont lblAgregar = new lblFont(label, "Arial", Font.BOLD, 30, 0,0,0);
        panelAgregar = new Panelo();
        panelAgregar.w = w;
        panelAgregar.h = h/10;
        panelAgregar.x = 0;
        panelAgregar.y = 0;
        panelAgregar.ras = new PanelRasLayout(panelAgregar, 0, 0, panelAgregar.w, panelAgregar.h);
        panelAgregar.salida = new ArrayList<Wrap>();
        panelAgregar.setBackground(new Color(184, 251, 184));

        System.out.println("obtenidos " + lbls.length + " labels");
        System.out.println("obtenidos " + cps.length + " componentes");

        inputs = new JComponent[cps.length];
        int i = 0,  yi = (int)(celh/h*0.5), xi = 4;
        int lblWidth = 13, lblHeight = 2;
        int txtWidth = 9, txtHeight = 2;

        int totalHeight = lblHeight*lbls.length; //celdas totales abarcadas por el formulario

        System.out.println("altura de formulario " + totalHeight + "\naltura de pantalla"+ (h/celh));
        int halfDiff = (h/celh - totalHeight)/2/celh + (panelHeight/celh/2); //margen para centrar el formulario

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
        for(String lbl : lbls){
            lblFont f = new lblFont("Ingrese el " + lbl, "Arial", Font.PLAIN, 15,0,0, 0);
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
    }
    public String[] recibirInputs(String[] tipos, boolean[] noNulos, int[]lgs, String[] lbls){
        int j = 0;
        String[] inps = new String[inputs.length];
        for(JComponent input : inputs){
            String in = extraerInput(input);
            if(validarInput(in, tipos[j], noNulos[j], lgs[j], lbls[j]) != 0) return null;
            inps[j] = in;
            j++;
        }
        return  inps;
    }
    public JComponent identificarComponente(String nombre){
        if(nombre.equals("JTextField")) return new JTextField(10);
        if(nombre.equals("JComboBox")) return new JComboBox<String>();
        System.out.println("Componente no identificado: " + nombre);
        return null;
    }
    public String extraerInput(JComponent comp){
        if(comp instanceof JTextField) return ((JTextField)comp).getText();
        if(comp instanceof JComboBox) return ((JComboBox)comp).getSelectedItem().toString();
        System.out.println("extraerInput desconocido");
        return null;
    }
    //al extraer el input se debe validar
    public byte validarInput(String input, String tipoDato, boolean noNulo, int limite, String label){

        ///VALIDAR LA EXISTENCIA DEL INPUT

        if(noNulo && input.isBlank()){
            JOptionPane.showMessageDialog(this, label + " no debe ser nulo", "Error de datos", JOptionPane.ERROR_MESSAGE);
            return 101;
        }
        if(input.length() > limite && tipoDato.equals("VARCHAR")){
            JOptionPane.showMessageDialog(this, label + " no debe exceder " + limite + "caracteres", "Error de datos", JOptionPane.ERROR_MESSAGE);
            return 102;
        }
        if(input.length() != limite && tipoDato.equals("CHAR")){
            JOptionPane.showMessageDialog(this, label + " debe tener " + limite + "caracteres", "Error de datos", JOptionPane.ERROR_MESSAGE);
            return 103;
        }

        //VALIDAR EL FORMATO DEL INPUT

        try{
            if(tipoDato.equals("int")){
                Integer.parseInt(input);
            }
            else if(tipoDato.equals("short")){
                Short.parseShort(input);
            }
            else if(tipoDato.equals("double")){
                Double.parseDouble(input);
            }
        }catch (NumberFormatException inte){
            JOptionPane.showMessageDialog(this, label + " debe ser numerico", "Error de datos", JOptionPane.ERROR_MESSAGE);
            return 104;
        }
        if(tipoDato.equals("DATE")){
            String[] partido = input.split("/");
            if(partido.length != 3){
                System.out.println("date = "+partido[0] + ", " + partido.length);
                JOptionPane.showMessageDialog(this, label + " debe ser una fecha numerica", "Error de datos", JOptionPane.ERROR_MESSAGE);
                return 104;
            }
            if(partido[0].length() == 4 && partido[1].length() == 2 && partido[2].length() == 2){
                try {
                    for(String fecha : partido){
                        System.out.println(fecha);
                        Integer.parseInt(fecha);
                    }
                }catch (NumberFormatException fechae){
                    JOptionPane.showMessageDialog(this, label + " debe ser una fecha numerica", "Error de datos", JOptionPane.ERROR_MESSAGE);
                    return 104;
                }

            }else{
                JOptionPane.showMessageDialog(this, label + " debe tener formato yyyy/mm/dd", "Error de datos", JOptionPane.ERROR_MESSAGE);
                return 105;
            }
        }
        return 0;//el input puede existir en su respectivo campo SQL
    }
}
