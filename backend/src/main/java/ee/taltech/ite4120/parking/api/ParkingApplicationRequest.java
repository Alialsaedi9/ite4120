
package ee.taltech.ite4120.parking.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class ParkingApplicationRequest {

    @NotBlank(message = "Personal identity code is required")
    @Pattern(
        regexp = "\\d{11}",
        message = "Personal identity code must contain exactly 11 digits"
    )
    private String personalCode;

    @NotNull(message = "Application date is required")
    private LocalDate applicationDate;

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
}
