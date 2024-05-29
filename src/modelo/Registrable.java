package modelo;

public interface Registrable {
    public String[] tipoDatos();

    public String[] propiedades();

    public Object[] obtenerValores();

    public static String[] obtenerLabels(){return null;}
    public static String[] obtenerComponentes(){return null;}
    public static String[] obtenerTipoDato(){return null;}
    public static boolean[] obtenerNoNulos(){return null;}
    public boolean[] noNulos();
    public static int[] obtenerLongitudes(){return null;}
}
