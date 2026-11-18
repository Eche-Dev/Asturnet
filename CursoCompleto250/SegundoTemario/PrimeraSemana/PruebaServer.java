import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class PruebaServer {
    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", ex -> {
            String html = "<h1> Mir primer Server EcheVilla </h1> <p>Método: " + ex.getRequestMethod()
                        +   "</p><p>Navegador: " + ex.getRequestHeaders().getFirst("User-Agent") +
                        "</p>";

                    responder(ex, 200, "text/html; charset=UTF-8", html);
        });

        server.createContext("/api/hora", ex ->
            responder(ex, 200, "application/json", "{\"hora\": \"" + java.time.LocalTime.now() +
        "\"}"));

        server.start();
        System.out.println("Servidor arrancado"); 
        
    }

    static void responder(com.sun.net.httpserver.HttpExchange ex, int codigo, String tipo, String cuerpo)
    throws java.io.IOException {
        byte[] bytes = cuerpo.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", tipo);
        ex.getResponseHeaders().set("Cache-Control", "no-store");
        ex.sendResponseHeaders(codigo, bytes.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(bytes);}
    }
}
