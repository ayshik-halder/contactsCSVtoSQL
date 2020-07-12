# Contacts CSV to SQL 
##  ~ Project by _Ayshik Halder_ and _Sandip Chakraborty_

>Backend Spring framework using Gradle dependencies to implement a RestAPI to import contacts in CSV format and store in Database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
- OpenJDK-8
- IntelliJ IDEA (for development)
```

### Building

A step by step series of examples that tell you how to get a development env running

> To build project, generate  .jar executable file

```
$ ./gradlew build
$ ./gradlew bootrun
```
> To execute the .jar file generated by gradle.
```
$ cd build/libs/
$ java -jar <Name of Jar file generated>
```
### API call

>To execute HTTP requests click on [LocaHost](http://localhost:8080/swagger-ui.html) .

## Deployment

The backend can be deployed without an IDE , using the above steps on a terminal alone.

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management
* [H2 DB](https://www.h2database.com/html/main.html) - Embedded in-memory databases
* [Swagger UI](https://swagger.io/tools/swagger-ui/) - RestAPI documentation tool
## Authors

* **Ayshik Halder** - *Initial work* - [Github](https://github.com/PurpleBooth)
* **Sandip Chakraborty** - *Architect* - [Linkedin](https://www.linkedin.com/in/sandip-chakraborty-28b48bb6/)


## License

This project is licensed under the GPL-3.0 License - see the [LICENSE](LICENSE) file for details.

## Support

Email us at <halderayshik@gmail.com> for support or feedback.
A star on the repository is appreciated.
