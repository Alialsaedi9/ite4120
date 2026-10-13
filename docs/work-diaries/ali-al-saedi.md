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
