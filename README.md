
# Tarea 3 de INF331 - Pruebas de Software

Sistema de gestión para un programa de fidelidad para una cadena de tiendas de conveniencia. El código viene acompañado con pruebas unitarias JUnit y medición de cobertura a través de EcclEmma.


<details>
<summary>Tabla de contenidos</summary>

- [Tareas de INF331 - Pruebas de Software](#tareas-de-inf331---pruebas-de-software)
- [Descripción del Diseño](#-descripción-del-diseño)
- [Instrucciones-para-compilar-y-ejecutar](#-instrucciones-para-compilar-y-ejecutar)
- [Ejemplo-de-Salida-de-Test](#-ejemplo-de-salida-de-test)
- [¿Qué-tipo-de-cobertura-he-medido-y-por-qué?](#-qué-tipo-de-cobertura-he-medido-y-por-qué)
- [Otras-consideraciones](#-otras-consideraciones)
- [Licencia](#-licencia)

</details>

## 🧩 Descripción del Diseño
Este sistema permite registrar clientes, compras y calcular puntos de fidelización con niveles (Bronce, Plata, Oro, Platino). El diseño se basa en tres clases principales:

- `Cliente`: Almacena información del usuario y su historial de puntos.
- `Compra`: Registra cada compra, calcula puntaje según nivel del cliente.
- `Tienda`: Administra los clientes y compras, actualiza niveles, genera IDs automáticos.

### 📘 Diagrama de Clases (UML simplificado)
![image](https://github.com/user-attachments/assets/b57071e1-1387-4a96-a57c-3cc7468b1352)

## ⚙️ Instrucciones para compilar y ejecutar
Este proyecto está desarrollado en Java y estructurado como una aplicación Maven. Para compilar, ejecutar y testear el sistema correctamente, se deben seguir los siguientes pasos:

### 📦 Requisitos previos
* Java JDK 17 o superior instalado
* Apache Maven instalado y agregado al PATH

### 🚀 Compilar el proyecto
1. Clona el repositorio
2. Navega e ingresa a la carpeta `Tarea3`.
3. Estando detro de la carpeta `Tarea3`, escriba `mvn compile`. Esto compilará todas las clases del directorio `src/main/java`.
4. Ejecuta la clase principal `Main.java` con `mvn exec:java "-Dexec.mainClass=Main"`. Esto correra la aplicación y se podrá utilizar.
   * En caso de que Maven no encuentre la clase `Main`, pruebe con `mvn exec:java -Dexec.mainClass=Main` (sin las comillas). Esto dependerá del sistema operativo.
   * En Windows PowerShell funciona con `mvn exec:java "-Dexec.mainClass=Main"`.
* Para ejecutar los test, utilice `mvn test`.

### 💡 Notas adicionales
* Los IDs de clientes y compras se generan automáticamente (C1, COMP1, etc.).
* Todos los datos se mantienen en memoria durante la ejecución.

## 💾 Ejemplo de Salida de Test
### CompraTest
| Given                                | When                  | Then                             | Salida esperada |
| ------------------------------------ | --------------------- | -------------------------------- | --------------- |
| Una compra de \$1000 y nivel Bronce  | Se calcula el puntaje | Se aplican reglas base           | 10 puntos       |
| Una compra de \$1000 y nivel Plata   | Se calcula el puntaje | Se aplica multiplicador 1.2      | 12 puntos       |
| Una compra de \$1000 y nivel Oro     | Se calcula el puntaje | Se aplica multiplicador 1.5      | 15 puntos       |
| Una compra de \$1000 y nivel Platino | Se calcula el puntaje | Se aplica multiplicador 2.0      | 20 puntos       |
| Una compra de \$270 y nivel Bronce   | Se calcula el puntaje | Redondeo hacia abajo del puntaje | 2 puntos        |

### ClienteTest
| Given                               | When                           | Then                                   | Salida esperada            |
| ----------------------------------- | ------------------------------ | -------------------------------------- | -------------------------- |
| Datos válidos para cliente          | Se crea el cliente             | Se inicializan atributos correctamente | ID, nombre, correo, etc.   |
| Un correo inválido al crear cliente | Se intenta crear el cliente    | Se lanza excepción                     | `IllegalArgumentException` |
| Cliente existente                   | Se cambia el nombre            | El nombre se actualiza                 | `"Valentina"`              |
| Cliente existente                   | Se cambia a un correo válido   | El correo se actualiza                 | `"nueva@mail.com"`         |
| Cliente existente                   | Se cambia a un correo inválido | Se lanza excepción                     | `IllegalArgumentException` |
| Cliente existente                   | Se actualizan puntos y nivel   | Los valores se modifican correctamente | 1000 pts, nivel "Plata"    |
| Cliente existente                   | Se actualiza la racha diaria   | El valor se guarda correctamente       | `2` días                   |

### TiendaTest
| Given                              | When                      | **Then                                   | Salida esperada            |
| --------------------------------------- | ------------------------------ | ------------------------------------------ | ------------------------------- |
| Datos válidos de nombre y correo        | Se agrega cliente              | Se crea cliente con ID automático          | Cliente con ID tipo `C1`, `C2`… |
| Cliente ya agregado con ID autogenerado | Se busca por ID                | Se retorna cliente                         | Cliente con nombre `"Ana"`      |
| Un ID que no existe                     | Se busca cliente               | No se encuentra cliente                    | `null`                          |
| Cliente existente                       | Se actualiza nombre y correo   | Cambios se aplican correctamente           | `"Valentina"` y nuevo correo    |
| ID no existente                         | Se intenta actualizar          | No se aplica ningún cambio                 | `false`                         |
| Cliente existente                       | Se elimina por ID              | Cliente es removido de la lista            | `true`                          |
| ID no existente                         | Se intenta eliminar            | No se encuentra el cliente                 | `false`                         |
| Cliente válido                          | Se registra compra automática  | Se crea compra con ID `COMPx`              | Compra registrada correctamente |
| ID inválido                             | Se registra compra automática  | No se crea compra                          | `null`                          |
| Cliente con compras registradas         | Se muestran sus compras        | Se imprime la información                  | No lanza excepción              |
| Cliente sin compras                     | Se muestran sus compras        | Se indica que no tiene compras registradas | No lanza excepción              |
| Tienda sin clientes                     | Se muestran todos los clientes | Se informa que no hay clientes             | No lanza excepción              |
| Tienda con clientes                     | Se muestran todos los clientes | Se imprime la lista                        | No lanza excepción              |
| Correo inválido (sin `@`)               | Se intenta agregar cliente     | Se lanza excepción                         | `IllegalArgumentException`      |
| Correo `null`                           | Se intenta agregar cliente     | Se lanza excepción                         | `IllegalArgumentException`      |


### TiendaCompraTest
| Given                                   | When                       | Then                           |Salida esperada         |
| -------------------------------------------- | ---------------------------------- | ----------------------------------- | ---------------------------- |
| Cliente Bronce hace compra de \$1000         | Se registra compra automática      | Se agregan 10 puntos sin bonus      | 10 puntos, nivel `"Bronce"`  |
| Cliente hace 3 compras de \$500 el mismo día | Se registran todas las compras     | Se otorgan 10 puntos extra de bonus | 25 puntos, bonus aplicado    |
| Cliente con 1490 puntos                      | Hace compra de \$1000              | Supera 1500, sube a nivel `"Oro"`   | Nivel actualizado a `"Oro"`  |
| Cliente no existente                         | Se registra compra con ID inválido | Compra no se agrega                 | Lista de compras sigue vacía |

## ⭐ ¿Qué tipo de cobertura he medido y por qué?
Se utilizó **EclEmma**, un plugin de Eclipse que permite medir cobertura de código al ejecutar pruebas. La cobertura obtenida corresponde a **cobertura de sentencias** y **de ramas**, lo que significa que se midió si todas las líneas y condiciones (`if`, `else`, etc.) fueron ejecutadas al menos una vez por los tests. Esta cobertura permite asegurar que se ejercitan los distintos caminos lógicos del programa, incluyendo casos esperados y alternativos.

Los resultados globales mostraron una cobertura total del **69,5%**, donde la clase `Compra` alcanzó el **100%**, `Tienda` un **96,8%**, `Cliente` un **61,6%**, y `Main` un **0,0%**, lo cual es esperable ya que contiene interacción por consola y no se prueba automáticamente. A nivel de pruebas, `CompraTest` obtuvo **100%**, `TiendaCompraTest` un **100%**, `TiendaTest` un **96,7%** y `ClienteTest` un **92,3%**.

![image](https://github.com/user-attachments/assets/3b87dc30-9979-4bff-a3bc-d414c8f957fe)

La cobertura se logró ejecutando todas las clases de prueba con la opción "Coverage As → JUnit Test". En particular, se diseñaron tests con el enfoque **given-when-then**. Esto permitió cubrir tanto la lógica de asignación de puntos como la gestión automática de IDs y condiciones condicionales que cambian el comportamiento del sistema según los datos de entrada.

## 📖 Licencia
Por favor, revise este [link](https://github.com/valnhe/inf331-tarea3/blob/master/LICENSE.txt).
