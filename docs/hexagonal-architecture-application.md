# Hexagonal architecture 

## Definition

The hexagonal architecture is an architecture pattern which allow to separate the 'business code' the Domain  from the 'technical code'.
By 'technical code' I mean the code created to get data, or the code created to call the business co

In this pattern the Domain doesn't know the code in the technical part, and the communication is allowed by interfaces.

 ## How take in place from a layer project

We start with project with packages controller , service, repository and each package use the same object.
To implement the hexagonal architecture, we **CAN**: 
* create the arborescence :
  * api
    * controller
  * application
  * domain
    * model
    * port
    * service
  * persistence
    * adapter
    * entity
    * mapper
* move all class in each package
* create specifics model in te domain
* create interface (port) in domain to communicate with other part
* in persistance package implement port
* in application package create the configuration and declare beans of the domain using the implementation of port as argument