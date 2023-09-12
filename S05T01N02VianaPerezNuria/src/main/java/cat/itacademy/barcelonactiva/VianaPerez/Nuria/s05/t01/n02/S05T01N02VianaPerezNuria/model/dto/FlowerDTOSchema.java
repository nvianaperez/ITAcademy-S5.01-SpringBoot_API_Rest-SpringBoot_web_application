package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class FlowerDTOSchema {

    //This class is used only to show how Swagger can modifiy the Request body required

    @Id
    @Schema (hidden = true)
    private int id;
    @Schema(defaultValue = "Geranio")
    private String name;
    @Schema(defaultValue = "Spain")
    private String country;

}
