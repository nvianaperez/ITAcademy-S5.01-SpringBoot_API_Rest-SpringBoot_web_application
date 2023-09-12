package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.service;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.dto.FlowerDTO;

import java.util.List;

public interface IFlowerService {

    FlowerDTO createFlower(FlowerDTO flowerDTO);
    FlowerDTO updateFlower(FlowerDTO flowerDTO);
    boolean deleteFlower(Long id);
    FlowerDTO getOneFlowerById(Long id);
    List<FlowerDTO> getAllFlowers();

    boolean checkFlowerDTO(FlowerDTO flowerDTO);
}
