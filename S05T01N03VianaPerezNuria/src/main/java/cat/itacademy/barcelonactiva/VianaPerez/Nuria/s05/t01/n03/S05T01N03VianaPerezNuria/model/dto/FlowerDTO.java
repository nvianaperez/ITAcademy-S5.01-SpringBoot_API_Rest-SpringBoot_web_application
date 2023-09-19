package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n03.S05T01N03VianaPerezNuria.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO implements Serializable {
    @JsonProperty("pk_FlorID")
    private Long pk_FlorID;
    @NotNull
    @NotBlank(message = "Flower's name could not be blank.")
    @JsonProperty("name")
    private String name;
    @NotNull
    @NotBlank(message = "Flower's country could not be blank.")
    @JsonProperty("country")
    private String country;
    @JsonProperty("type")
    private String type;

    @JsonIgnore
    private final List<String> EUcountries = List.of("Austria", "Belgium", "Bulgaria", "Croatia","Republic of Cyprus",
            "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy",
            "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia",
            "Slovenia", "Spain", "Sweden");

    public FlowerDTO(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
        this.type = EUcountries.stream().anyMatch(c -> c.equalsIgnoreCase(country)) ? "EU" : "NOT EU";
    }

}

