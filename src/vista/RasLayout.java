package vista;

import javax.swing.*;
import java.util.ArrayList;

class Wrap {
    JComponent componente;
    int xFrom = 0;
    int yFrom = 0;

    int xOffset = 0;
    int yOffset = 0;

    int xLatchDiff = 0;
    int yLatchDiff = 0;

    int widthFrom = 0;
    int heightFrom = 0;

    double xRelative = 0;
    double yRelative = 0;

    double widthRelative = 0;
    double heightRelative = 0;

    double ratioMulti = 1;

    boolean resize = true;

    public Wrap(JComponent c){
        componente = c;

    }

    public JComponent getComponente() {
        return componente;
    }
    public void reubicar(int x, int y){
        xFrom = x;
        yFrom = y;
    }
    public void redimensionar(int w, int h){
        widthFrom = w;
        heightFrom = h;
    }
    public Wrap nuevoRatio(double r){
        ratioMulti = r;
        return this;
    }
}
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
    public void centerOffset(Wrap w, byte x, byte y){
        if(x==1) w.xOffset = w.componente.getWidth()/2;
        if(y==1) w.yOffset = w.componente.getHeight()/2;
    }
    public void posicionar(Wrap wrap, int x, int y){
        wrap.componente.setLocation(x, y);
    }
    public void dimensionar(Wrap wrap, int w, int h){
        wrap.componente.setSize(w, h);
    }

    public void posicionarRelativo(Wrap w){
        int x = (int) (j.getWidth()*w.xRelative + w.xLatchDiff - w.xOffset);
        int y = (int) (j.getHeight()*w.yRelative + w.yLatchDiff - w.yOffset);
        w.componente.setBounds(x, y, w.componente.getWidth(), w.componente.getHeight());
    }
    public void dimensionarRelativo(Wrap wr){
        int w = (int) (j.getWidth()*wr.widthRelative);
        int h = (int) (j.getHeight()*wr.heightRelative);
        wr.componente.setSize(w, h);
    }

    public void actualizarRelativo(Wrap wrap){
        posicionarRelativo(wrap);
        if(wrap.resize) dimensionarRelativo(wrap);
    }
    //en vez de usar otra layout para componentes, haz que se peguen a el
    public void offset(Wrap wrap, int x, int y){

        wrap.xLatchDiff = x;
        wrap.yLatchDiff = y;
    }

    public static void refrescar(ArrayList<Wrap> w, RasLayout r){
        for(Wrap parte : w){
            String n = parte.getClass().getName();
            r.actualizarRelativo(parte);
            //if(n.equals("JLabel") || n.equals("JTextField"));
        }
    }
}
