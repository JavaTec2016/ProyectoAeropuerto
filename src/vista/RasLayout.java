package vista;

import javax.swing.*;
import java.util.ArrayList;


public class RasLayout {
    JFrame j;
    int cw = 1;
    int ch = 1;

    public RasLayout(JFrame J){
        j = J;
        j.getContentPane().setLayout(null);
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
        j.setTitle("Ventana");
        j.setSize(470, 800);
        j.setLocationRelativeTo(null);
        j.setVisible(true);

    }
    public RasLayout(JFrame J, String nom, int w, int h){
        j = J;
        j.getContentPane().setLayout(null);
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
        j.setTitle(nom);
        j.setSize(w, h);
        j.setLocationRelativeTo(null);
        j.setVisible(true);

    }
    //frame falso que imita al componente
    public RasLayout(JComponent cas){
        JFrame J = new JFrame();
        J.setSize(cas.getSize());
        J.setLocation(cas.getLocation());

        j = J;

    }
    public void setVisible(boolean vis){
        j.setVisible(vis);
    }
    public double getRatio(double position, char coord){
        if(coord == 'x') return position/j.getWidth();
        return position/j.getHeight();
    }
    public void agregar(Wrap wrap, int x, int y, int width, int height){
        wrap.componente.setBounds(x,y,width,height);
        wrap.xFrom = x; wrap.yFrom = y;
        wrap.widthFrom = width; wrap.heightFrom = height;
        j.add(wrap.componente);
    }
    public void agregarRelativo(Wrap wrap, int x, int y, int width, int height){
        wrap.componente.setBounds(x,y,width,height);

        wrap.xFrom = x; wrap.yFrom = y;
        wrap.widthFrom = width; wrap.heightFrom = height;
        wrap.xRelative = getRatio(x, 'x');
        wrap.yRelative = getRatio(y, 'y');
        wrap.widthRelative = getRatio(width, 'x');
        wrap.heightRelative = getRatio(height, 'y');

        j.add(wrap.componente);
    }
    public void prepararRelativo(Wrap wrap, int x, int y, int width, int height){
        wrap.componente.setBounds(x,y,width,height);

        wrap.xFrom = x; wrap.yFrom = y;
        wrap.widthFrom = width; wrap.heightFrom = height;
        wrap.xRelative = getRatio(x, 'x');
        wrap.yRelative = getRatio(y, 'y');
        wrap.widthRelative = getRatio(width, 'x');
        wrap.heightRelative = getRatio(height, 'y');
    }
    public Wrap encuadrarRelativo(JComponent comp, int x, int y, double w, double h){
        Wrap res = new Wrap(comp);
        agregarRelativo(res, x*cw, y*ch, (int) (w*cw), (int) (h*ch));
        //res.resize = false;
        return res;
    }

    public void posicionar(Wrap wrap, int x, int y){
        wrap.componente.setLocation(x, y);
    }
    public void dimensionar(Wrap wrap, int w, int h){
        wrap.componente.setSize(w, h);
    }


    public void actualizarRelativo(Wrap wrap){
        wrap.posicionarRelativo(j);
        if(wrap.resize) wrap.dimensionarRelativo(j);
    }
    //en vez de usar otra layout para componentes, haz que se peguen a el
    public void offset(Wrap wrap, int x, int y){

        wrap.xLatchDiff = x;
        wrap.yLatchDiff = y;
    }
    public static void refrescar(ArrayList<Wrap> w, RasLayout r){
        for(Wrap parte : w){
            r.actualizarRelativo(parte);
            //if(n.equals("JLabel") || n.equals("JTextField"));
        }
    }
}
