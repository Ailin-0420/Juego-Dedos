
package daw;

import javax.swing.JOptionPane;

public class NewClass {
    
    public static void main(String[] args) {
        
        String opcion ="";
        JOptionPane.showMessageDialog(null, "-BIENVENIDO AL JUEGO DE LOS DEDOS "
                + "CHINOS-");
        opcion = JOptionPane.showInputDialog(null, "Sabes las instrucciones?\nEn "
                + "caso de no saberlas introduca N (no).\nSi ya las sabes introduzca"
                + " una S (si) para proceder a jugar");
        if (opcion == null  || !opcion.equalsIgnoreCase("S") ||  !opcion.equalsIgnoreCase("N")) {
            JOptionPane.showInputDialog(null, """
                                              Por favor
                                              , introduce una S si sabe las instrucciones del juego o
                                              una N si desconoce las instrucciones""");
        }
        String jugador1 = "";
        String jugador2 = "";
        switch (opcion) {
            case "S" -> {
                JuegoDedos.jugarDedosChinos(0, 0, 0, 0);
            }
            case "N" -> {
                JuegoDedos.mostrarInstrucciones();
            }
            default -> JOptionPane.showInputDialog(null, "Por favor introduzca una "
                        + "opción válida");
        }
    }
}
