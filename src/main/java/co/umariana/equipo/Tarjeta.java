package co.umariana.equipo;

import java.util.regex.Pattern;

/**
 * La tarjeta de un integrante del equipo.
 *
 * @param usuario su usuario de GitHub (letras, números y guiones)
 * @param nombre  su nombre, como quiere que aparezca
 * @param mensaje una frase corta (máximo 80 caracteres)
 * @param color   un color con almohadilla y 6 dígitos hexadecimales, por ejemplo #FFD166
 *
 * Si algún dato es inválido, el constructor lanza una excepción que dice qué corregir.
 */
public record Tarjeta(String usuario, String nombre, String mensaje, String color) {

    public static final int MAXIMO_MENSAJE = 80;
    private static final Pattern USUARIO = Pattern.compile("[A-Za-z0-9]([A-Za-z0-9-]*[A-Za-z0-9])?");
    private static final Pattern COLOR = Pattern.compile("#[0-9A-Fa-f]{6}");

    public Tarjeta {
        usuario = usuario == null ? "" : usuario.strip();
        nombre = nombre == null ? "" : nombre.strip();
        mensaje = mensaje == null ? "" : mensaje.strip();
        color = color == null ? "" : color.strip();

        if (!USUARIO.matcher(usuario).matches()) {
            throw invalida(usuario, "el usuario debe ser su usuario de GitHub (letras, números y guiones)");
        }
        if (nombre.isEmpty()) {
            throw invalida(usuario, "falta el nombre");
        }
        if (mensaje.isEmpty()) {
            throw invalida(usuario, "falta el mensaje");
        }
        if (mensaje.length() > MAXIMO_MENSAJE) {
            throw invalida(usuario, "el mensaje tiene " + mensaje.length() + " caracteres y el máximo es " + MAXIMO_MENSAJE);
        }
        if (!COLOR.matcher(color).matches()) {
            throw invalida(usuario, "el color debe ser como #FFD166 (almohadilla y 6 dígitos hexadecimales)");
        }
        color = color.toUpperCase();
    }

    private static IllegalArgumentException invalida(String usuario, String motivo) {
        return new IllegalArgumentException("Tarjeta inválida (" + (usuario.isEmpty() ? "sin usuario" : usuario) + "): " + motivo);
    }
}
