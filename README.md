###### Readme-top
<!-- CONTACT -->
## Contact
![Gmail][gmail.com] -  brentliu0@gmail.com

[![LinkedIn][linkedIn.com]][linkedIn-url] - https://www.linkedin.com/in/brent-liu-18a408196/

![Github][github.com] - https://github.com/Liubre82/restaurant-ordering-app


# Restaurant-Management-Application

<!-- ABOUT THE PROJECT -->
## About The Project
Restaurant Ordering Management is a backend application that focuses on creating REST apis for a restaurant for online ordering. The application
has authentication and authorization that are implemented using Spring security and JWT. The application CRUD functionalities for various different services
for example: a menu for the restaurant with images, food descriptions, pricing, food variations, etc. 
Users can write reviews, browse their order history, the menu, etc.

Implemented Unit Tests for controller routes in Junit 5.

created a CI/CD pipeline for the application, the servers are all deployed on aws as well as the application will be deployed on 
AWS although not accessible unless I start the instances.

This was a project I created to practice and learn more skills as a back-end developer.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Built With

* [![Java][java.com]][java-url]
* [![Spring][Spring.io]][Spring-url]
* [![SpringBoot][SpringBoot.io]][SpringBoot-url]
* [![SpringSecurity][SpringSecurity.com]][SpringSecurity-url]
* [![MySql][mysql.com]][MySql-url]
* [![Hibernate][Hibernate.com]][Hibernate-url]
* [![Junit5][Junit5.com]][Junit5-url]
* [![JWT][JWT.io]][JWT-url]
* [![Maven][maven.com]][maven-url]
* [![Jenkins][Jenkins.com]][Jenkins-url]
* [![Sonarqube][Sonarqube.com]][Sonarqube-url]
* [![Docker][Docker.com]][Docker-url]
* [![TrivyScan][trivy.com]][trivy-url]
* [![Argo][Argo.com]][Argo-url]
* [![Kubernetes][Kubernetes.com]][Kubernetes-url]
* [![AWS][AWS.com]][AWS-url]
* [![Intellij][intellij.com]][intellij-url]
    ## CI/CD Pipeline Diagram (Deployed on AWS)

    [![CICD][cicdImage]][cicdImage-url]

    ## MySql Schema ER Diagram

    [![MySqlERD][MySqlERD.com]][MySqlERD-url]
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

setting up your project locally. To get a local copy up and running follow these steps.


### Setup

* Create and set up your own MySql server, I used version 8.0.36 on my local machine. schema.sql
and data.sql are is in the ~/restaurant-ordering-app/src/main/resources package for datatable creation and data population.

   ```sh
   https://dev.mysql.com/downloads/installer/
   ```

* set up your environment variables to connect to your MySql DB 
   ```sh
  spring.datasource.url=jdbc:mysql://localhost:3306/restaurant? #restaurant is the database name I used & named.
  spring.datasource.username=
  spring.datasource.password=
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
  spring.jpa.show-sql=true
  #spring.jpa.hibernate.ddl-auto=create
   ```
### Installation

1. Clone the repo
   ```sh
   gh repo clone Liubre82/restaurant-ordering-app
   ```

2. Install Maven Dependencies. In `restaurant-ordering-app`  directory
    ```sh
    cd restaurant-ordering-app
    maven clean install
    ```

### Deployment

