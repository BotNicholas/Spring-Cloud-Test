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

# Config
All the config you can find [here](https://github.com/BotNicholas/Spring-Cloud-Test-Config)...
