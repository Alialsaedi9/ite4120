# Work Diary – Arushi David

## 15.10.2026

**Time spent:** approximately 2 hours

### Activities
- Reviewed the Accessible Parking Permit Register User Story and compared it with the existing data model from the municipal officer and business perspective.
- Checked that the application, decision and permit data model supports the main business flow described in the User Story.
- Identified open specification questions concerning application status, permit status and permit validity dates.
- Added UI requirements for creating and viewing applications, recording decisions, issuing permits, handling invalid permit issuance and viewing permit details.
- Documented the existing business rules and open business questions in the project specification.
- Reviewed the relevant TEHIK non-functional requirements for the Accessible Parking Permit Register.
- Added applicable non-functional requirements concerning REST API versioning, Liquibase database migrations, WCAG 2.2 Level AA accessibility, error handling, database relationships, personal data in logs and secure communication.

### Problems and corrections
- Identified that the allowed values and transitions for `permit_application.status` and `parking_permit.status` are not yet defined in the specification.
- Identified that the validation relationship between `valid_from` and `valid_until` needs clarification.
- Avoided defining new status values or business rules without agreement from the group.

### AI assistance
- Used AI assistance to help identify possible gaps in the specification and formulate UI requirements.

### What I learned
- Learned how a User Story can be traced to the data model, UI requirements and business rules.
- Learned the difference between functional and non-functional requirements.
- Learned that unclear business rules should be documented as open questions rather than assumed during implementation.
- Learned how TEHIK requirements such as accessibility, database versioning, error handling and personal-data protection can apply to an e-government information system.

### Next steps
- Discuss the open business questions with the group.
- Review any feedback from Ali and Kadri on the specification changes.
- Continue with the next implementation tasks assigned after Session 3.