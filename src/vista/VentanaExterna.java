package vista;

import controlador.ConsultaAvanzada;
import controlador.DAO;
import modelo.ModeloBD;
import modelo.Registrable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    public String objetivo;

    public String[] lbls;
    protected String[] cps;

    public String[] tipos;
    public int[] lgs;
    public boolean[] nnl;
    public String[] props;
    protected JTable ress;
    public DefaultTableModel model;
    protected JScrollPane scroller;
    protected Wrap wInput;
    protected int idx;
    public JComboBox<String> opciones;

    public boolean filtroCambio(){return true;}
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

        panel.ras.agregarRelativo(wLblAgregar, 0, 0, panel.w, panel.h);
        lblPanel.setVerticalAlignment(SwingConstants.CENTER);
        lblPanel.setHorizontalAlignment(SwingConstants.CENTER);
        //wLblAgregar.centerOffset(1,1);
        //ras.actualizarRelativo(wLblAgregar);

        //wLblAgregar.posicionarRelativo(panel);

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
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(panel.salida, panel.ras);
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
    public void generarConsulta(String objetivo, String label, int panelHeight, int separation, int yp, int lblMaxWidth){
        this.objetivo = objetivo;
        ras = new RasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(null, props);
        ress = new JTable(model);
        scroller = new JScrollPane(ress);

        Wrap wScroll = new Wrap(scroller);


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

        Wrap wPanel = new Wrap(panel);
        Wrap wLblAgregar = new Wrap(lblPanel);

        btnCancelar = new JButton("CANCELAR");
        btnLimpiar = new JButton("LIMPIAR");
        btnValidar = new JButton(btnAccion);

        ras.agregarRelativo(wPanel, panel.x, panel.y, panel.w, panel.h);
        int restheight = h-panelHeight;
        Wrap wBtnLimpiar = ras.encuadrarRelativo(btnLimpiar, 36+lblMaxWidth+19, (int)(panelHeight/celh + restheight/celh*0.2), 10,2);

        Wrap wBtnValidar = ras.encuadrarRelativo(btnValidar, 36+lblMaxWidth+19, (int)(panelHeight/celh + restheight/celh*0.45), 12, 2);
        Wrap wBtnCancelar = ras.encuadrarRelativo(btnCancelar, 36+lblMaxWidth+19, (int)(panelHeight/celh + restheight/celh*0.7), 12,2);


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

        opciones = new JComboBox<String>();
        for(String lbl : lbls){
            opciones.addItem(lbl);
        }
        salida.add(ras.encuadrarRelativo(opciones, 4, (panel.h/celh)+2, lblMaxWidth, 2));

        lblFont indicacion = new lblFont("Seleccione un atributo", "Arial", Font.BOLD, 12,0,0, 0);
        salida.add(ras.encuadrarRelativo(indicacion, 4, (panel.h/celh)+1, lblMaxWidth, 1));
        indicacion.setHorizontalAlignment(SwingConstants.CENTER);
        indicacion.setVerticalAlignment(SwingConstants.CENTER);
        //salida.get(salida.size()-1).centerOffset(1,0);

        int resheight = h/celh-((panel.h/celh)+1);
        //Wrap wScroll = new Wrap(scroller);
        scroller.setViewportView(ress);
        wScroll = ras.encuadrarRelativo(scroller, 4, (int)(h/celh*0.33), w/celw*0.6, h/celh*0.6);
        //salida.get(salida.size()-1).centerOffset(1,1);
        salida.add(wScroll);
        idx = opciones.getSelectedIndex();
        inputs[1] = identificarComponente(cps[idx]);
        wInput = ras.encuadrarRelativo(inputs[1], 4+opciones.getWidth()/celw+1, (panel.h/celh)+2, lblMaxWidth, 2);

        salida.add(wInput);
        //int inputIdx = getComponentCount()-1;
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                PanelRasLayout.refrescar(panel.salida, panel.ras);
            }
        });

        opciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("z: " + getContentPane().getComponentZOrder(wInput.componente));
                idx = opciones.getSelectedIndex();
                wInput.componente.setVisible(false);
                getContentPane().remove(wInput.componente);
                salida.remove(wInput);
                //int idx = opciones.getSelectedIndex();
                inputs[1] = identificarComponente(cps[idx]);
                System.out.println("tipo: " + cps[idx]);

                if(inputs[1] instanceof JComboBox<?>){
                    ((JComboBox)inputs[1]).addItem("Technician");
                    ((JComboBox)inputs[1]).addItem("Traffic Controller");
                }
                //wInput.componente = inputs[1];
                wInput = ras.encuadrarRelativo(inputs[1], 4+opciones.getWidth()/celw+1, (panel.h/celh)+2, lblMaxWidth, 2);
                salida.add(wInput);
                RasLayout.refrescar(salida, ras);
                //ras.actualizarRelativo(wInput);
            }
        });
        //btnLimpiar.removeActionListener(btnLimpiar.getActionListeners()[0]);
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarComponente(wInput.componente);
            }
        });
        inputs[0] = opciones;
        //realiza la consulta y muestra la tabla
        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ConsultaAvanzada ca = new ConsultaAvanzada(ref);
                ca.start();
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

                    if(validarInput(in, tipos[j], nnl[j], lgs[j], lbls[j], false) != 0) return;

                    inps[j] = extraerInput(input, tipos[j]);
                    j++;
                }

                int codigo = dao.agregarUniversal(ModeloBD.instanciar(inps, objetivo));
                notificarSQL(codigo, mensajeExito, mensajeDupe, mensajeRelacion, tipo);
            }
        });
    }
    public void limpiarComponente(JComponent c){
        if(c instanceof JTextField) ((JTextField)c).setText("");
    }
    protected void activarBotonModificar(String mensajeExito, String mensajeDupe, String mensajeRelacion, String tipo, int primarias){
        //objeto de utilidad


        btnValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(!filtroCambio()) return;
                int j = 0;
                //prepara los argumentos para el objeto
                Object[] inps = new Object[inputs.length];
                for(JComponent input : inputs){
                    String in = extraerInput(input);

                    if(validarInput(in, tipos[j], nnl[j], lgs[j], lbls[j], true) != 0){
                        System.out.println("lamomba");
                        return;
                    }
                    if(in.isBlank() || in.equalsIgnoreCase("NULL")) inps[j] = null;
                    else inps[j] = extraerInput(input, tipos[j]);
                    j++;
                }

                int codigo = dao.actualizarUniversal(ModeloBD.instanciar(inps, objetivo), primarias);
                notificarSQL(codigo, mensajeExito, mensajeDupe, mensajeRelacion, tipo);
            }
        });
    }
    public String[] recibirInputs(String[] tipos, boolean[] noNulos, int[]lgs, String[] lbls, boolean overrideNulo){
        int j = 0;
        String[] inps = new String[inputs.length];
        for(JComponent input : inputs){
            String in = extraerInput(input);
            if(validarInput(in, tipos[j], noNulos[j], lgs[j], lbls[j], overrideNulo) != 0) return null;
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
    public byte validarInput(String input, String tipoDato, boolean noNulo, int limite, String label, boolean overrideNulo){

        ///VALIDAR LA EXISTENCIA DEL INPUT
        if(overrideNulo) return 0;
        if((noNulo && input.isBlank() || input.equalsIgnoreCase("NULL"))){
            JOptionPane.showMessageDialog(this, label + " no debe ser nulo", "Error de datos", JOptionPane.ERROR_MESSAGE);
            return 101;
        }
        if((input.length() > limite && tipoDato.equals("VARCHAR"))){
            JOptionPane.showMessageDialog(this, label + " no debe exceder " + limite + "caracteres", "Error de datos", JOptionPane.ERROR_MESSAGE);
            return 102;
        }
        if((input.length() != limite && tipoDato.equals("CHAR"))){
            System.out.println(limite);
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
            case 1:
                JOptionPane.showMessageDialog(ref, "Sin cambios", tipo, JOptionPane.WARNING_MESSAGE);
                break;
            case 1062:
                JOptionPane.showMessageDialog(ref, mensajeDupe, "Error de duplicacion", JOptionPane.ERROR_MESSAGE);
                break;
            case 1048:
                //JOptionPane.showMessageDialog(ref, "mensajeRelacion", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 1451:
                JOptionPane.showMessageDialog(ref, mensajeRelacion, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case 1452:
                System.out.println("notificarSQL: " + codigo);
                JOptionPane.showMessageDialog(ref, mensajeRelacion, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(ref, "Error de instruccion ("+codigo+")", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        System.out.println("Codigo: " + codigo);
    }
}
