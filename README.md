
# Tarea 3 de INF331 - Pruebas de Software

Sistema de gestión para un programa de fidelidad para una cadena de tiendas de conveniencia. El código viene acompañado con pruebas unitarias JUnit y medición de cobertura a través de...


<details>
<summary>Tabla de contenidos</summary>

- [Tareas de INF331 - Pruebas de Software](#tareas-de-inf331---pruebas-de-software)
- [Descripción del Diseño](#-🧩-Descripción-del-Diseño)
- [Instrucciones-para-compilar-y-ejecutar](#-⚙️-Instrucciones-para-compilar-y-ejecutar)
- [Ejemplo-de-Salid-de-Test](#💾-Ejemplo-de-Salid-de-Test)
- [¿Qué-tipo-de-cobertura-he-medido-y-por-qué?](#⭐-¿Qué-tipo-de-cobertura-he-medido-y-por-qué?)
- [Otras-consideraciones](#🔎-Otras-consideraciones)
- [Licencia](#📖-Licencia)

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
| Given                             | When                           | Then                                      | Salida esperada       |
| --------------------------------- | ------------------------------ | ----------------------------------------- | --------------------- |
| Un cliente nuevo con ID único     | Se agrega a la tienda          | El cliente queda registrado correctamente | Cliente encontrado    |
| Un cliente con ID duplicado       | Se intenta agregar a la tienda | No se duplica el registro                 | Solo uno con ese ID   |
| Un cliente ya existente en tienda | Se busca por ID                | Se retorna el cliente                     | Cliente "Ana"         |
| Un ID que no existe               | Se busca cliente               | No se encuentra cliente                   | `null`                |
| Un cliente existente              | Se actualiza nombre y correo   | Cambios se aplican correctamente          | `"Valentina"` y email |
| Un ID que no existe               | Se intenta actualizar          | No se aplica cambio                       | `false`               |
| Un cliente existente              | Se elimina por ID              | El cliente es removido de la tienda       | `true`                |
| Un ID que no existe               | Se intenta eliminar            | No se encuentra el cliente                | `false`               |

### TiendaCompraTest
| Given                                           | When                               | Then                                    | Salida esperada              |
| ----------------------------------------------- | ---------------------------------- | --------------------------------------- | ---------------------------- |
| Cliente Bronce hace compra de \$1000            | Se registra la compra              | Se agregan 10 puntos sin bonus          | 10 puntos, nivel "Bronce"    |
| Cliente realiza 3 compras de \$500 el mismo día | Se registran todas las compras     | Se otorgan 10 puntos de bonus           | 25 puntos, racha = 1         |
| Cliente con 1490 puntos                         | Realiza compra de \$1000           | Pasa a más de 1500 y sube a nivel "Oro" | Nivel actualizado a "Oro"    |
| Compra con cliente no existente                 | Se registra compra con ID inválido | No se agrega la compra                  | Lista de compras sigue vacía |



## ⭐ ¿Qué tipo de cobertura he medido y por qué?
a

## 🔎 Otras consideraciones
a

## 📖 Licencia
Por favor, revise este link.
