# Historia de Usuario 8: Ignorar Líneas Vacías en el Banco de Preguntas CSV

## 📖 Descripción General
Como docente, quiero que si al modificar el CSV en Excel este introduce líneas vacías accidentales al final del archivo, el sistema las ignore y  siga funcionando, para evitar caídas en el proceso de carga de exámenes.

## 🎯 Nivel de Dificultad
**Medio** (Manipulación en infraestructura e I/O).

## ✅ Criterios de Aceptación
1. Ubicar el repositorio que procesa el CSV (`CsvQuestionBankRepository` u homólogo).
2. Modificar el bucle de lectura de líneas para que verifique si la línea obtenida está vacía o si solo contiene comas consecutivas sin texto útil (ej. `,,,,`).
3. En caso de detectarlo, se debe hacer un `continue` para saltar esa iteración sin lanzar excepciones tontas ni causar fallos en la estructura del arreglo particionado por `.split()`.

## 🛠️ Pistas de Implementación
* Revisa la clase en la capa `infrastructure` encargada de leer el archivo plano.
* Realiza un `.trim()` y chequea `isEmpty()` antes de intentar partir la cadena en trozos.
