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

    public JComponent[] inputs;

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
            String[] partido = tipoDato.split("/");
            if(partido[0].length() == 4 && partido[1].length() == 2 && partido[2].length() == 2){
                try {
                    for(String fecha : partido){
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
