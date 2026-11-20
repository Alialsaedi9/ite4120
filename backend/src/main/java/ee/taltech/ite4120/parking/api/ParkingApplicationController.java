
package ee.taltech.ite4120.parking.api;

import ee.taltech.ite4120.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking/applications")
@Tag(
    name = "Parking",
    description = "Accessible parking permit applications"
)
public class ParkingApplicationController {

    private final ParkingService service;

    public ParkingApplicationController(ParkingService service) {
        this.service = service;
    }

    @Operation(summary = "Create a parking permit application")
    @ApiResponse(responseCode = "201", description = "Application created")
    @ApiResponse(responseCode = "400", description = "Invalid application data")
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody ParkingApplicationRequest body) {

        Long applicationId = service.createApplication(
                body.getPersonalCode(),
                body.getApplicationDate()
        );

        return ResponseEntity
                .created(URI.create("/api/parking/applications/" + applicationId))
                .build();
    }
}
