# Análisis de Archivos Java: DDD, SOLID y POO

Este documento contiene un análisis detallado de cada uno de los 15 archivos Java que componen el proyecto, explicando sus funciones principales, la implementación de principios SOLID, los fundamentos de Programación Orientada a Objetos (POO) aplicados y su papel en el diseño guiado por el dominio (DDD - Domain-Driven Design) según la arquitectura limpia del proyecto.

---

## 1. `com/exam/Main.java`
- **Funciones:** Es el punto de entrada (Bootstrapping o Composition Root) de la aplicación para su ejecución en Consola. Instancia los repositorios de infraestructura, los servicios del dominio, los casos de uso (Application Service) y arranca la interfaz de usuario en consola, inyectando todas las dependencias manualmente. Además, provee un método estático para generar el archivo CSV dummy de prueba.
- **Principios SOLID:** Implementa el **Principio de Inversión de Dependencias (DIP)**. En este punto donde las implementaciones de bajo nivel (infraestructura) se inyectan en componentes de alto nivel (dominio y aplicación) por medio de sus interfaces.
- **Fundamentos de POO:** Se evidencia el uso de abstracción, al instanciar y asignar a interfaces estandarizadas (ej: referenciar `CsvQuestionBankRepository` con la interfaz `QuestionBankRepository`).
- **Contexto DDD:** Actúa en la **Capa de Configuración/ Main**, acoplando la infraestructura a la capa de dominio en un entorno real. Es el Composition Root del sistema.

## 2. `com/exam/MainSwing.java`
- **Funciones:** Punto de entrada para iniciar la aplicación mediante la Interfaz Gráfica de usuario (GUI) en Java Swing. Al igual que el `Main` de consola, inyecta las dependencias e inicia el flujo asíncrono para interactuar por pantalla con ventanas y alertas.
- **Principios SOLID:** Implementa **DIP** (inyección de dependencias) y el **Principio de Responsabilidad Única (SRP)**, estando especializado estrictamente en preparar e iniciar la vista Swing pero no manejando la lógica de UI por sí mismo, cediéndola a `SwingUI`.
- **Fundamentos de POO:** Instanciación y manejo de programación imperativa, ocultamiento de la lógica de negocio mediante la abstracción instanciada de `ExamApplicationService`.
- **Contexto DDD:** Es la **Capa de Configuración/ Main**, adaptando el arranque para la perspectiva Front-end Gráfica (Adaptador Primario).

## 3. `com/exam/application/ExamApplicationService.java`
- **Funciones:** Ofrece una fachada (o API) a la capa de presentación que expone de forma explícita los **casos de uso** principales del sistema: iniciar examen, responder a una pregunta y finalizar el examen, coordinando componentes entre repositorios y entidades.
- **Principios SOLID:** **Principio de Responsabilidad Única (SRP)** (Responsable netamente de orquestar flujos y manejar persistencia temporal/casos de uso). No contiene reglas de negocio.
- **Fundamentos de POO:** **Encapsulación pura**, la lógica de coordinación mantiene instancias en su interior sin exponer detalles ni referencias internas directamente al UI sino mediado por DTOs.
- **Contexto DDD:** Pertenece a la **Application Layer (Capa de Aplicación)**. No posee regla de negocio de dominio, es el pegamento que usa al dominio para procesar comandos. 

## 4. `com/exam/application/dto/DTOs.java`
- **Funciones:** Contiene objetos que almacenan y trasladan información plana del dominio a la interfaz de usuario sin exponer los Aggregate Roots o las entidades complejas de manera riesgosa.
- **Principios SOLID:** **SRP (Principio de Responsabilidad Única)**. Su única responsabilidad es el transporte de datos "en frío". También apoya en evitar violaciones de límites arquitecturales.
- **Fundamentos de POO:** **Inmutabilidad**, modelada inteligentemente utilizando las estructuras limpias tipo `record` nativamente provistas por Java, garantizando que el transporte de datos sea predecible y no genere efectos secundarios causados por encapsulamiento débil.
- **Contexto DDD:** Transportadores de datos que ayudan a blindar a los agregados y no exponer comportamiento (**Data Transfer Layer / Application Layer**).

## 5. `com/exam/domain/model/ExamAttempt.java`
- **Funciones:** Representa una sesión y las intenciones en ejecución del estudiante mientras desarrolla un examen. Almacena las respuestas del candidato, lleva un control de si ha terminado o no y encapsula su calificación oficial al momento del cierre.
- **Principios SOLID:** Sigue el **Principio de Responsabilidad Única (SRP)**. Controla solamente las transformaciones de estado y asignaciones directas referentes a su propio inicio, registro de respuestas y finalización.
- **Fundamentos de POO:** **Encapsulamiento de alto rigor**. Protege el mapa de respuestas (`answers`), restringiendo su modificación cuando `isFinished` ya es verdadero; y prohíbe exponer setter indiscriminadamente.
- **Contexto DDD:** Componente central, es el **Aggregate Root (Raíz de Agregado)**. Engloba el clúster de entidades y datos requeridos para transacciones de integridad atómica relativas al intento.

