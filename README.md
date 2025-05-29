# BanGiayScrum2

**BanGiayScrum2** is a final university project designed to demonstrate the integration of automation tools in software testing and development workflows, with a focus on utilizing Jenkins for continuous integration and continuous deployment (CI/CD).

This project enables testers to automate the testing process by simply updating the `test.js` file and committing the changes. Once committed, Jenkins automatically triggers the testing process without requiring any manual intervention.

## Project Overview

This `Jenkinsfile` defines a CI/CD pipeline that automates the following key tasks:

- **Environment Setup**  
  Initializes necessary environment variables for Redmine integration (such as API key and URL), and configures the Node.js environment on Jenkins.

- **Source Code Management**  
  Clones the designated branch from the GitHub repository to prepare for the build process.

- **Redmine Integration**  
  Automatically extracts the Redmine Issue ID from the latest commit message to associate Jenkins builds with relevant Redmine issues.

- **Dependency Installation**  
  Executes `npm install` to install all required Node.js dependencies.

- **Automated Testing**  
  Runs `npm test` to perform automated tests on the application.

- **Build Notifications**  
  Sends email notifications to inform stakeholders about the build status—whether successful or failed.

- **Redmine Status Updates**  
  Updates the status of the related Redmine issue automatically:
  - Marks it as **"Resolved"** if the build succeeds.
  - Marks it as **"Feedback"** if the build fails.

## Summary

This pipeline significantly streamlines the software development workflow by automating the processes of building, testing, notifying, and updating Redmine issue statuses—all triggered by a simple code commit. It enhances productivity, reduces manual tasks, and ensures better integration between development and issue tracking.
