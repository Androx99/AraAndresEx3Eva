PREGUNTA 1

Explica cómo funciona la relación 1:N entre Agencia y Satelite tanto en SQL como en Java.

En ambas indica que 1 agencia va a poder tener muchos satelites.
Como se traduce esto, En java los satelites deberan tener un objeto Agencia asignada permitiendo que cada agencia pueda tener mas de un satelite agenciado pero no que un satelite pueda tener mas de una agencia.
En SQL se utiliza el mismo principio pero utilizando las id en vez el objeto entero.

PREGUNTA 2

Explica por qué en Java utilizamos:

private Agencia agencia;

y no:

private int agenciaId;

Porque al llamar al objeto entero lo relacionamoos igualmente y es mas sencillo  utilizar las funciones del objeto

PREGUNTA 3

Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.

