package daw;

import java.util.InputMismatchException;
import java.util.Random;
import javax.swing.JOptionPane;

public class JuegoDedos {

    public static void main(String[] args) {
        
        //Implementa un programa para jugar contra la máquina al juego de de dedos
        int opcion = 0;
        String jugador1 = "";
        String jugador2 = "";
        JOptionPane.showMessageDialog(null, "-BIENVENIDO AL JUEGO DE LOS DEDOS "
                + "CHINOS-");
        String opcionS;
        opcionS = showMenu();
       
        do {
            
            if (opcionS == null) {
                opcionS = JOptionPane.showInputDialog("Por favor, introduce una opcion valida");
            }

            try {
                opcion = Integer.parseInt(opcionS);
                switch (opcion) {
                    case 1 -> {
                        mostrarInstrucciones();
                        opcionS = showMenu();
                    }

                    case 2 -> {
                        jugarDedosChinos(0, 0, 0, 0);
                        opcionS = showMenu();
                    }

                    case 3 -> {
                        salidaPantalla();
                    }

                    default ->
                        opcionS = JOptionPane.showInputDialog(null, "Por favor introduzca una "
                                + "opción válida");
                }
            } catch (NumberFormatException nfe) {
                opcionS = JOptionPane.showInputDialog(null, "Por favor, introduce un "
                        + "numero valido");
                   
            }
        } while (opcion != 3);
    }
    
    public static String showMenu () {
         String menu = """
                      Sabes las instrucciones?
                      En caso de no saberlas introduca 1 (no).
                      Si ya las sabes introduzca una 2 (si) para proceder a jugar
                      Si deseas salir, introduce 3
                      """;
        String entrada;
        
        entrada =  JOptionPane.showInputDialog(menu);
        
        return entrada;
    }

    public static void mostrarInstrucciones() {
        JOptionPane.showMessageDialog(null, "Has elegido ver las instrucciones");
        JOptionPane.showMessageDialog(null, """
            Los dos jugadores esconden un puño detrás de la espalda. Después cada 
            jugador a la vez dice el número de dedos que cree que habrán extendidos 
            entre las dos manos y simultáneamente muestran las manos.
            En nuestro caso la máquina pensará los dedos a sacar y los dedos que se
            sacarán entre los dos jugadores.
            La puntuación mínima obtenible es 2, ya que no existe el cero (el puño 
            cerrado vale 1).
            El jugador que haya acertado gana. Si ninguno lo ha acertado, se vuelve 
            a empezar.
            Gana el que consigue una ventaja de dos victorias sobre el otro jugador.
            Si hay empate después de las 4 rondas, se juega hasta que se consigue la
            ventaja de dos rondas.
            """);
    }

    public static void salidaPantalla() {
        JOptionPane.showMessageDialog(null, "Has decidido salir. Hasta pronto!");
    }

