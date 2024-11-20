# esport-web-app

## Technologies Used

This application is built using **Maven** and **Spring Boot**, with the following dependencies:
- Spring Data JPA
- Spring Web
- Spring Boot DevTools
- Validation
- Lombok
- H2 Database

## Development Environment

I developed this application using **Eclipse Java EE IDE** because it is open source and well-suited for Java development. However, you can use any IDE compatible with Spring Boot applications, such as IntelliJ IDEA.

## How to Build and Run

1. Clone this repository to your local machine.
2. Open your IDE of choice.
3. Import the project as a Maven project.
4. Enable Annotation Processing for Lombok
5. Run the Spring Boot application using the IDE's run configuration.

### Instructions for Eclipse Java EE IDE

1. Import as Existing Maven Projects

![image](https://github.com/user-attachments/assets/a03db232-811d-4f90-9d7a-bb56a8380cb8)

2. Set Up Lombok in Eclipse

   To enable Lombok annotations in Eclipse, follow these steps:

   - Step 1: Download Lombok 
     Download the Lombok jar file from this [link](https://projectlombok.org/downloads/lombok.jar).

   - Step 2: Install Lombok 
     1. Open a terminal or command prompt and navigate to the folder where the jar file is located.
     2. Run the following command:  
        ```bash
        java -jar lombok.jar
        ```
     3. In the Lombok installation window:
        - Lombok installer will find your `eclipse.exe` file or you can click **Specify Location** and locate it in your Eclipse installation folder.
        - Click **Install/Update**.

   - Step 3: Enable Annotation Processing
     Annotation processing must be activated in the project settings:
     1. Right-click on the project in Eclipse and go to **Properties**.
     2. Navigate to **Java Compiler → Annotation Processing**.
     3. Check **Factory Path**.
     4. Enable all checkboxes under this section.

    ![image](https://github.com/user-attachments/assets/587fe676-6b9e-4212-8d41-3ac3b6ec0c49)

   - Step 4: Restart Eclipse
     Restart Eclipse to ensure the changes take effect.

3. Right click on project and Run as Spring Boot App

![image](https://github.com/user-attachments/assets/8ec127df-5781-4a4a-8d71-fd7a87d9525c)
