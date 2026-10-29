package co.umariana.equipo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Revisan LAS TARJETAS REALES del equipo (Integrantes.java): si la tarjeta de un integrante
 * está mal escrita, estas pruebas fallan y dicen cuál y por qué.
 * NO MODIFIQUE ESTE ARCHIVO.
 */
class IntegrantesTest {

    @Test
    void todasLasTarjetasSonValidas() {
        List<Tarjeta> tarjetas = assertDoesNotThrow(Integrantes::tarjetas);
        assertFalse(tarjetas.isEmpty(), "Integrantes.java no tiene ninguna tarjeta");
    }

    @Test
    void noHayUsuariosRepetidosAunqueCambieLaMayuscula() {
        Set<String> vistos = new HashSet<>();
        for (Tarjeta t : Integrantes.tarjetas()) {
            assertTrue(vistos.add(t.usuario().toLowerCase()), "Usuario repetido: " + t.usuario());
        }
    }

    @Test
    void elEquipoTieneNombreYLema() {
        assertFalse(Integrantes.NOMBRE_EQUIPO.isBlank(), "Falta el nombre del equipo");
        assertFalse(Integrantes.LEMA.isBlank(), "Falta el lema del equipo");
    }
}