    public static void jugarDedosChinos(int dedosUsuario1, int dedosUsuario2,
            int dedosUsuario1Total, int dedosUsuario2Total) {
        JOptionPane.showMessageDialog(null, "Es hora de jugar dedos chinos!");

        Random rd = new Random();
        // Se le da el valor 0 para que esta pueda ser inicializada en el sumatorio
        // puntuacionJG1++ y puntuacionJG2++
        int puntuacionJG1 = 0;
        int puntuacionJG2 = 0;

        String jugador1 = JOptionPane.showInputDialog(null, "Introduzca el nombre"
                + " del primer jugador");
        String jugador2 = JOptionPane.showInputDialog(null, "Introduzca el nombre"
                + " del segundo jugador");

        dedosMaquina(jugador1, jugador2, dedosUsuario1Total, dedosUsuario2Total);

        do {
            try {
                dedosUsuario1 = dedosSonValidos(jugador1 + " introduzca los dedos que vas"
                        + " a sacar");
                dedosUsuario2 = dedosSonValidos(jugador2 + " introduzca los dedos que vas"
                        + " a sacar");
                dedosUsuario1Total = dedosSonValidosTotal(jugador1 + ", introduce el "
                        + "numero de dedos que cree que se va a sacar entre los 2");
                dedosUsuario2Total = dedosSonValidosTotal(jugador2 + ", introduce los "
                        + "dedos que cree que va a sacar el");
            } catch (InputMismatchException ime) {
                JOptionPane.showInputDialog(null, "Introduzca un numero valido");
            }
        } while (((dedosUsuario1 <= 1 || dedosUsuario1 >= 6 || dedosUsuario2 <= 1
                || dedosUsuario2 >= 6) && dedosUsuario1Total != dedosUsuario2Total));

        int dedosTotal = dedosUsuario1 + dedosUsuario2;

        if (dedosUsuario1Total == dedosTotal) {
            puntuacionJG1++;
            JOptionPane.showMessageDialog(null, jugador1 + " felicidades!, has"
                    + " ganado 1 punto");
        } else if (dedosUsuario2Total == dedosTotal) {
            do {
                puntuacionJG2++;
                JOptionPane.showMessageDialog(null, jugador2 + " felicidades!, has"
                        + " ganado 1 punto");
            } while (dedosUsuario2Total != dedosUsuario1Total);
            JOptionPane.showInputDialog(null, "No puedes poner el mismo "
                    + "numero que el jugador 1");

        } else {
            JOptionPane.showMessageDialog(null, """
                                                Ninguno de los dos jugadores ha
                                                sacado el numero total de dedos.
                                                Nadie gana puntos.
                                                """);
        }

        if (puntuacionJG1 - puntuacionJG2 >= 2) {
            JOptionPane.showMessageDialog(null, "Felicidades!! " + jugador1
                    + " , has ganado");
        } else if (puntuacionJG2 - puntuacionJG1 >= 2) {
            JOptionPane.showMessageDialog(null, "Felicidades!! " + jugador2
                    + " , has ganado");
        }
    }

    // METODO PARA COMPROBAR QUE LOS DEDOS(STRING) SON VALIDOS (VALIDAR CON INT)
    public static int dedosSonValidosTotal(String dedosS) {
        boolean valido = false;
        int dedos;

        do {
            dedos = Integer.parseInt(dedosS);
            if (dedos >= 1 && dedos <= 10) {
                valido = true;
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que introducir"
                        + " el numero de dedos entre 1 y 5");
                valido = false;
                break;
            }
        } while (!(valido));
        return dedos;
    }

    public static int dedosSonValidos(String texto) {
        boolean valido = false;
        int dedos;
        do {
            String dedosS = JOptionPane.showInputDialog(null, texto);
            dedos = Integer.parseInt(dedosS);
            if (dedos >= 1 && dedos <= 5) {
                valido = true;
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que introducir"
                        + " el numero de dedos entre 1 y 5");
                valido = false;
                break;
            }
        } while (!(valido));

        return dedos;
    }

    public static void dedosMaquina(String jugador1, String jugador2,
            int dedosUsuario1, int dedosUsuario2) {

        Random rd = new Random();
        int dedosMaquinaUsuario1 = rd.nextInt(1, 6);
        int dedosMaquinaUsuario2 = rd.nextInt(1, 6);
        int sumaDedos = dedosMaquinaUsuario1 + dedosMaquinaUsuario2;

        JOptionPane.showMessageDialog(null, "El robot piensa que el " + jugador1
                + " va a sacar " + dedosMaquinaUsuario1 + " dedos");
        JOptionPane.showMessageDialog(null, "El robot piensa que el " + jugador2
                + " va a sacar " + dedosMaquinaUsuario2 + " dedos");

        JOptionPane.showMessageDialog(null, "La maquina piensa que entre los dos"
                + " se van a sacar " + sumaDedos + " dedos");
    }

}
