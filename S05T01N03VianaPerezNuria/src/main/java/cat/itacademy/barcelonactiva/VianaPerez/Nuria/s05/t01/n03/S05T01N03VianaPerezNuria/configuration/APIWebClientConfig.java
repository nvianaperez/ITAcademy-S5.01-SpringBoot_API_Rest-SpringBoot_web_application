package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n03.S05T01N03VianaPerezNuria.configuration;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class APIWebClientConfig {


    @Bean
    public WebClient webClient() {

        /** Creating a WebClient instance with timeouts
         * first, create a HttpClient instance:
         * set the connection timeout via the ChannelOption.CONNECT_TIMEOUT_MILLIS option
         * configure a response timeout using the responseTimeout directive
         * second, configure WebClient using HttpClient instance and some headers
        */
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000));

        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:9001/flowers")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client;
    }
}
