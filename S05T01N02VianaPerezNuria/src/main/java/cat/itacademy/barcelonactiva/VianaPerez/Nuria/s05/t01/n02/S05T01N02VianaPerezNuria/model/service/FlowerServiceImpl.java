package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.service;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.repository.IFlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements IFlowerService{

    @Autowired
    private IFlowerRepository flowerRepository;

    @Override
    @Transactional
    public FlowerDTO createFlower(FlowerDTO flowerDTO) {
        if(checkFlowerDTO(flowerDTO)) {
            FlowerEntity flower = convertToEntity(flowerDTO);
            return convertToDTO(flowerRepository.save(flower));
        }
        return null;
    }
    @Override
    @Transactional
    public FlowerDTO updateFlower(FlowerDTO flowerDTO) {
        if(flowerRepository.existsById(flowerDTO.getPk_FlorID())) {
            flowerRepository.save(convertToEntity(flowerDTO));
            return flowerDTO;
        }
        return null;
    }
    @Override
    public boolean deleteFlower(Long id) {
        if (flowerRepository.findById(id).isPresent()) {
            flowerRepository.delete(flowerRepository.findById(id).get());
            return true;
        }
        return false;
    }
    @Override
    public FlowerDTO getOneFlowerById(Long id) {
        if (flowerRepository.findById(id).isPresent()) {
            FlowerDTO flowerDTO = flowerRepository.findById(id)
                    .map(this::convertToDTO).get();
            return flowerDTO;
        }
        return null;
    }
    @Override
    public List<FlowerDTO> getAllFlowers() {
        if(flowerRepository.findAll().isEmpty()) return null;
        else return flowerRepository.findAll().stream()
                .map(this::convertToDTO)
//                .map(f -> convertToDTO(f))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkFlowerDTO(FlowerDTO flowerDTO) {
        Optional<FlowerEntity> flowerOptional = flowerRepository.findByNameIgnoreCase(flowerDTO.getName());
        if (flowerOptional.isPresent()) return false;
        if (flowerDTO.getName().isEmpty() || flowerDTO.getCountry().isEmpty()) return false;
        return true;
    }

    private FlowerDTO convertToDTO (FlowerEntity flower) {
        FlowerDTO flowerDTO = new FlowerDTO();
        flowerDTO.setPk_FlorID(flower.getPk_FlorID());
        flowerDTO.setName(flower.getName());
        flowerDTO.setCountry(flower.getCountry());
        return flowerDTO;
    }

    private FlowerEntity convertToEntity (FlowerDTO flowerDTO) {
        FlowerEntity flower = new FlowerEntity();
        flower.setPk_FlorID(flowerDTO.getPk_FlorID());
        flower.setName(flowerDTO.getName());
        flower.setCountry(flowerDTO.getCountry());
        return flower;
    }
}
