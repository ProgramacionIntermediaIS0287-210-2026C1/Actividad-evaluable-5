#!/bin/bash
# Script para automatizar la creación de Issues de GitHub con las Historias de Usuario

echo "Verificando autenticación de GitHub CLI..."
if ! command -v gh &> /dev/null
then
    echo "Error: gh (GitHub CLI) no está instalado. Instálalo para continuar."
    echo "Instrucciones de instalación (Debian/Ubuntu): sudo apt info gh && sudo apt install gh"
    exit 1
fi

REPO="ProgramacionIntermediaIS0287-210-2026C1/Actividad-Evaluable-4"

echo "Creando Issues en $REPO..."

# Función genérica para crear issues
crear_issue() {
    local title=$1
    local body_file=$2
    local label=$3
    gh issue create -R "$REPO" --title "$title" --body-file "$body_file" --label "$label"
}

# --- Sencillas ---
crear_issue "HU01: Personalización de Mensajes de Consola" "HU01_Personalizacion_Mensajes.md" "Sencilla"
crear_issue "HU02: Estado de Aprobación" "HU02_Estado_Aprobacion.md" "Sencilla"
crear_issue "HU03: Formato de Identificador Estudiantil" "HU03_Formato_Identificador.md" "Sencilla"
crear_issue "HU04: Guía de Uso Interfaz Gráfica" "HU04_Documentar_GUI.md" "Sencilla"
crear_issue "HU05: Viñetas en Preguntas de Opción Múltiple" "HU05_Vinetas_Multiples.md" "Sencilla"

# --- Medias ---
crear_issue "HU06: Validación Estricta de Identificadores" "HU06_Validar_ID_Estudiante.md" "Media"
crear_issue "HU07: Prevención de Respuestas Vacías" "HU07_Validar_Respuesta_Vacia.md" "Media"
crear_issue "HU08: Ignorar Líneas Vacías en CSV" "HU08_Resiliencia_CSV_Malo.md" "Media"
crear_issue "HU09: Límite de Preguntas por Examen" "HU09_Limite_Preguntas.md" "Media"
crear_issue "HU10: Medición de Tiempo Consumido" "HU10_Manejo_Tiempo.md" "Media"

# --- Difíciles ---
crear_issue "HU11: Nuevo Tipo de Pregunta (Ordenamiento)" "HU11_Nuevas_Preguntas.md" "Dificil"
crear_issue "HU12: Exportar Calificaciones" "HU12_Exportar_TXT.md" "Dificil"
crear_issue "HU13: Interrupción de Examen" "HU13_Pausar_Intento.md" "Dificil"
crear_issue "HU14: Panel Administrativo Provisional" "HU14_Menu_Admin.md" "Dificil"
crear_issue "HU15: Integración de Notificaciones Graficadas MVC" "HU15_Notificaciones_UI.md" "Dificil"

echo "¡Issues creados exitosamente en GitHub!"
