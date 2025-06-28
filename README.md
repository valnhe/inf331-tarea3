
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
a

## ⚙️ Instrucciones para compilar y ejecutar
a

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
| Given                                       | When                              | Then                                       | Salida esperada                 |
| ------------------------------------------- | --------------------------------- | ------------------------------------------ | ------------------------------- |
| Un cliente nuevo con ID único               | Se agrega a la tienda             | El cliente queda registrado correctamente  | Cliente encontrado              |
| Un cliente con ID duplicado                 | Se intenta agregar                | No se duplica el registro                  | Solo uno con ese ID             |
| Un cliente ya existente                     | Se busca por ID                   | Se retorna el cliente                      | Cliente con nombre "Ana"        |
| Un ID que no existe                         | Se busca cliente                  | No se encuentra cliente                    | `null`                          |
| Un cliente existente                        | Se actualiza nombre y correo      | Cambios se aplican correctamente           | `"Valentina"` y nuevo correo    |
| Un ID que no existe                         | Se intenta actualizar             | No se aplica cambio                        | `false`                         |
| Un cliente existente                        | Se elimina por ID                 | El cliente es removido                     | `true`                          |
| Un ID que no existe                         | Se intenta eliminar               | No se encuentra el cliente                 | `false`                         |
| Un cliente válido                           | Se registra compra automática     | Se crea compra con ID automático           | Compra con ID `COMPx`           |
| Un cliente inválido                         | Se registra compra automática     | No se crea compra                          | `null`                          |
| Un cliente con compras                      | Se muestran sus compras           | Se imprime la información de compras       | No lanza excepción              |
| Un cliente sin compras                      | Se muestran sus compras           | Se indica que no tiene compras registradas | No lanza excepción              |
| Una tienda sin clientes                     | Se muestran todos los clientes    | Se informa que no hay clientes             | No lanza excepción              |
| Una tienda con clientes                     | Se muestran todos los clientes    | Se imprime la lista de clientes            | No lanza excepción              |
| Datos válidos de nombre y correo            | Se agrega cliente automáticamente | Se crea cliente con ID automático          | Cliente con ID tipo `C1`, `C2`… |
| Correo inválido al crear cliente automático | Se intenta agregar                | Se lanza excepción                         | `IllegalArgumentException`      |

### TiendaCompraTest
| Given                                           | When                               | Then                                    | Salida esperada              |
| ----------------------------------------------- | ---------------------------------- | --------------------------------------- | ---------------------------- |
| Cliente Bronce hace compra de \$1000            | Se registra la compra              | Se agregan 10 puntos sin bonus          | 10 puntos, nivel "Bronce"    |
| Cliente realiza 3 compras de \$500 el mismo día | Se registran todas las compras     | Se otorgan 10 puntos de bonus           | 25 puntos, racha = 1         |
| Cliente con 1490 puntos                         | Realiza compra de \$1000           | Pasa a más de 1500 y sube a nivel "Oro" | Nivel actualizado a "Oro"    |
| Compra con cliente no existente                 | Se registra compra con ID inválido | No se agrega la compra                  | Lista de compras sigue vacía |



## ⭐ ¿Qué tipo de cobertura he medido y por qué?
Se utilizó **EclEmma**, un plugin de Eclipse que permite medir cobertura de código al ejecutar pruebas. La cobertura obtenida corresponde a **cobertura de sentencias** y **de ramas**, lo que significa que se midió si todas las líneas y condiciones (`if`, `else`, etc.) fueron ejecutadas al menos una vez por los tests. Esta cobertura permite asegurar que se ejercitan los distintos caminos lógicos del programa, incluyendo casos esperados y alternativos.

Los resultados globales mostraron una cobertura total del **71,1%**, donde la clase `Compra` alcanzó el **100%**, `Tienda` un **97,2%**, `Cliente` un **61,6%**, y `Main` un **0,0%**, lo cual es esperable ya que contiene interacción por consola y no se prueba automáticamente. A nivel de pruebas, `CompraTest` obtuvo **100%**, `TiendaCompraTest` un **99,3%**, `TiendaTest` un **97,1%** y `ClienteTest` un **92,3%**.

![image](https://github.com/user-attachments/assets/5e35ba04-2c24-4319-8cc3-02c3f136a709)

La cobertura se logró ejecutando todas las clases de prueba con la opción "Coverage As → JUnit Test". En particular, se diseñaron tests con el enfoque **given-when-then** para seguir lo recomendado por TDD. Esto permitió cubrir tanto la lógica de asignación de puntos como la gestión automática de IDs y condiciones condicionales que cambian el comportamiento del sistema según los datos de entrada.

## 🔎 Otras consideraciones
a

## 📖 Licencia
Por favor, revise este [link](https://github.com/valnhe/inf331-tarea3/blob/master/LICENSE.txt).