## 6. `com/exam/domain/model/Question.java`
- **Funciones:** Clase abstracta genérica que establece qué es una pregunta desde un punto de vista base (define que todas poseen texto, llave de identificación (ID) y un texto para la respuesta correcta) y un contrato abstracto para ser respondida e impresa.
- **Principios SOLID:** Implementa magistralmente el **Principio de Abierto/Cerrado (OCP)** (abierta para generar cualquier nuevo tipo de pregunta, cerrada para afectar el modelo genérico que espera cada pregunta) y promueve implícitamente el **Principio de Sustitución de Liskov (LSP)** en sus subclases.
- **Fundamentos de POO:** **Abstracción general y Polimorfismo** al exigir las declaraciones de métodos como `isCorrect()` y `displayFormat()` a quien concrete la pregunta.
- **Contexto DDD:** Es una **Entity (Entidad del Dominio)** porque tienen una identidad explícita modelada a lo largo del tiempo (marcada por `QuestionId`).

## 7. `com/exam/domain/model/QuestionTypes.java`
- **Funciones:** Una macro-clase estática integradora que define con exactitud múltiples comportamientos de pregunta (Elección Única, Falso/Verdadero, Llenar vacíos, Múltiple).
- **Principios SOLID:** Son el pilar del **Liskov Substitution Principle (LSP)** en la jerarquía, ya que no rompen condiciones ni supuestos generales al ser tratados como el tipo `Question`. También implementan el comportamiento para cerrar y aprovechar el **Principio OCP**, separando la lógica algorítmica de qué implica cada validación de respuestas de negocio.
- **Fundamentos de POO:** Aprovechamiento profundo de la **Herencia** (vía la relación conceptual *Es-Un* con `Question`) y la implementación lógica del **Polimorfismo** (resolviendo reglas diferentes en `isCorrect`).
- **Contexto DDD:** Conforman las **Entidades concretas** derivadas, aportando lógica explícita de negocio a los datos.

## 8. `com/exam/domain/repository/Repositories.java`
- **Funciones:** Declara cómo las entidades del sistema serán almacenadas o recuperadas desde cualquier persistencia, estableciendo solo el "Qué", sin definir el "Cómo". Tiene repositorios independientes de Intentos de Exámenes y del Banco de Preguntas.
- **Principios SOLID:** Implementación de **Interface Segregation Principle (ISP)** dividiendo contratos en abstracciones distintas (`ExamAttemptRepository` y `QuestionBankRepository`) y no forzando un acoplamiento. También sirve como la declaración fundamental del **Principio de Inversión de Dependencias (DIP)**.
- **Fundamentos de POO:** **Abstracción pura**, definiendo los contratos (interfaces).
- **Contexto DDD:** Corresponden claramente al concepto de **Repositories (Repositorios)** a manera de coleccionadores en memoria, actuando como Puertos de Salida en una Clean Architecture/Ports&Adapters.

## 9. `com/exam/domain/service/AttemptManager.java`
- **Funciones:** Contiene lógica para verificar las reglas de seguridad y anti-fraude referentes a que un estudiante solo cuente con un intento activo.
- **Principios SOLID:** Destaca en base al **SRP (Responsabilidad Única)** al aislar una regla de negocio muy particular; previniendo acoplarla en la propia Entidad de `ExamAttempt` que carece por conocimiento global propio de si existen o no otros de su mismo modelo en el repo.
- **Fundamentos de POO:** **Colaboración entre clases y Desacoplamiento**.
- **Contexto DDD:** Es un **Domain Service (Servicio de Dominio)** encargado de reglas de negocio en la que participan varias entidades o no calzan con la lógica interior específica de una sola de ellas a nivel unitario de la identidad.

