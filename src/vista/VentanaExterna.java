package vista;

import controlador.DAO;
import modelo.ModeloBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import static controlador.DAO.d;

public class VentanaExterna extends JFrame {
    public RasLayout ras;
    public ArrayList<Wrap> salida;
    public String title;
    public int w;
    public int h;
    public int celh = 1;
    public int celw = 1;

    //public String tipo;

    public DAO dao = d;

    public JComponent[] inputs;

    public JButton btnCancelar;
    public JButton btnValidar;
    public JButton btnLimpiar;
    public VentanaExterna ref = this;
    protected Panelo panel;
    protected String btnAccion = "-";
    protected String objetivo;

    protected String[] lbls;
    protected String[] cps;

    protected String[] tipos;
    protected int[] lgs;
    protected boolean[] nnl;

    public void autoGenerar(String objetivo, String label, int panelHeight, int separation, int yp, int lblMaxWidth){
        this.objetivo = objetivo;
        ras = new RasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        panel = new Panelo();
        lblFont lblPanel = new lblFont(label, "Arial", Font.BOLD, 30, 0,0,0);
        lblPanel.setHorizontalAlignment(SwingConstants.CENTER);
        lblPanel.setVerticalAlignment(SwingConstants.CENTER);
        panel = new Panelo();
        panel.w = w;
        panel.h = panelHeight;
        panel.x = 0;
        panel.y = 0;
        panel.ras = new PanelRasLayout(panel, 0, 0, panel.w, panel.h);
        panel.salida = new ArrayList<Wrap>();
        panel.setBackground(new Color(184, 251, 184));

        System.out.println("obtenidos " + lbls.length + " labels");
        System.out.println("obtenidos " + cps.length + " componentes");

        inputs = new JComponent[cps.length];
        int i = 0,  yi = (int)(celh/h*0.5), xi = 4;
        int lblWidth = 13, lblHeight = 2;
        int txtWidth = 9, txtHeight = 2;
        int nextHeight = txtHeight+separation;

        int totalHeight = nextHeight*lbls.length; //celdas totales abarcadas por el formulario

        System.out.println("altura de formulario " + totalHeight + "\naltura de pantalla"+ (h/celh));
        int halfDiff = (h/celh - totalHeight)/2/celh + (panelHeight/celh/2)+yp; //margen para centrar el formulario

        System.out.println("margen vertical de " + halfDiff + "celdas");

        for(String cmp : cps){
            inputs[i] = identificarComponente(cmp);
            //caso especial
            if(inputs[i] instanceof JComboBox){
                ((JComboBox<String>) inputs[i]).addItem("Technician");
                ((JComboBox<String>) inputs[i]).addItem("Traffic Controller");
            }
            //agregar al ras
            salida.add(ras.encuadrarRelativo(inputs[i], xi+lblMaxWidth+2, halfDiff+nextHeight*(i+1), txtWidth, txtHeight));
            i++;
        }

        i = 0;
        for(String lbl : lbls){
            lblFont f = new lblFont("Ingrese el " + lbl, "Arial", Font.PLAIN, 15,0,0, 0);
            salida.add(ras.encuadrarRelativo(f, xi, halfDiff+nextHeight*(i+1), lblWidth, lblHeight));
            i++;
        }
        Wrap wPanel = new Wrap(panel);
        Wrap wLblAgregar = new Wrap(lblPanel);

        btnCancelar = new JButton("CANCELAR");
        btnLimpiar = new JButton("LIMPIAR");
        btnValidar = new JButton(btnAccion);

        ras.agregarRelativo(wPanel, panel.x, panel.y, panel.w, panel.h);
        int restheight = h-panelHeight;
        Wrap wBtnLimpiar = ras.encuadrarRelativo(btnLimpiar, xi+lblMaxWidth+19, (int)(panelHeight/celh + restheight/celh*0.2), 10,2);

        Wrap wBtnValidar = ras.encuadrarRelativo(btnValidar, xi+lblMaxWidth+19, (int)(panelHeight/celh + restheight/celh*0.45), 12, 2);
        Wrap wBtnCancelar = ras.encuadrarRelativo(btnCancelar, xi+lblMaxWidth+19, (int)(panelHeight/celh + restheight/celh*0.7), 12,2);


        wBtnValidar.centerOffset(1,1);
        wBtnCancelar.centerOffset(1,1);
        wBtnLimpiar.centerOffset(1,1);

        panel.ras.agregarRelativo(wLblAgregar, panel.w/2, panel.h/2, panel.w, panel.h);
        lblPanel.setVerticalAlignment(SwingConstants.CENTER);
        lblPanel.setHorizontalAlignment(SwingConstants.CENTER);
        wLblAgregar.centerOffset(1,1);
        wLblAgregar.posicionarRelativo(panel);
        panel.salida.add(wLblAgregar);

        salida.add(wPanel);
        salida.add(wBtnValidar);
        salida.add(wBtnCancelar);
        salida.add(wBtnLimpiar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

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
                ref.dispose();

            }
        });

    }
    protected void activarBotonValidar(String mensajeExito, String mensajeDupe, String mensajeRelacion, String tipo){
        //objeto de utilidad


        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int j = 0;
                Object[] inps = new Object[inputs.length];
                for(JComponent input : inputs){
                    String in = extraerInput(input);

                    if(validarInput(in, tipos[j], nnl[j], lgs[j], lbls[j]) != 0) return;

                    inps[j] = extraerInput(input, tipos[j]);
                    j++;
                }

                int codigo = dao.agregarUniversal(ModeloBD.instanciar(inps, objetivo));
                notificarSQL(codigo, mensajeExito, mensajeDupe, mensajeRelacion, tipo);
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
    public Object extraerInput(JComponent comp, String tipo){
        Object res = "";
        if(comp instanceof JTextField) res = ((JTextField)comp).getText();
        if(comp instanceof JComboBox) res = ((JComboBox)comp).getSelectedItem().toString();

        if(tipo.equals("CHAR") || tipo.equals("VARCHAR") || tipo.equals("DATE")){
           res = res.toString();
        }else if (tipo.equals("DECIMAL")){
            res = Double.parseDouble(res.toString());
        }else if(tipo.equals("SMALLINT")){
            res = Short.parseShort(res.toString());
        }else{
            res = Integer.parseInt(res.toString());
        }
        return res;
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
    public void notificarSQL(int codigo, String mensajeExito, String mensajeDupe, String mensajeRelacion, String tipo){
        switch (codigo){
            case 0:
                JOptionPane.showMessageDialog(ref, mensajeExito, tipo, JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1062:
                JOptionPane.showMessageDialog(ref, mensajeDupe, "Error de duplicacion", JOptionPane.ERROR_MESSAGE);
                break;
            case 1451:
                JOptionPane.showMessageDialog(ref, mensajeRelacion, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 1452:
                JOptionPane.showMessageDialog(ref, mensajeRelacion, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(ref, "Error de instruccion ("+codigo+")", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        System.out.println("Codigo: " + codigo);
    }
}
