# Historia de Usuario 3: Formato de Identificador Estudiantil

## 📖 Descripción General
Como desarrollador, quiero que el identificador del estudiante tenga una representación amigable en cadenas de texto para propósitos de depuración y logs.

## 🎯 Nivel de Dificultad
**Sencilla** (Sobreescritura de método `toString()`).

## ✅ Criterios de Aceptación
1. Modificar el Objeto de Valor (`Value Object`) `StudentId` para sobrescribir su método `toString()`.
2. El método sobreescrito debe devolver la cadena en formato: `[Estudiante-ID: XXXXX]`, donde `XXXXX` es el valor real interno.
3. Verificar que durante cualquier impresión indirecta de este objeto, se muestre dicho formato de manera estándar.

## 🛠️ Pistas de Implementación
* Busca el archivo `StudentId.java` dentro del paquete `vo` de la capa de Dominio.
* El núcleo del cambio es simplemente hacer uso de `@Override public String toString()`.
