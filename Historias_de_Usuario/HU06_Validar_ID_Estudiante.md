# Historia de Usuario 6: Validación Estricta de Identificadores

## 📖 Descripción General
Como administrador del sistema, quiero que se restrinja el formato de los IDs de estudiantes ingresados, para evitar errores en la persistencia o perfiles falsos con caracteres inválidos.

## 🎯 Nivel de Dificultad
**Medio** (Validaciones con expresiones regulares en dominio).

## ✅ Criterios de Aceptación
1. Modificar el Value Object `StudentId`.
2. Al momento de instanciarse, debe validar mediante una expresión regular que el ID solo contiene letras (a-z, A-Z) y números (0-9).
3. No debe permitir guiones, espacios ni otros caracteres especiales.
4. Si la validación falla, debe lanzar una `IllegalArgumentException` con un mensaje claro reportando el error en el formato.
5. El tamaño mínimo del ID debe ser 5 caracteres y máximo 10.

## 🛠️ Pistas de Implementación
* Revisa la clase `StudentId` en el paquete de `domain.vo`.
* Considera la función `String.matches("^[a-zA-Z0-9]{5,10}$")` en el constructor de `StudentId`.
