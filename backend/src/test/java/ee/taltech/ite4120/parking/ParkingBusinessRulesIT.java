package ee.taltech.ite4120.parking;

import static org.assertj.core.api.Assertions.assertThat;

import ee.taltech.ite4120.parking.model.ParkingPermit;
import ee.taltech.ite4120.parking.service.ParkingService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Executable business tests for the Accessible Parking Permit Register.
 * These tests originate from the plain-language business tests in the
 * parking specification.
 */
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class ParkingBusinessRulesIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:18-alpine");

    @Autowired
    ParkingService service;

    @Test
    void aParkingPermitCanBeIssuedForAnApprovedApplication() {
        // Given a person has a permit application with an APPROVED decision
        Long applicationId = service.createApprovedApplication(
                "39001010015",
                LocalDate.now());

        // When the municipal officer issues a parking permit for that person
        ParkingPermit permit = service.issuePermit(
                applicationId,
                "PARK-IT-001",
                LocalDate.now(),
                LocalDate.now().plusYears(1));

        // Then a parking permit is created with a unique permit number,
        // validity period, and ACTIVE status
        assertThat(permit.getId()).isNotNull();
        assertThat(permit.getApplicationId()).isEqualTo(applicationId);
        assertThat(permit.getPermitNumber()).isEqualTo("PARK-IT-001");
        assertThat(permit.getValidFrom()).isEqualTo(LocalDate.now());
        assertThat(permit.getValidUntil()).isEqualTo(LocalDate.now().plusYears(1));
        assertThat(permit.getStatus()).isEqualTo("ACTIVE");
    }
}
