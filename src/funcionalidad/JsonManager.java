package funcionalidad;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

public class JsonManager {

    private JsonManager() {} // Clase estática, no instanciable

    // -----------------------------
    // Objetos individuales
    // -----------------------------
    public static <T extends JSONConvertible> void guardarObjeto(String ruta, T obj) throws IOException {
        JSONObject json = obj.toJSONObject();
        Files.writeString(Paths.get(ruta), json.toString(4));
    }

    public static <T> T leerObjeto(String ruta, Function<JSONObject, T> creador) throws IOException, org.json.JSONException {
        String contenido = Files.readString(Paths.get(ruta));
        JSONObject json = new JSONObject(contenido);
        return creador.apply(json);
    }

    // -----------------------------
    // Listas y Sets
    // -----------------------------
    public static <T extends JSONConvertible> void guardarLista(String ruta, Collection<T> coleccion) throws IOException {
        JSONArray array = new JSONArray();
        for (T elem : coleccion) {
            array.put(elem.toJSONObject());
        }
        Files.writeString(Paths.get(ruta), array.toString(4));
    }

    public static <T> List<T> leerLista(String ruta, Function<JSONObject, T> creador) throws IOException, org.json.JSONException {
        String contenido = Files.readString(Paths.get(ruta));
        JSONArray array = new JSONArray(contenido);

        List<T> lista = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            lista.add(creador.apply(array.getJSONObject(i)));
        }
        return lista;
    }

    public static <T> Set<T> leerSet(String ruta, Function<JSONObject, T> creador) throws IOException, org.json.JSONException {
        return new HashSet<>(leerLista(ruta, creador));
    }

    // -----------------------------
    // Mapas
    // -----------------------------
    public static void guardarMapaStr(String ruta, Map<String, Object> mapa) throws IOException {
        JSONObject json = new JSONObject(mapa);
        Files.writeString(Paths.get(ruta), json.toString(4));
    }
    public static <T extends JSONConvertible> void guardarMapaInt(String ruta, Map<Integer, T> mapa) throws IOException {
        JSONObject json = mapDeObjetosAJSONObjectInt(mapa);
        Files.writeString(Paths.get(ruta), json.toString(4));
    }


    public static Map<String, Object> leerMapaStr(String ruta) throws IOException, org.json.JSONException {
        String contenido = Files.readString(Paths.get(ruta));
        JSONObject json = new JSONObject(contenido);
        return json.toMap();
    }
    public static <T> Map<Integer, T> leerMapaInt(String ruta, Function<JSONObject, T> creador) throws IOException, org.json.JSONException {
        String contenido = Files.readString(Paths.get(ruta));
        JSONObject json = new JSONObject(contenido);

        Map<Integer, T> mapa = new HashMap<>();
        for (String key : json.keySet()) {
            Integer intKey = Integer.parseInt(key);                  // Convertir la clave a Integer
            T value = creador.apply(json.getJSONObject(key));       // Crear objeto usando el creador
            mapa.put(intKey, value);
        }

        return mapa;
    }


    public static <T extends JSONConvertible> JSONObject mapDeObjetosAJSONObjectStr(Map<String, T> mapa) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, T> e : mapa.entrySet()) {
            json.put(e.getKey(), e.getValue().toJSONObject());
        }
        return json;
    }
    public static <T extends JSONConvertible> JSONObject mapDeObjetosAJSONObjectInt(Map<Integer, T> mapa) {
        JSONObject json = new JSONObject();
        for (Map.Entry<Integer, T> e : mapa.entrySet()) {
            json.put(String.valueOf(e.getKey()), e.getValue().toJSONObject());
        }
        return json;
    }

    public static <T> Map<String, T> jsonAmapDeObjetos(JSONObject json, Function<JSONObject, T> creador) {
        Map<String, T> mapa = new LinkedHashMap<>();
        for (String key : json.keySet()) {
            mapa.put(key, creador.apply(json.getJSONObject(key)));
        }
        return mapa;
    }
}


