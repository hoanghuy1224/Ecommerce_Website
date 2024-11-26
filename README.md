**Ecommerce Store** is a online shopping project built with **Java Spring Boot MVC** and **Thymeleaf**. This web application provides a good user experience through a frontend developed with **HTML, CSS, JavaScript,** and **jQuery**, including powerful **jQuery DataTables** for enhanced data management.

The backend used **Spring Boot** for clean, structured code with a **service layer**, **Spring Data JPA** for efficient database operations using **MySQL**, and **Spring Security** to protect user data with **authentication** and **role-based authorization**. Optimized with **Lombok** to reduce boilerplate code, this project is packaged as a **WAR** file for easy deployment on any servlet container, like **Apache Tomcat**.

# Features
  - **Spring Boot MVC Architecture:** This project follows a well-organized architecture with Service, Repository, and Controller layers to ensure clean code, easy maintenance, and scalability.
  - **Frontend Technologies:**
      -  **Thymeleaf:** Server-side Java templating engine for rendering dynamic HTML pages.
      - **HTML5/CSS3:** Responsive design for modern browsers and mobile devices.
      - **JavaScript & jQuery:** Enhance user interactivity with dynamic elements.
      - **jQuery DataTables:** Efficient data handling and display with pagination, sorting, and search functionalities.
  
 - **Backend Technologies:**
      - **Java Spring Boot (3.3.2):** Core framework for the application backend, handling business logic and database interaction.
      - **Spring Data JPA:** Simplified data access and database queries with custom JPA finder methods.
      - **MySQL:** Relational database used for data persistence.
      - **Spring Security:** Secure the application with user authentication and role-based authorization.
      - **Lombok:** Simplifies code with annotations to eliminate boilerplate code (like **getters**, **setters**, **constructors**).
      - **Maven:** Build tool for project management and dependency management.
      - **Java Mail Sender:** Send Email for Password Reset.
  
 - **Security Features:**
      - User authentication and authorization with Spring Security.
      - Protected routes and roles-based access control.
  
 - **Deployment:** Packaged as a **WAR** file, suitable for deployment on any servlet container like Apache Tomcat.

 - **Modern Development Practices:**
      - Spring Boot DevTools for faster development and hot reload.
      - Custom JPA Queries for efficient data retrieval and management.



# Requirements
  - Java 17 (project setup supports Java 17 but can be adjusted for Java 8 environments)
  - MySQL
  -Maven
  -Apache Tomcat (for WAR deployment)

# Installation
   **1. Clone the repository:**
```bash
git clone https://github.com/mdtalalwasim/Ecommerce_Store.git
```
   **2. Navigate to the project directory:**
```bash
cd Ecommerce_Store
```
  **3. Set up the MySQL database:**
