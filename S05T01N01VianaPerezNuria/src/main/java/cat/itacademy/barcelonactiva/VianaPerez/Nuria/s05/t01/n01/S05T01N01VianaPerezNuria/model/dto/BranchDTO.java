package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n01.S05T01N01VianaPerezNuria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class BranchDTO {

    private Long pk_branchId;
    @NotNull
    @NotBlank(message = "Name of bankbranch could not be blank.")
    private String name;
    @NotNull
    @NotBlank(message = "Name of country could not be blank.")
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

//public record SucursalDTO (
//        Long pk_sucursalId,
//        @NotNull String nomSucursal,
//        @NotNull String paisSucursal,
//        @NotNull String tipusSucursal) {
//}
