package co.umariana.equipo;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Abre la ventana del equipo con las tarjetas de Integrantes.java.
 *
 * Esta clase YA está completa: no es necesario modificarla.
 * Para ejecutarla use el botón Run de VS Code, o  ./scripts/start.sh
 * (después de un git pull, ciérrela y ábrala de nuevo para ver los cambios).
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                List<Tarjeta> tarjetas = Integrantes.tarjetas();
                new VentanaEquipo(Integrantes.NOMBRE_EQUIPO, Integrantes.LEMA, tarjetas).setVisible(true);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Hay una tarjeta mal escrita", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}
