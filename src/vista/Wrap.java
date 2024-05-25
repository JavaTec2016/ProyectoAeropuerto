package vista;

import javax.swing.*;

public class Wrap {
    JComponent componente;
    public int xFrom = 0;
    public int yFrom = 0;

    public int xFinal = 0;
    public int yFinal = 0;

    public int xOffset = 0;
    public int yOffset = 0;

    public int centerX = 0;
    public int centerY = 0;

    int xLatchDiff = 0;
    int yLatchDiff = 0;

    public int widthFrom = 0;
    public int heightFrom = 0;

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
    public void calcularCoordenadas(JComponent j){
        int x = (int) (j.getWidth()*xRelative + xLatchDiff);
        int y = (int) (j.getHeight()*yRelative + yLatchDiff);
        xFinal = x;
        yFinal = y;
    }
    public void posicionarRelativo(JFrame j){
        calcularCoordenadas(j);
        componente.setBounds(xFinal-xOffset, yFinal-yOffset, componente.getWidth(), componente.getHeight());
    }
    public void posicionarRelativo(JComponent j){
        calcularCoordenadas(j);
        componente.setBounds(xFinal-xOffset, yFinal-yOffset, componente.getWidth(), componente.getHeight());
    }
    public void dimensionarRelativo(JFrame j){
        int w = (int) (j.getWidth()*widthRelative);
        int h = (int) (j.getHeight()*heightRelative);
        componente.setSize(w, h);
    }
    public void dimensionarRelativo(JComponent j){
        int w = (int) (j.getWidth()*widthRelative);
        int h = (int) (j.getHeight()*heightRelative);
        componente.setSize(w, h);
    }
    public void centerOffset(int x, int y){

        if(x==1) xOffset = componente.getWidth()/2;
        if(y==1) yOffset = componente.getHeight()/2;
        centerX = x;
        centerY = y;
    }
    public Wrap chainCenterOffset(int x, int y){
        if(x==1) xOffset = componente.getWidth()/2;
        if(y==1) yOffset = componente.getHeight()/2;
        centerX = x;
        centerY = y;
        return this;
    }

}
