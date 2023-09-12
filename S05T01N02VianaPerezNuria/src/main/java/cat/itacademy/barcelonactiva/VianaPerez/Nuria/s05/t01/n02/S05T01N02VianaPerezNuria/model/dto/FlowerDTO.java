package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FlowerDTO {
    private Long pk_FlorID;
    @NotNull
    @NotBlank(message = "Flower's name could not be blank.")
    private String name;
    @NotNull
    @NotBlank(message = "Flower's country could not be blank.")
    private String country;

    private String type;

    private final List<String> EUcountries = List.of("Austria", "Belgium", "Bulgaria", "Croatia","Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public void setCountry(String country) {
        this.country = country;
        this.type = EUcountries.stream()
                .anyMatch(c->c.equalsIgnoreCase(country)) ? "EU" : "NOT EU";
    }

}
