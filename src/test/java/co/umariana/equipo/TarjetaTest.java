package co.umariana.equipo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Pruebas de las reglas de una tarjeta. NO MODIFIQUE ESTE ARCHIVO. */
class TarjetaTest {

    @Test
    void unaTarjetaValidaSeGuardaConElColorEnMayusculas() {
        Tarjeta t = new Tarjeta("ana-perez", "Ana Pérez", "Hola equipo", "#ffd166");
        assertEquals("#FFD166", t.color());
        assertEquals("Ana Pérez", t.nombre());
    }

    @Test
    void quitaLosEspaciosDeLosExtremos() {
        Tarjeta t = new Tarjeta(" ana ", "  Ana  ", " Hola ", " #112233 ");
        assertEquals(new Tarjeta("ana", "Ana", "Hola", "#112233"), t);
    }

    @Test
    void rechazaUnUsuarioQueNoEsDeGitHub() {
        for (String usuario : new String[] {"", "juan perez", "-juan", "juan-", "ju@n", null}) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Tarjeta(usuario, "Ana", "Hola", "#112233"), "usuario: " + usuario);
        }
    }

    @Test
    void rechazaUnNombreVacio() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Tarjeta("ana", "  ", "Hola", "#112233"));
        assertTrue(e.getMessage().contains("ana") && e.getMessage().contains("nombre"));
    }

    @Test
    void rechazaUnMensajeVacioODemasiadoLargo() {
        assertThrows(IllegalArgumentException.class, () -> new Tarjeta("ana", "Ana", "", "#112233"));
        assertThrows(IllegalArgumentException.class,
                () -> new Tarjeta("ana", "Ana", "x".repeat(Tarjeta.MAXIMO_MENSAJE + 1), "#112233"));
        assertEquals(Tarjeta.MAXIMO_MENSAJE,
                new Tarjeta("ana", "Ana", "x".repeat(Tarjeta.MAXIMO_MENSAJE), "#112233").mensaje().length());
    }

    @Test
    void rechazaUnColorInvalido() {
        for (String color : new String[] {"", "rojo", "FFD166", "#FFD16", "#GGGGGG", "#FFD1666", null}) {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> new Tarjeta("ana", "Ana", "Hola", color), "color: " + color);
            assertTrue(e.getMessage().contains("color"));
        }
    }
}
