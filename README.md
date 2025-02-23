# What this project's about?

This is just a simple Spring Cloud project there I try out Spring Cloud and apply my theory knowledges on practice

# Components:

This project consists of four components:
1) **MathMicro** - microservice responsible for generating math examination questions
2) **HistoryMicro** - microservice responsible for generating history examination questions
3) **ExaminatorMicro** - microservice responsible for making calls to MathMicro and HistoryMicro ad generating Exam Questions
4) **ExamensSubjectsInformerMicro** - microservice that's used as **Spring Cloud Stream Kafka** consumer
5) **ExamensSubjectsRetreiverMicro** - microservice that's used as **Spring Cloud Stream Kafka** producer
6) **EuricaDiscoveryMicro** - Spring Cloud Discovery Service. Responsible for microcervices detection and registration
7) **ApiGatewayMicro** - Spring Cloud API Service. Acts as an entry point for the application
8) **ConfigServerMicro** - Spring Cloud Configuration Service. Acts as a centralized configuration storage. Retrieves configuration from [here](https://github.com/BotNicholas/Spring-Cloud-Test-Config)...
9) **AuthenticationService** - Spring Cloud JWT Security Provider. Exposes endpoints to register a user, login and refresh access and both tockens

# Config
All the config you can find [here](https://github.com/BotNicholas/Spring-Cloud-Test-Config)...

# Common Docker Images

[Here](https://github.com/BotNicholas/Spring-Cloud-Test/tree/master/common-docker-images) you can find some common docker images as, for example [kafka](https://github.com/BotNicholas/Spring-Cloud-Test/tree/master/common-docker-images/kafka) or [vault](https://github.com/BotNicholas/Spring-Cloud-Test/tree/master/common-docker-images/vault) images...

> _**Atention!**_ Please take in the consideration the fact that some Dockerfile images can be found in other components. More often these are DB images.\
> For example [HistoryMicro](https://github.com/BotNicholas/Spring-Cloud-Test/tree/master/HistoryMicro) and [AuthenticationService](https://github.com/BotNicholas/Spring-Cloud-Test/tree/master/AuthenticationService) use Databases, thus you can find Dockerfile file in their resources folders...
