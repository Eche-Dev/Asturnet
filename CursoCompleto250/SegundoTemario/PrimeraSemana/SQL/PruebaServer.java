import com.sun.net.httpserver.HttpServer; // Servidor HTTP ligero incluido en el JDK (sin Tomcat)
import java.io.OutputStream;              // Flujo de bytes para escribir el cuerpo de la respuesta
import java.net.InetSocketAddress;        // Representa IP + número de puerto donde escuchará el servidor
import java.nio.charset.StandardCharsets; // Juego de caracteres (UTF-8) para tildes y símbolos

public class PruebaServer {
    public static void main(String[] arg) throws Exception { // throws Exception delega errores de red/E-S
        // Crea el servidor HTTP en el puerto 8080 (http://localhost:8080); 0 = cola de conexiones por defecto
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Ruta raíz "/": la lambda se ejecuta en cada petición; "ex" (HttpExchange) contiene petición y respuesta
        server.createContext("/", ex -> {
            // Construye una respuesta HTML usando el método HTTP (GET, etc.) y la cabecera User-Agent del navegador
            String html = "<h1> Mir primer Server EcheVilla </h1> <p>Método: " + ex.getRequestMethod()
                        +   "</p><p>Navegador: " + ex.getRequestHeaders().getFirst("User-Agent") +
                        "</p>";

            // Envía código 200 OK, tipo de contenido HTML en UTF-8 y el cuerpo generado
            responder(ex, 200, "text/html; charset=UTF-8", html);
        });

        // Segunda ruta "/api/hora": devuelve un JSON con la hora actual del servidor
        server.createContext("/api/hora", ex ->
            responder(ex, 200, "application/json", "{\"hora\": \"" + java.time.LocalTime.now() +
        "\"}"));

        server.start(); // Arranca el servidor en segundo plano para aceptar peticiones
        System.out.println("Servidor arrancado"); // Avisa por consola de que ya está escuchando
        
    }

    // Método auxiliar que envía cualquier respuesta HTTP: línea de estado + cabeceras + cuerpo
    static void responder(com.sun.net.httpserver.HttpExchange ex, int codigo, String tipo, String cuerpo)
    throws java.io.IOException {
        byte[] bytes = cuerpo.getBytes(StandardCharsets.UTF_8); // Convierte el texto a bytes UTF-8
        ex.getResponseHeaders().set("Content-Type", tipo);      // Indica al navegador si recibe HTML o JSON
        ex.getResponseHeaders().set("Cache-Control", "no-store"); // Evita que el navegador guarde en caché la respuesta
        ex.sendResponseHeaders(codigo, bytes.length);           // Envía código de estado, cabeceras y tamaño del cuerpo
        try (OutputStream os = ex.getResponseBody()) { os.write(bytes);} // Escribe el cuerpo y cierra el flujo automáticamente
    }
}
