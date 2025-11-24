# Buenas. 

### Muchas gracias por darnos otra oportunidad en nuestro Sistema Carcelario!

## ¿Que cosas cambiamos para este recuperatorio?
### El sistema ahora:

1. Implementa correctamente baja lógica (soft delete, para persistencia historia en los datos eliminados como un recluso muerto, o un guardia despedido)

2. Persiste el estado de los objetos en JSON.

3. Mejoramos el menu en subdivisiones coherentes con todas las funciones.

4. Presenta mayor robustez y coherencia interna en sus clases y colecciones.

## Elección de colecciones
#### Breve explicacion de por que elegimos las colecciones elegidas.

>Prisioneros (Map)
>>Ventaja: acceso directo por clave (habitualmente ID)). Ideal para operaciones como buscar, mover o obtener el pabellón de un recluso sin recorrer toda la colección.
Encaja bastante bien con metodos como getPabellonDelRecluso / buscarReclusoDB (Tiene sentido que un sistema carcelario oriente la busqueda a una key del prisionero).


>Empleados (List)
>>Ventaja: mantiene orden de inserción, permite duplicados y es simple de iterar para listados y reportes (ej: EmpleadoDB recorre List<Empleado> para mostrar empleados).
Buen ajuste si las operaciones más frecuentes son iteraciones completas o mostrar listas completas (y las eliminaciones son baja lógica/ soft delete con Activo).


>Guardias (HashSet)
>>Ventaja: HashSet ofrece contains/add/remove y evita duplicados basándose en equals/hashCode. 
>Tiene sentido cuando quieres unicidad (por placa) y búsquedas rápidas.
Guardia implementa equals y hashCode en src/PersonasEmpleadoUsuario/Guardia.java, lo que permite que HashSet funcione bien.