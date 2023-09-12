package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="flower")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FlowerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long pk_FlorID;

    @Column(name="name")
    private String name;
    @Column(name="country")
    private String country;

    public FlowerEntity(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
