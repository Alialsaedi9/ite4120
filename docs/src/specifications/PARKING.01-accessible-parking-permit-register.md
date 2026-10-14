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

## Business tests

1. **A parking permit can be issued for an approved application.**

   Given a person has a permit application with an `APPROVED` decision  
   When the municipal officer issues a parking permit for that person  
   Then a parking permit is created for the person with a unique permit number, validity period, and status
2. **A parking permit cannot be issued without an approved decision.**

   Given a person has a permit application without an `APPROVED` decision  
   When the municipal officer tries to issue a parking permit for that person  
   Then the request is rejected and no parking permit is created
