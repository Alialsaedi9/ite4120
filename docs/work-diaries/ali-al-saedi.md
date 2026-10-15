# Work Diary – Ali Al Saedi

## 06.10.2026

**Time spent:** approximately 3 hours

### Activities
- Reviewed the ITE4120 course requirements and assessment criteria.
- Reviewed the course GitHub repository and the Animals Register example.
- Cloned the ITE4120 course repository to my local computer.
- Installed and configured the required development tools, including Homebrew, OpenJDK 25, Node.js, npm, and Docker.
- Configured GitHub Packages authentication using a classic Personal Access Token with `read:packages` permission.
- Configured npm access to the `@helex-solutions/ui` package.
- Installed the frontend dependencies.
- Started the backend and verified the REST API using Swagger UI.
- Started the frontend and verified that the Animals Register application runs locally.
- Reviewed the example application's structure, including the frontend, REST API, and mock registry.
- Coordinated with my group regarding the next course tasks and deadlines.

### Problems and corrections
- Docker Desktop required additional setup before the development environment could run correctly.
- The first GitHub Personal Access Token did not have the required `read:packages` permission.
- npm returned `401 Unauthorized` and `403 Forbidden` errors while accessing GitHub Packages.
- Created a new classic GitHub Personal Access Token with the correct `read:packages` scope and updated the npm configuration.
- After correcting the token configuration, `npm install` completed successfully and the frontend started correctly.

### AI assistance
- Used ChatGPT as a step-by-step guide for understanding the course requirements and setting up the development environment.
- Used AI assistance to diagnose Docker, Java, npm, and GitHub Packages configuration issues.
- Verified the suggested steps manually by running the commands and checking the resulting application.

### What I learned
- Understood the basic structure of the course template and how the frontend, backend, database, REST API, and registry integration relate to each other.
- Learned how GitHub Packages authentication is used to access the Helex UI dependency.
- Learned how to run and verify the example application locally.
- Understood that our own component must be specification-driven and that individual work must remain traceable through commits and the work diary.

### Next steps
- Agree with the group on our project domain and first user story.
- Prepare the project specification.
- Design the data model with at least three tables and a foreign-key relationship.
- Define the first business tests using Given / When / Then.

## 15.10.2026

**Time spent:** approximately 2 hours

### Activities
- Continued development of the Accessible Parking Permit Register with the group.
- Reviewed the parking permit specification and database schema.
- Confirmed that the permit is linked to a person rather than to a specific vehicle.
- Reviewed the three-table data model and the foreign-key relationships between permit applications, decisions, and issued permits.
- Refined two business tests into clear Given / When / Then scenarios.
- Defined the positive case where a permit can be issued after an `APPROVED` decision.
- Defined the negative case where issuing a permit without an `APPROVED` decision must be rejected.
- Added validation requirements for personal codes, decisions, permit numbers, and permit validity dates.
- Added consistent REST API error-handling requirements using `400 Bad Request`, `404 Not Found`, and `409 Conflict`.
- Coordinated the division of work with the group and helped establish shared GitHub repository access for group collaboration.

### Decisions and reasoning
- Kept the parking permit person-centric because the permit belongs to the person and is not tied to a specific vehicle.
- Used `409 Conflict` when a request is structurally valid but conflicts with the current application state, such as attempting to issue a permit without an approved decision.
- Kept the business tests in plain-language Given / When / Then format so they can later be converted into executable tests.

### Problems and corrections
- Initially inserted the validation section at the wrong position in the specification and accidentally replaced the document heading.
- Detected the problem using `git diff` before committing.
- Restored the file from Git and inserted the validation section again in the correct location.
- Verified the changes before creating the commit.

### AI assistance
- Used ChatGPT to review the specification structure and discuss suitable validation and error-handling rules.
- Used AI assistance to refine the business tests and explain the distinction between validation and REST API error handling.
- Used AI assistance for Git commands and to diagnose and correct the misplaced specification section.
- Reviewed and verified the suggested changes manually before committing them.

### What I learned
- Learned how business requirements can be expressed as Given / When / Then scenarios and later converted into automated tests.
- Better understood the difference between input validation, business-rule validation, and REST API error responses.
- Learned why `git diff` should be used to review changes before staging and committing them.
- Improved my understanding of collaborative Git workflows and the importance of traceable individual commits.

### Next steps
- Review the group members' contributions and integrate them into the project.
- Prepare the REST API/OpenAPI requirements and API surface for the parking component.
- Convert the business tests into executable tests during the next development stage.
- Verify the required X-Road integration approach for the project.
