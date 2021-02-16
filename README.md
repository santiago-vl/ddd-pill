# DDD :pill:

## Description

The aim of this repository is to share knowledge about Domain-Driven Design. 
Following the git tags `step-X` it is appreciated as a highly coupled legacy code, difficult to maintain
and that does not comply with SOLID patters, it matures into a code with a high level quality following
the Domain-Driven Design tactical patterns.

## Getting Started

### Dependencies

* Java 11+
* Maven 3.3+
* Docker + Docker-compose

### Executing program

* From `step-6` or `main` branch
```
docker-compose up -d
mvn install
cd dddpill-boot
mvn spring-boot:run
```
* From `step-0` to `step-5`
```
docker-compose up -d
mvn spring-boot:run
```

## License

This project is licensed under the MIT License - see the LICENSE.md file for details
