package co.umariana.equipo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** Ventana con el nombre del equipo y una tarjeta por integrante. */
public class VentanaEquipo extends JFrame {

    public VentanaEquipo(String nombreEquipo, String lema, List<Tarjeta> lista) {
        super("Tarjetas del equipo");

        JLabel titulo = new JLabel(nombreEquipo);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 28f));
        JLabel subtitulo = new JLabel(lema);
        subtitulo.setFont(subtitulo.getFont().deriveFont(Font.ITALIC, 15f));
        JPanel cabecera = new JPanel();
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
        cabecera.setBorder(BorderFactory.createEmptyBorder(16, 18, 6, 18));
        cabecera.add(titulo);
        cabecera.add(subtitulo);

        JPanel tarjetas = new JPanel(new GridLayout(0, 2, 14, 14));
        tarjetas.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        for (Tarjeta t : lista) {
            tarjetas.add(crearTarjeta(t));
        }
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(tarjetas, BorderLayout.NORTH);
        JScrollPane desplazable = new JScrollPane(contenedor);
        desplazable.setBorder(null);
        desplazable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel total = new JLabel(lista.size() + (lista.size() == 1 ? " tarjeta" : " tarjetas"));
        total.setBorder(BorderFactory.createEmptyBorder(6, 18, 12, 18));

        add(cabecera, BorderLayout.NORTH);
        add(desplazable, BorderLayout.CENTER);
        add(total, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(620, 520);
        setLocationRelativeTo(null);
    }

    private static JPanel crearTarjeta(Tarjeta t) {
        Color fondo = Color.decode(t.color());
        // Texto negro o blanco según qué tan claro sea el fondo, para que siempre se lea.
        double luz = (0.299 * fondo.getRed() + 0.587 * fondo.getGreen() + 0.114 * fondo.getBlue()) / 255;
        Color texto = luz > 0.6 ? Color.BLACK : Color.WHITE;

        JLabel nombre = new JLabel(t.nombre());
        nombre.setFont(nombre.getFont().deriveFont(Font.BOLD, 20f));
        nombre.setForeground(texto);
        JLabel mensaje = new JLabel("<html><body style='width:200px'>" + escapar(t.mensaje()) + "</body></html>");
        mensaje.setForeground(texto);
        JLabel usuario = new JLabel("@" + t.usuario());
        usuario.setFont(usuario.getFont().deriveFont(12f));
        usuario.setForeground(texto);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        panel.add(nombre, BorderLayout.NORTH);
        panel.add(mensaje, BorderLayout.CENTER);
        panel.add(usuario, BorderLayout.SOUTH);
        return panel;
    }

    private static String escapar(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
