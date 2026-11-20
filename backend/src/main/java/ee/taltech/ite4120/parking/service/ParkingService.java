package ee.taltech.ite4120.parking.service;

import ee.taltech.ite4120.parking.model.ParkingPermit;
import ee.taltech.ite4120.parking.repository.ParkingRepository;
import java.time.LocalDate;
import org.helex.commons.exception.ConflictException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
}

    @Transactional
    public Long createApplication(
            String personalCode,
            LocalDate applicationDate) {

        return repository.insertApplication(
                personalCode,
                applicationDate);
       }

    @Transactional
    public Long createApprovedApplication(
            String personalCode,
            LocalDate applicationDate) {

        Long applicationId =
                repository.insertApplication(personalCode, applicationDate);

        repository.insertApprovedDecision(applicationId, applicationDate);

        return applicationId;
    }

    @Transactional
    public ParkingPermit issuePermit(
            Long applicationId,
            String permitNumber,
            LocalDate validFrom,
            LocalDate validUntil) {

        // Business rule:
        // A parking permit can only be issued for an APPROVED application.
        if (!repository.hasApprovedDecision(applicationId)) {
            throw new ConflictException(
                    "parkingPermit",
                    "parking permit cannot be issued without an APPROVED decision");
        }

        Long permitId = repository.insertPermit(
                applicationId,
                permitNumber,
                validFrom,
                validUntil);

        return repository.loadPermit(permitId);
    }

}