## 10. `com/exam/domain/service/GradingService.java`
- **Funciones:** Determina cuántas son las respuestas acertadas cruzando la información guardada en un `ExamAttempt` con la comprobación polimórfica que permite verificar cada `Question` base. Devuelve una puntuación final sobre un total estructurado como `Calificacion`.
- **Principios SOLID:** Respeta **SRP**, desvinculando la lógica iterativa de "evaluación y calificación de set de datos masivos" fuera de un objeto Entidad puro.
- **Fundamentos de POO:** **Paso por Mensajes/Acoplamiento Sintáctico** (le envía instrucciones a los objetos para que comprueben su estado algorítmico interno por comportamiento (`isCorrect()`).
- **Contexto DDD:** También un **Domain Service**, ideal para aislar comportamientos de cálculo transaccional sin distorsionar o recargar entidades.

## 11. `com/exam/domain/vo/ValueObjects.java`
- **Funciones:** Agrupa tipos específicos de alto valor contextual para proteger la inyección de tipos en constructores (ID de Estudiantes, UUID de Preguntas, Frases de Respuestas o Resultados de evaluación globales con sumatorias e integros de puntaje).
- **Principios SOLID:** Fomenta el paradigma anti error de dominios asegurando indirectamente que el sistema está estructurado mediante un modelo atómico (Responsabilidad Única para tipos simples).
- **Fundamentos de POO:** **Inmutabilidad absoluta** en Java con estructuras `records`. Garantiza una programación robusta orientada a restricciones sin lidiar con los métodos getters and setters invasivos.
- **Contexto DDD:** **Value Objects (Objetos de Valor)**, clases determinadas no por quién son mediante una identidad temporal, sino por los datos o su valor interno en estricto rigor de igualdades conceptuales.

## 12. `com/exam/infrastructure/CsvQuestionBankRepository.java`
- **Funciones:** Adaptador tecnológico especializado en acceder a ficheros albergados en el disco duro (un `.csv` o base tabular), parsearlos sistemáticamente y construir instancias polimórficas de Entidades de sub-tipos de `Question`.
- **Principios SOLID:** Implementa **DIP** (depende de la abstracción `QuestionBankRepository` no de lógica de alto nivel). También sigue tácitamente **SRP** encargado en solitario del I/O de CSV.
- **Fundamentos de POO:** Implementación de Abstracción con **Herencia de interfaces**, redefiniendo la lógica de ejecución del método `findAll()`.
- **Contexto DDD:** Actúa como componente en la **Capa de Infraestructura**. Representa el Adaptador Secundario bajo el concepto de Clean Architecture.

## 13. `com/exam/infrastructure/InMemoryExamAttemptRepository.java`
- **Funciones:** Adaptador tecnológico de base de datos volátil. Utiliza diccionarios nativos (`HashMap`) temporales para guardar cada intento de estudiante o poder buscar rápidamente cuál tiene en ejecución activa.
- **Principios SOLID:** Implementa fuertemente **DIP** como una alternativa concreta fácil y sencilla de suplir el repositorio base sin acoplar lógicas subyacentes.
- **Fundamentos de POO:** Implementación de interface (polimorfismo del diseño para ser tratada globalmente como referenciable).
- **Contexto DDD:** Se acopla en la **Capa de Infraestructura**, como un segundo Adaptador Secundario (manejo asíncrono temporal en memoria).

## 14. `com/exam/presentation/ConsoleUI.java`
- **Funciones:** Implementa un entorno de interacción de consola del lado del cliente final, haciendo loop entre preguntas, procesando capturas vía `Scanner`, enviándolas al caso de uso a través del Application Service y procesando las salidas estándar finales.
- **Principios SOLID:** Implementa el **SRP**. Se dedica enteramente o exclusivamente a recibir las peticiones e imprimir strings de salida sin incluir lógicas de procesamiento.
- **Fundamentos de POO:** Relación de **Asociación** (mantiene referencia al servicio de aplicación y escrutinio del sistema de entrada). Encapsula `Scanner` limitándolo al interior de vida útil.
- **Contexto DDD:** **Capa de Presentación**. Actúa como Adaptador Primario para accionar/disparar Casos de Uso del sistema.

## 15. `com/exam/presentation/SwingUI.java`
- **Funciones:** Expone una ventana gráfica de la aplicación interactiva que evoluciona junto con el flujo del caso de uso. Presenta visualmente vistas dinámicas que se adapten al tipo y subtipo de preguntas utilizando JFrames, JPanels, Layouts, y componentes de forms.
- **Principios SOLID:** Aprovecha el **Principio Abierto/Cerrado (OCP)** en su factory de paneles visuales, logrando dibujar la interfaz dependiendo de en cuál subclase de Pregunta en cuestión se base, sin depender de hardcoding tipo if-else que quiebre la jerarquía, apoyadas por abstracciones y delegados como `QuestionPanel`.
- **Fundamentos de POO:** Uso abundante y robusto de **Patrones de Diseño (Factory Method, Composiciones)** apoyados sobre **Polimorfismo, Herencia** (derivaciones de Swing GUI Component) y **Abstracciones** del contrato de presentación interna.
- **Contexto DDD:** Encaja perfectamente como un Adaptador Primario en la **Capa de Presentación** para interfaces ricas, sin romper con el encapsulamiento de flujo hacia el modelo central.
