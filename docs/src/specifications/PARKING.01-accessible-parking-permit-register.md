# PARKING.01 · Accessible Parking Permit Register

> **PARKING.01** · state **Draft** · traces from [PARKING-US-001](../user-stories/PARKING-US-001.md)

## Description

A register for accessible parking permit applications, decisions and issued permits.

The permit is linked to a person, not to a specific vehicle. The register stores only the person's personal identity code. Current personal information, such as name and address, is retrieved from the Population Register through a registry adapter, using X-Road in production. The Population Register remains the source of truth for personal data.

A municipal officer creates a permit application, records an approval or rejection decision and, when the application is approved, issues an accessible parking permit.

## Data model *(→ session 2)*

| Table | Field | Type | Rules |
| --- | --- | --- | --- |
| `parking.permit_application` | `id` | bigint | PK |
| | `personal_code` | varchar(20) | required |
| | `application_date` | date | required |
| | `status` | varchar(30) | required |
| | `reason` | varchar(500) | optional |
| `parking.permit_decision` | `id` | bigint | PK |
| | `application_id` | bigint | required; FK → `permit_application.id` |
| | `decision_date` | date | required |
| | `decision_type` | varchar(20) | required; `APPROVED` or `REJECTED` |
| | `decision_reason` | varchar(500) | optional |
| `parking.parking_permit` | `id` | bigint | PK |
| | `application_id` | bigint | required; FK → `permit_application.id` |
| | `permit_number` | varchar(50) | required; unique |
| | `valid_from` | date | required |
| | `valid_until` | date | required |
| | `status` | varchar(20) | required |

## Validation and error handling

The system must validate all requests before storing or changing data.

- `personal_code` is required and must contain exactly 11 digits.
- A decision can only be recorded for an existing permit application.
- `decision_type` must be either `APPROVED` or `REJECTED`.
- A parking permit can only be issued when the related application has an `APPROVED` decision.
- `permit_number` must be unique.
- `valid_until` must not be earlier than `valid_from`.

The REST API must return consistent error responses:

- `400 Bad Request` when input data is missing or invalid.
- `404 Not Found` when the requested permit application does not exist.
- `409 Conflict` when the requested operation conflicts with the current state, for example when attempting to issue a parking permit without an `APPROVED` decision.

## UI and business requirements

### UI requirements

**UI-01 · Create application**

The system shall provide the municipal officer with a form for creating a permit application. The form shall allow the officer to enter the person's personal identity code, application date and optional reason.

**UI-02 · Application details**

The system shall provide the municipal officer with a view of a permit application's details and current status. Current personal information retrieved through the registry adapter may be displayed when available but shall not be stored locally by the Parking Register.

**UI-03 · Record decision**

The system shall allow the municipal officer to record an `APPROVED` or `REJECTED` decision for an application. The interface shall support the decision date and optional decision reason defined in the data model.

**UI-04 · Issue permit**

The system shall provide the municipal officer with an action for issuing a parking permit for an application with an `APPROVED` decision.

**UI-05 · Prevent invalid permit issuance**

If the municipal officer attempts to issue a parking permit for an application without an `APPROVED` decision, the permit shall not be created and the interface shall display a clear error message.

**UI-06 · Permit details**

The system shall display an issued permit's unique permit number, validity period and current status.

### Existing business rules

The UI shall respect the business rules already defined by the User Story and specification:

- A permit decision is either `APPROVED` or `REJECTED`.
- A parking permit may only be issued for an application with an `APPROVED` decision.
- The permit is linked to a person rather than to a specific vehicle.
- Current personal information such as name and address is retrieved through the registry adapter when needed and is not stored locally by the Parking Register.

### Open business questions

The following points require clarification before implementation:

- Define the allowed values and transitions for `permit_application.status`.
- Define the allowed values and transitions for `parking_permit.status`.

## Relevant non-functional requirements

The following TEHIK non-functional requirements are relevant to the Accessible Parking Permit Register:

- **NFR-01 · API versioning:** The REST API shall support versioning in accordance with TEHIK requirement 3.3.
- **NFR-02 · Database migrations:** Database changes shall be automated and versioned using Liquibase, in accordance with TEHIK requirement 3.7.
- **NFR-03 · Accessibility:** The web user interface shall comply with WCAG 2.2 Level AA, in accordance with TEHIK requirement 4.1.
- **NFR-04 · Error handling:** User-facing errors shall be presented as clear, user-friendly error messages, in accordance with TEHIK requirement 4.6.
- **NFR-05 · Database relationships:** Relational references shall use foreign keys and each table shall have a defined primary key, in accordance with TEHIK requirements 6.3 and 6.6.
- **NFR-06 · Personal data and logging:** System logs shall not contain direct personal data, in accordance with TEHIK requirement 8.12.
- **NFR-07 · Secure communication:** Data transmission shall use appropriately protected channels, in accordance with TEHIK requirement 9.6.



## Business tests

1. **A parking permit can be issued for an approved application.**

   Given a person has a permit application with an `APPROVED` decision  
   When the municipal officer issues a parking permit for that person  
   Then a parking permit is created for the person with a unique permit number, validity period, and status
2. **A parking permit cannot be issued without an approved decision.**

   Given a person has a permit application without an `APPROVED` decision  
   When the municipal officer tries to issue a parking permit for that person  
   Then the request is rejected and no parking permit is created
