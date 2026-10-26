package ee.taltech.ite4120.parking.repository;

import ee.taltech.ite4120.parking.model.ParkingPermit;
import java.time.LocalDate;
import org.helex.commons.db.bean.PgBeanProcessor;
import org.helex.commons.db.repo.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingRepository extends BaseRepository {

    private final PgBeanProcessor permitBp =
            new PgBeanProcessor(ParkingPermit.class);

    public Long insertApplication(String personalCode, LocalDate applicationDate) {
        String sql = """
                insert into parking.permit_application
                    (personal_code, application_date)
                values (?, ?)
                returning id
                """;

        return queryForObject(
                sql,
                Long.class,
                personalCode,
                applicationDate);
    }

    public Long insertApprovedDecision(Long applicationId, LocalDate decisionDate) {
        String sql = """
                insert into parking.permit_decision
                    (application_id, decision_date, decision_type)
                values (?, ?, 'APPROVED')
                returning id
                """;

        return queryForObject(
                sql,
                Long.class,
                applicationId,
                decisionDate);
    }

    public boolean hasApprovedDecision(Long applicationId) {
        Integer count = queryForObject(
                """
                select count(1)
                  from parking.permit_decision
                 where application_id = ?
                   and decision_type = 'APPROVED'
                """,
                Integer.class,
                applicationId);

        return count != null && count > 0;
    }

    public Long insertPermit(
            Long applicationId,
            String permitNumber,
            LocalDate validFrom,
            LocalDate validUntil) {

        String sql = """
                insert into parking.parking_permit
                    (application_id, permit_number, valid_from, valid_until, status)
                values (?, ?, ?, ?, 'ACTIVE')
                returning id
                """;

        return queryForObject(
                sql,
                Long.class,
                applicationId,
                permitNumber,
                validFrom,
                validUntil);
    }

    public ParkingPermit loadPermit(Long id) {
        return getBean(
                """
                select *
                  from parking.parking_permit
                 where id = ?
                """,
                permitBp,
                id);
    }

    public int countPermitsForApplication(Long applicationId) {
        Integer count = queryForObject(
                """
                select count(1)
                  from parking.parking_permit
                 where application_id = ?
                """,
                Integer.class,
                applicationId);

        return count == null ? 0 : count;
    }
}
