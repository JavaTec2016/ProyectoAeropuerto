package vista;

import javax.swing.*;

public class Wrap {
    JComponent componente;
    int xFrom = 0;
    int yFrom = 0;

    int xFinal = 0;
    int yFinal = 0;

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
    public void calcularCoordenadas(JFrame j){
        int x = (int) (j.getWidth()*xRelative + xLatchDiff);
        int y = (int) (j.getHeight()*yRelative + yLatchDiff);
        xFinal = x;
        yFinal = y;
    }
    public void posicionarRelativo(JFrame j){
        calcularCoordenadas(j);
        componente.setBounds(xFinal-xOffset, yFinal-yOffset, componente.getWidth(), componente.getHeight());
    }
    public void dimensionarRelativo(JFrame j){
        int w = (int) (j.getWidth()*widthRelative);
        int h = (int) (j.getHeight()*heightRelative);
        componente.setSize(w, h);
    }
    public void centerOffset(int x, int y){
        if(x==1) xOffset = componente.getWidth()/2;
        if(y==1) yOffset = componente.getHeight()/2;
    }
    public Wrap chainCenterOffset(int x, int y){
        if(x==1) xOffset = componente.getWidth()/2;
        if(y==1) yOffset = componente.getHeight()/2;
        return this;
    }

}