/*
::::::----------:::::-==----+++++===--:::::::+#%*-::::::::::::::::::::::::::::::::::::::::::::::::::
:::-:::---------:::::-==---=+++++====----=-+%%%#+++*#%%@%%%%%%#=-:::::::::::::::::::::::::::::::::::
::::::----------:::::-==---=++*+++***+#%%%%%@@%@@@@@@@@@@@%%%%%#**=:::::::::::::::::::::::::::::::::
:::::::---------:::::-==---=+*****#%%@@%@@@%%@@@@@@@@@@@@@@@@@@%%%*+-:::::::::::::::::::::::::::::::
::::-:::--------:::::-==---+#####%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%*=::::::::::::::::::::::::::::
---::::---------:::::-==----+*%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%###+-:::::::::::::::::::::::::
-:::::----------:::::-===---+#%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@%%%%####+-:::::::::::::::::::::::
-:-::-:---------:::::-===*==#%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@@%%%#####+-:::::::::::::::::::::
-:--::::--------:::::-+===*%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%##%%%%#=::::::::::::::::::::
-:--::::--------:::::-+==%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%#####%%+-::::::::::::::::::
-::-::----------:::::-+*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%#%%%%%%*-:::::::::::::::::
---:::----------:::::-*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%@#-::::::::::::::::
---::-:----------::::=%%@@@@@@@@@%%%%%@@@@@@@@@@@@@@@@@@@@@@@@%%@%%%@@@@@@@%%%#*@@@#=:::::::::::::-:
--:::::----------::--@@@@@@@@@@%%%####%%@%%@@@@@@@@@@@@@@@@@@@@%@%%%%%@@@@@@%%%-::+@#*-::::::::::::-
---:-------------:::*@@@@@@@@@%%%#****#%%%%%%@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%@%%%+-:::%%*=::::::------
---::------------::-@@@@#%@@@@%%##*****##%%%%%@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%@@@@@%=+@%=:::::------
--:::------------::#@%#==#@%@%%##*********#%@@@@@@@@@@@@@@@@@@@@@@%%%%#*##%%%%@@@%@@@@@@+:::--------
-----------------:*@%-====#%@%#*************##%%@@@@@@@@@@@@@@@@@@@%%#*++*%%%@%%%%@%%%%%+::---------
-----------------*@%--*%@@@@%%#*****########****#%%%%%@@@@@@@@@@@%%%#+===+#%@@%%%@%%%%%%=-----------
----------------=@@%#@@@@@@@%%***#%%%%%%%%%%%%#***##%%%%@@@@@@@%%%##*+=--=*@@@%%%@@@@%%%=-----------
-----------------@@@@@@@@@@@@%###########%%%%%##***###%%%%%%%%%%%%##*+==-+#@@@@@@@@@@%%%------------
-----------------#%@@@@@@@@@@@%%%%%%%%%%@@@%%%####*###%%@@@%%%%%*+*#**%@@@%@@@@@@@@@%%%%------------
-----------------+%@@@@@@@@@%%%#**##%%%%%%%####@@@@@@@%%%%%##%%%#***+*##@@%@@@@@@@@@%%%%------------
------------------%@@%%@@@@@@@%****#############@@#%@@#%%%%%####*++*##==*-%@@@@@@@@@%%%#---------===
------------------=%%%#%@@@@@@@#****############@***#%#*#%%####***##+--#==@@@@@@@@@@%%%*-=+**##%%%%%
-------------------%%@@@@@@@@@@#****####*####*#%#**+=*%#+++++++*##*=--+%-*@@@@@@@@@@%%@%%%%%%%%%%%%%
-------------------=%@@@@@@@@@@@#*****####***#%#***+--*%#+++**##*+===##--#@@@@@@@@@@%%@%%%%%%%%%%%%%
-------------------:#%@@@@@@@@@%*%%%%#####%%%##****+=:=*=*%%%%%%%%%+===-=@@@@@@@@@@@@@@@@@@@@@@@@@@%
--------------------=%%@@@@@@@@@#**************#***++-:++####*++++=====-*@@@@@@@@@@@@@@@@@@@@@@@@@@@
---------------------#%%@@@@@@@@%*****************++++--+##**+++========%@@@@@@@@@@@@@@@@@@@@@@@@@@@
----------------------+%@@@@@@@@@#*******************++*+***++++========@@@@@@@@@@@@@@@@@@@@@@@@@@@@
----------------------++%@@@@@@@@%**************##**###+====+======+====+%@@@@@%%@@@@@@@@@@@@@@@@@@@
----------------------+%@@@@@@@@@@#****************###*+==============+%#=+%@@@@@@@@@@@@@@@@@@@@@@@@
----------------------=*@#=@@@@@@@@#***************#***+===========+==*%%*=-#@@@@@@@@@@@@@@@@@@@@@@@
----------------------===%#--+*@%++=+*********####***##*++***+====++=*##%%+=-=%@@@@@@@@@@@@@@@@@@@@@
----------------------====%%=++%@%+==+#****#*########%#######*+===++==**%%#+--:--#@@@@@@@@@@@@@@@@@%
----------------------==---#@@#+%%+==#@#*********#####**+++++++==++==---+%%*=--::::==+=++%@@@@%%%%%%
----------------------==-----%@@%%*=+@@@#####****########****++=+++===---###+=-----:-*###=--#%%%%%%%
----------------------==-----=+#@@@#==+##%####******####****+++*++++==---##%*=--------=#*=-=+%%%%%@%
----------------------==-----=+++%@@@#%#%%##**********++++++++***++++==-=*#%*+=-------*##+--*%%%%%%%
----------------------==--+++*#*#%%@%%%#%%%###*********+++++**#**++++===**%%#+=-------###=---*%%%%%%
----------------------==++++*#*#%%%%#%#%%%%%##############********+++++*+#%%#*+=-------**=-----+*#%%
---------------------=+++**#%##%%%%%%%#%%%%%%###***######***##******++#*+#%%#*+======-=*=----------+
--------------------++++*##%%##%%%%%%%#%%%#%@@###**############******##+*####*+======+#+==---=------
------------------+****####%%#%%%%%%@##%%%#%@@%#######################*+####**+==+++*%*=======------
----------------+**#######%%##%%%%%#@%#%%%%%@@@%######################+=*###**+***#%@#==============
--------------****%######%%%#%%%%%%#@%####%%@%@%######################+++****++*#%@%*+==============
-----------=**##*#%######%%%#%%%%%%#%%%#%%%%@%@@%#####################*********%@%%*+++++++=++++====
==========*######%%%#%%%%%%%%%@@%%%%%%%%%%%%@@@@%%################%##########%@@%%*****++*+***+++=== */

