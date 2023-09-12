package cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.controller;

import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.dto.FlowerDTOSchema;
import cat.itacademy.barcelonactiva.VianaPerez.Nuria.s05.t01.n02.S05T01N02VianaPerezNuria.model.service.IFlowerService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/flowers")
public class FlowerController {
    @Autowired
    private IFlowerService flowerService;


    //http://localhost:9001/flowers/add
    @PostMapping("/add")
    @Operation(
            tags = {"Sprint 5.2"},
            operationId = "addFlower",
            summary = "Create a new flower and save in database",
            description = "needs a FlowerDTO in json format, check if flower is present by name and make sure that name and country attributes are not empty. Finally, the expected responses are 201,400 and 500",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "FlowerDTO constructor needs only name and country attributes",
            content = @Content(schema = @Schema(implementation = FlowerDTOSchema.class))),
//            requestBody has its own annotation, the others (like "pathVariable", "cookieValue", "requestHeader" or "requestParam")  go inside parameters annotation
//            parameters = {@Parameter(name = "name of the variable", description = "The path variable", example = "21", in = ParameterIn.PATH)},
//            hidden = true,
            externalDocs = @ExternalDocumentation(url = "#", description = "For more details check the link")
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Flower values not valid or Flower already exists", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the flower", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})})
    })

    public ResponseEntity<FlowerDTO> addFlower(@Valid @RequestBody FlowerDTO flowerDTO) {
        FlowerDTO flowerSaved = flowerService.createFlower(flowerDTO);
        if (flowerSaved != null) return new ResponseEntity<>(flowerSaved, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //http://localhost:9001/flowers/update
    @PutMapping("/update")
    @Operation(
            tags = {"Sprint 5.2"},
            operationId = "updateFlower",
            summary = "Update a flower and save changes in database",
            description = "needs a FlowerDTO in json format, check if flower is present by id and make sure that name and country NEW attributes are not empty. Finally, the expected responses are 200,400 and 404",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "FlowerDTO constructor needs only name and country attributes",
                    content = @Content(schema = @Schema(implementation = FlowerDTO.class))),
//            requestBody has its own annotation, the others (like "pathVariable", "cookieValue", "requestHeader" or "requestParam")  go inside parameters annotation
//            parameters = {@Parameter(name = "name of the variable", description = "The path variable", example = "21", in = ParameterIn.PATH)},
//            hidden = true,
            externalDocs = @ExternalDocumentation(url = "#", description = "For more details check the link")
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower updated correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Some of new Flower values are not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})}),
            @ApiResponse(responseCode = "404", description = "Flower id is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})})
    })
    public ResponseEntity<FlowerDTO> updateFlower(@Valid @RequestBody FlowerDTO flowerDTO) {
        FlowerDTO flowerUpdated = flowerService.updateFlower(flowerDTO);
        if(flowerUpdated == null)  return ResponseEntity.status(404).body(null);
        if(flowerUpdated.getName().isEmpty() || flowerUpdated.getCountry().isEmpty()) return ResponseEntity.status(400).body(null);
        return ResponseEntity.status(200).body(flowerUpdated);
    }

    //http://localhost:9001/flowers/delete/{id}
    @DeleteMapping("/delete/{id}")
    @Operation(
            tags = {"Sprint 5.2"},
            operationId = "deleteFlower",
            summary = "Delete a flower and save changes in database",
            description = "needs the variable id in the path, check if flower is present by id and delete the flower in database. Finally, the expected responses are 200 and 404",
            parameters = {@Parameter(name = "id", description = "Id from the path variable", example = "21", in = ParameterIn.PATH)},
            externalDocs = @ExternalDocumentation(url = "#", description = "For more details check the link")
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower deleted correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})}),
            @ApiResponse(responseCode = "404", description = "Flower id is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})})
    })
    public ResponseEntity<FlowerDTO> deleteFlower(@Valid @PathVariable Long id) {
        if (flowerService.deleteFlower(id)) return ResponseEntity.status(200).body(null);
        return ResponseEntity.status(404).body(null);
    }

    //http://localhost:9001/flowers/getOne/{id}
    @GetMapping("/getOne/{id}")
    @Operation(
            tags = {"Sprint 5.2"},
            operationId = "getOneFlower",
            summary = "Find a flower by id in database.",
            description = "needs the variable id in the path, find a flower by id in database and return his FlowerDTO if is present, or null if is not present. Finally, the expected responses are 200 and 404",
            parameters = {@Parameter(name = "id", description = "Id from the path variable", example = "21", in = ParameterIn.PATH)},
            externalDocs = @ExternalDocumentation(url = "#", description = "For more details check the link")
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower showed correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Flower id is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})})
    })

    public ResponseEntity<FlowerDTO> getOneFlower(@Valid @PathVariable Long id) {
        if (flowerService.getOneFlowerById(id) != null) return ResponseEntity.status(200).body(flowerService.getOneFlowerById(id));
        return ResponseEntity.status(404).body(null);
    }

    //http://localhost:9001/flowers/getAll
    @GetMapping("/getAll")
    @Operation(
            tags = {"Sprint 5.2"},
            operationId = "getAllFlowers",
            summary = "Find list of flowers.",
            description = "return all instances of entity Flower. Finally, the expected responses are 200 and 404",
            externalDocs = @ExternalDocumentation(url = "#", description = "For more details check the link")
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower list showed correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "There is no flowers in database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = FlowerDTO.class), examples = {@ExampleObject(name = "example", value = "null")})})
    })

    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        if (flowerService.getAllFlowers() != null) return ResponseEntity.status(200).body(flowerService.getAllFlowers());
        return ResponseEntity.status(404).body(null);
    }



}










