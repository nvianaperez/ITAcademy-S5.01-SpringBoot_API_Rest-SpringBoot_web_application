package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n03.S05T01N03VianaPerezNuria.model.service;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n03.S05T01N03VianaPerezNuria.model.dto.FlowerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IWebClientFlowerService {
    Mono<FlowerDTO> createFlower(FlowerDTO flowerDTO);
    Mono<FlowerDTO> updateFlower(FlowerDTO flowerDTO);
    Mono<FlowerDTO> deleteFlower(Long id);
    Mono<FlowerDTO> getOneFlowerById(Long id);
    Flux<FlowerDTO> getAllFlowers();

}
