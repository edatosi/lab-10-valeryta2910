#!/usr/bin/env bash
# Abre la ventana con las tarjetas del equipo. Se cierra con la X de la ventana (o Ctrl + C).
#
#   ./scripts/start.sh
set -euo pipefail
source "$(dirname "${BASH_SOURCE[0]}")/_comun.sh"
exigir_java

echo "Abriendo la aplicación (la primera vez tarda unos minutos mientras se descarga lo necesario)..."
exec ./mvnw -B -q compile exec:java "$@"