```bash
CREATE DATABASE ecommerce_store;
```
  **4. Configure database connection settings in application.properties:**
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_store
spring.datasource.username=your-username
spring.datasource.password=your-password
```
**5. Run the application:** 
**Access the app at http://localhost:8080.**


More keyword related to this project: 
- Ecommerce Store Java
- Spring Boot MVC Ecommerce
- Thymeleaf Spring Boot
- Ecommerce website Spring Security
- Java Spring Boot Shopping Cart
- MySQL Spring Boot Ecommerce
- Ecommerce Project with Java and MySQL
- Java Ecommerce Repository
- Spring boot Ecommerce Repository


# Project Screenshots are given below: 
_**Note : Project is Not Fully Completed yet, Still More Modules are in under development. The project will updated day by day._

**Index Page:**

![index-page](https://github.com/user-attachments/assets/a6cef969-4330-4ca4-853c-9ce6053f632a)
![index-page-02](https://github.com/user-attachments/assets/f5b03507-b663-4219-8fb3-f196cb666c24)
![index-page-03](https://github.com/user-attachments/assets/80dfd50a-f97b-47bb-b787-a19f4188d938)
![Index-page-04-with-category-Dropdown](https://github.com/user-attachments/assets/565dcb55-640b-4873-a41f-af4a7580d62a)


**Register Page**

![Register-User-Page](https://github.com/user-attachments/assets/0da3cff5-67b3-477e-9ca3-e2bd1dab0b63)


**Login Page:**

![Login Page](https://github.com/user-attachments/assets/9fe201bd-110f-4401-8453-d7db2987243a)


**Admin Dashboard**

![Admin-Dashboard](https://github.com/user-attachments/assets/21f43839-2490-4e43-a742-de26f2932a7b)


**User:**

![User-Login-Page](https://github.com/user-attachments/assets/c3ef8cc8-c68c-4ef3-a754-54bae322c9f6)


**User After Login:**

![User_Login_Index_Page](https://github.com/user-attachments/assets/720873b4-638a-4b7e-852f-97ea7fa70433)


**User Disable Feature :**


![WhenUser-Disable](https://github.com/user-attachments/assets/a84c2068-e606-4ad3-a7b5-46a27be25755)


**Wrong Password Enter:**

![WrongPasswordLoginTry](https://github.com/user-attachments/assets/97d1e4e5-dfbd-42ec-a198-ea5cf764e007)


**Logout:**

![LogoutSuccefully](https://github.com/user-attachments/assets/4c9e3fe3-3109-40d4-9233-6e078c454b6d) 


**Category Module:**

**Category Home:**

![All_category-List](https://github.com/user-attachments/assets/b3dbd9da-3a76-4461-ae33-290b0eeef686)

**ADD Category:**

![Add-a-Category](https://github.com/user-attachments/assets/f82f9e24-090c-4744-a289-617a51189441)

**Edit Category**

![Edit-a-Category](https://github.com/user-attachments/assets/e5a81c82-ceb7-4254-a39b-7839d8baf5d1)


**Search Category**

![Search-Category-in-Category-List](https://github.com/user-attachments/assets/c5399f1b-d4f9-4479-b97c-d3616d5116b2)


**Product Module**


**Product Home**

![product-list](https://github.com/user-attachments/assets/e36339be-0546-4bb1-8a54-3beb4c1cc63f)

**Add Product Page**

![add-product-form](https://github.com/user-attachments/assets/8a5fb013-24a8-4c9c-9ea6-36120a391d40)

![add-product-form-fillup](https://github.com/user-attachments/assets/6b1b3988-a94e-4134-b0fc-fe4366c50132)

**Product Added**

![product-save-successfully-home](https://github.com/user-attachments/assets/400a7c96-795d-4342-a881-bbd3468aeebe)

**Product Home - List of All Product**
![ListOfAll-Products](https://github.com/user-attachments/assets/afccf91c-f28f-4e7d-aabd-80cb35dd3a65)

**Edit Product Module**
**Edit Product with Discount**
![Edit-Product-With-10%-Discount](https://github.com/user-attachments/assets/eae191f1-557a-4374-b4d6-94c31686794d)

**Product Updated with Discount**

![Product-Update-With-Discount-Price](https://github.com/user-attachments/assets/ab2f34b6-2f6a-48ce-a050-3062ece4381e)


**After Adding 10% Discount**

![Product-List-many](https://github.com/user-attachments/assets/be0a8bbf-6a36-4e06-82fb-52839917ee75)

![ListOfAll-Products](https://github.com/user-attachments/assets/882a1395-993e-42e1-bef5-40fcc787dee3)

**Product Details**

![Single-Product-Details-Page](https://github.com/user-attachments/assets/96781427-3acb-4a36-bf8c-69d6135f5912)

![Single-product-details-page-02](https://github.com/user-attachments/assets/11ca6bca-e2fd-46fd-aaac-3f641d9d9bff)

![Details-Product-Blazer](https://github.com/user-attachments/assets/c46456db-35ee-437d-90e4-3b5c90435cc6)

![Details-product-with-discount](https://github.com/user-attachments/assets/262c73e3-5d2d-46f9-88a2-4bdc16539c2a)


**Out of Stock Product**

![Out-of-Stock-Products](https://github.com/user-attachments/assets/8ff390c7-c8bc-4f53-baed-d6d43f0e9160)



**Product Category Filter**

_Category Wise Product Filtering_

**All Category**

![All_category-List](https://github.com/user-attachments/assets/91a9937b-10d2-44b5-9b78-b28725b64deb)

**All Products - All Categories Product**

![Category-All-Product-with-Discount](https://github.com/user-attachments/assets/18d5393b-d3cf-46cd-9f45-98b592e07744)

**Filter Category wise products**

Casual Shirt:

![Category-wise-casual-Shirts](https://github.com/user-attachments/assets/17457da9-e1cd-47b6-9df7-cbd9efc92f9e)

KnitWear:

![Category-wise-knit-wear](https://github.com/user-attachments/assets/eb3b9026-4bc2-4592-8d92-028dc96d2a53)

Blazer :

![Category-wise-blazer](https://github.com/user-attachments/assets/ea3dbe35-1946-4c2b-b016-a3f33ad579aa)

Panjabi:

![Category-wise-panjabi-product](https://github.com/user-attachments/assets/c4fd2738-d94e-49e1-8f5d-3a8274903fb7)

Pants:

![Category-Wise-Pants](https://github.com/user-attachments/assets/dc0bad70-e341-4b6d-ab8b-c18ab67421db)

Accessories:

![Category-wise-accessories](https://github.com/user-attachments/assets/0b5fa8f7-9454-4dd6-a5cb-54aaca113aa2)

No Product Found for This Category:

![No-Product-with-this-category](https://github.com/user-attachments/assets/99781cda-dd1e-413e-bfd3-9fbbbff66491)

**Forgot Password:**
![Screenshot from 2024-10-17 21-40-50](https://github.com/user-attachments/assets/72b3f8ed-f084-4b22-a2dc-41988e676196)

![Screenshot from 2024-10-17 21-40-59](https://github.com/user-attachments/assets/8b8c1758-723d-4c82-9f3c-e4128d6c2c8d)

