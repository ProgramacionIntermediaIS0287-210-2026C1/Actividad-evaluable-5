# Historia de Usuario 15: Integración de Notificaciones Graficadas MVC

## 📖 Descripción General
Como usuario de interfaz moderna, quiero que la interfaz gráfica (SwingUI) no envíe alertas de error a la consola negra trasera de los IDE cuando se equivoquen ingresando credenciales vacías, sino que me salga una ventana pop-up agradable y visible.

## 🎯 Nivel de Dificultad
**Difícil** (Refactorización grande del patrón en Presentation en Java UI).

## ✅ Criterios de Aceptación
1. Localizar los bloques `catch(Exception)` en `SwingUI.java`.
2. Evitar o eliminar todo rastro del uso de `System.out.println` o `printStackTrace()` en los flujos principales en dicha clase.
3. Usar en cambio algo similar a componente del SDK de UI, p.e. `JOptionPane.showMessageDialog()` para que arroje alertas modales contextualizando que no se puede arrancar el intento o que hay error en el ID brindado.
4. Abstraer este método para que quede genérico `mostrarErrorGrafico(String msg)` permitiendo ser rehusable.

## 🛠️ Pistas de Implementación
* Entérate de la librería `javax.swing.JOptionPane`.
* Pasa el contexto completo e interrumpe el ciclo lógico si es menester. Asegurate de que el programa no colapse internamente provocando bugs a futuro por mala gestión de estado del listener.