* Run RestaurantOrderingAppApplication.main() to start the application.


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[firebase-config-img]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FfirebaseConfig(readme.md).png?alt=media&token=643cf4c1-72ae-4dd0-a3a9-442b663dd255
[firebase-config-img-url]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FfirebaseConfig(readme.md).png?alt=media&token=643cf4c1-72ae-4dd0-a3a9-442b663dd255
[urlParams.js]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FuserIdparams(forReadMe.md).png?alt=media&token=9c07515c-d365-4ecc-8e0c-9071e7d3ba2c
[urlParams-url]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FuserIdparams(forReadMe.md).png?alt=media&token=9c07515c-d365-4ecc-8e0c-9071e7d3ba2c
[project.io]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2Fproject%20image.png?alt=media&token=5b717eb0-73d7-4be2-b7d9-fbe989bbec80
[project-url]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2Fproject%20image.png?alt=media&token=5b717eb0-73d7-4be2-b7d9-fbe989bbec80
[projectRender-io]: https://estate-finder-mern.onrender.com/
[projectRender-url]: https://estate-finder-mern.onrender.com/
[linkedIn.com]: https://img.shields.io/badge/LinkedIn-0A66C2?logo=linkedin&logoColor=fff&style=flat
[linkedIn-url]: https://www.linkedin.com/in/brent-liu-18a408196/
[cicdImage]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FCICDPipeline(Restaurant-app).png?alt=media&token=4d42c95f-34c1-451a-a8bf-620d4e7aec4f
[cicdImage-url]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FCICDPipeline(Restaurant-app).png?alt=media&token=4d42c95f-34c1-451a-a8bf-620d4e7aec4f
[MySqlERD.com]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FMySql%20Schema%20ER%20Diagram-1.png?alt=media&token=77fca807-5d7d-41cd-9a2d-fad017baad18
[MySqlERD-url]: https://firebasestorage.googleapis.com/v0/b/mern-real-estate-9a14b.appspot.com/o/SeedsImages%2FMySql%20Schema%20ER%20Diagram-1.png?alt=media&token=77fca807-5d7d-41cd-9a2d-fad017baad18

[MySql.com]: https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white
[MySql-url]: https://www.mysql.com/
[java.com]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]: https://java.com/
[JWT.io]: https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink
[JWT-url]: https://jwt.io/
[Spring.io]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: spring.io
[SpringBoot.io]: https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white
[SpringBoot-url]: https://spring.io/projects/spring-boot
[AWS.com]: https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white
[AWS-url]: https://aws.amazon.com/
[SpringSecurity.com]: https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white
[SpringSecurity-url]: https://spring.io/projects/spring-security
[Jenkins.com]: https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white
[Jenkins-url]: https://www.jenkins.io/
[Junit5.com]: https://img.shields.io/badge/JUnit5-25A162.svg?style=for-the-badge&logo=JUnit5&logoColor=white
[Junit5-url]: https://junit.org/junit5/
[Docker.com]: https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white
[Docker-url]: https://www.docker.com/
[Sonarqube.com]: https://img.shields.io/badge/SonarQube-4E9BCD.svg?style=for-the-badge&logo=SonarQube&logoColor=white
[Sonarqube-url]: https://www.sonarsource.com/products/sonarqube/
[Kubernetes.com]: https://img.shields.io/badge/Kubernetes-326CE5.svg?style=for-the-badge&logo=Kubernetes&logoColor=white
[Kubernetes-url]: https://kubernetes.io/
[Argo.com]: https://img.shields.io/badge/Argo-EF7B4D.svg?style=for-the-badge&logo=Argo&logoColor=white
[Argo-url]: https://argo-cd.readthedocs.io/en/stable/
[Hibernate.com]: https://img.shields.io/badge/Hibernate-59666C.svg?style=for-the-badge&logo=Hibernate&logoColor=white
[Hibernate-url]: https://hibernate.org/
[maven.com]: https://img.shields.io/badge/Apache%20Maven-C71A36?logo=apachemaven&logoColor=fff&style=for-the-badge
[maven-url]: https://maven.apache.org/
[trivy.com]: https://img.shields.io/badge/Trivy-1904DA?logo=trivy&logoColor=fff&style=for-the-badge
[trivy-url]: https://trivy.dev/
[intellij.com]: https://img.shields.io/badge/IntelliJ%20IDEA-000?logo=intellijidea&logoColor=fff&style=for-the-badge
[intellij-url]: https://www.jetbrains.com/idea/?var=1
[gmail.com]: https://img.shields.io/badge/Gmail-EA4335?logo=gmail&logoColor=fff&style=flat
[github.com]: https://img.shields.io/badge/GitHub-181717?logo=github&logoColor=fff&style=flat