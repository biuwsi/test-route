## Additional properties:

#### route.policy
Policy of loading file with countries. Implemented 2 policies:
 - remote - loads file from remote resource
 - resource - loads file from path (set by default)

#### route.resource-policy.file
 Path to the resource file (required property if resource policy set)

#### route.remote-policy.url
 Url to the resource file (required property if remote policy set)

#### route.algorithm
Algorithm for graph search. **bts** set by default


## Running:
> mvn spring-boot:run

- 8080 port by default
- resource policy (from local file) by default
- bts algorithm by default (actually, there is only one algorithm implemented)

There is some test

## Requirements
- Maven
- Java 11+