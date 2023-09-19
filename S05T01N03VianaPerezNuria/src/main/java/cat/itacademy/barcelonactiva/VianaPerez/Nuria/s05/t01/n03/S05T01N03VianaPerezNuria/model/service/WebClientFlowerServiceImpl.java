package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n03.S05T01N03VianaPerezNuria.model.service;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n03.S05T01N03VianaPerezNuria.model.dto.FlowerDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebClientFlowerServiceImpl implements IWebClientFlowerService{

    private final WebClient webClient;


    @Override
    @Transactional
    public Mono<FlowerDTO> createFlower(FlowerDTO flowerDTO) {
        return webClient.post()
                .uri("/add")
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }
    @Override
    @Transactional
    public Mono<FlowerDTO> updateFlower(FlowerDTO flowerDTO) {
        return webClient.put()
                .uri("/update")
                .body(Mono.just(flowerDTO), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }
    @Override
    public Mono<FlowerDTO> deleteFlower(Long id) {
        return webClient.delete()
                .uri("/delete/"+id)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }
    @Override
    public Mono<FlowerDTO> getOneFlowerById(Long id) {
        return webClient.get()
                .uri("/getOne/"+id)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }
    @Override
    public Flux<FlowerDTO> getAllFlowers() {
        return webClient.get()
                .uri("/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class);
    }

}
