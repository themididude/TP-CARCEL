# Buenas. 

### gracias por darnos otra oportunidad en nuestro sistema carcelario . . . .

![carcel.jpg jeje](jail.jpg)

## ¿Que cosas cambiamos para este recuperatorio?

### ABCML completo (Alta, Baja Lógica, Consulta, Modificación)

![exposicion1](expo1.jpg)

## Elección de colecciones
#### Breve explicacion de por que elegimos dichas colecciones:

>MAP (Reclusos)
> - Ventaja: acceso directo por clave (habitualmente ID)). Ideal para operaciones como buscar, mover o obtener el pabellón de un recluso sin recorrer toda la colección.
> - Encaja bastante bien con metodos como getPabellonDelRecluso / buscarReclusoDB (Tiene sentido que un sistema carcelario oriente la busqueda a una key del prisionero).

>LIST (Empleados)
> - Ventaja: mantiene orden de inserción, permite duplicados y es simple de iterar para listados y reportes (ej: EmpleadoDB recorre List<Empleado> para mostrar empleados).
> - Buen ajuste si las operaciones más frecuentes son iteraciones completas o mostrar listas completas.

>HASHSET (Guardias)
> - Ventaja: HashSet ofrece contains/add/remove y evita duplicados basándose en equals/hashCode.
> - Tiene sentido cuando quieres unicidad (por placa) y búsquedas rápidas.
Guardia implementa equals y hashCode en src/PersonasEmpleadoUsuario/Guardia.java, lo que permite que HashSet funcione bien.

## Persistencia JSON, Cumplimiento de requisitos

![exposicion2](expo2.jpg)

> - Se garantiza que cada archivo contiene más de 15 elementos, según lo solicitado.
> - Se corrigieron inconsistencias como el uso simultáneo de "Activo" y "activo".
> - Serializacion de informes (no nos dimos cuenta que faltaba). JSON Construct reconstruye informes desde persistencia, etc.

## Revision estructural, robustez y coherencia interna

![exposicion4](expo4.jpg)

> - Los guardias ahora se almacenan en un HashSet, lo cual evita duplicados y mejora la eficiencia.
> - ColeccionManager fue actualizado para trabajar correctamente con esta nueva estructura.
> - Empleado ahora implementa la interfaz Activable para integrarse con el sistema de baja lógica.
> - Se agregaron setters faltantes en Guardia y nueva funcionalidad en Recluso (setSentencia).
> - Se eliminó un menú antiguo que quedaba como backup innecesario.
> - EmpleadoDB.crearEmpleadoDesdeConsola() fue reparado, ya que devolvía null.
> - Función RealRemove() implementada para casos en los que sí se requiere una eliminación real (por ejemplo, al mover un guardia entre pabellones).
### Tambien, ahora es posible modificar los datos de la carcel, pedidos por unica vez en la primera ejecucion.

## Actualización de los Menus y mejoras generales (calidad de vida) - - - - - -

![exposicion3](expo3.jpg)

### El sistema de menús fue revisado y reorganizado para ofrecer una navegación más clara. Entre los cambios más importantes se incluyen:

> - CRUCIAL: Inclusión de tres nuevas excepciónes personalizada: LoginInvalido, ReclusoNoEncontrado, WrongGenderException

> - Agregado de opciones que faltaban para completar el flujo de trabajo del menú.
> - Integración total de las nuevas funciones en todos los submenús.
> - También se incorporó la función global pedirCambios(), que permite modificar objetos manteniendo su valor actual si el usuario solo presiona Enter, mejorando la interacción en las operaciones de modificación.



# En sintesis, el sistema ahora:

### 1. Implementa correctamente baja lógica (soft delete, para persistencia historia en los datos eliminados como un recluso muerto, o un guardia despedido)

### 2. Persiste el estado de los objetos en JSON.

### 3. Presenta mayor robustez y coherencia interna en sus clases y colecciones.

### 4. Mejoramos el menu en subdivisiones coherentes con todas las funciones.


