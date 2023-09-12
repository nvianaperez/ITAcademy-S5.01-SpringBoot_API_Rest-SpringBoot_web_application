package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.repository;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.domain.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFlowerRepository extends JpaRepository<FlowerEntity, Long> {
    Optional<FlowerEntity> findByNameIgnoreCase(String name);
}
