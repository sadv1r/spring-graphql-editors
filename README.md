# Spring GraphQL Editors

[![build](https://github.com/sadv1r/spring-graphql-editors/actions/workflows/build.yml/badge.svg)](https://github.com/sadv1r/spring-graphql-editors/actions/workflows/build.yml)

These starters intend to be used
with [spring-boot-starter-graphql](https://docs.spring.io/spring-graphql/docs/current/reference/html/), but it will work
without it, you just need to provide GraphQL path as `spring.graphql.path` parameter.

## GraphQL Voyager

[![Maven Central](https://img.shields.io/maven-central/v/ru.sadv1r.spring.graphql/graphql-voyager-spring-boot-starter)](https://search.maven.org/artifact/ru.sadv1r.spring.graphql/graphql-voyager-spring-boot-starter)
[![GitHub Repo stars](https://img.shields.io/github/stars/IvanGoncharov/graphql-voyager?style=social)](https://github.com/IvanGoncharov/graphql-voyager)

**GraphQL Voyager** becomes accessible at root `/voyager` (or as configured in `spring.graphql.voyager.path`).

Available Spring Boot configuration parameters (either `application.yml`
or `application.properties`):

```yaml
spring:
  graphql:
    voyager:
      enabled: true
      path: /voyager
      displayOptions:
        skipRelay: true
        skipDeprecated: true
        rootType: Query
        sortByAlphabet: false
        showLeafFields: true
        hideRoot: false
      hideDocs: false
      hideSettings: false
      cdn: unpkg
      stylePath: /style.css
```

### Dependency

#### Maven

```xml

<dependency>
    <groupId>ru.sadv1r.spring.graphql</groupId>
    <artifactId>graphql-voyager-spring-boot-starter</artifactId>
    <version>0.6.0</version>
</dependency>
```

#### Gradle

```groovy
implementation 'ru.sadv1r.spring.graphql:graphql-voyager-spring-boot-starter:0.6.0'
```

## Graph*i*QL

[![Maven Central](https://img.shields.io/maven-central/v/ru.sadv1r.spring.graphql/graphql-graphiql-spring-boot-starter)](https://search.maven.org/artifact/ru.sadv1r.spring.graphql/graphql-graphiql-spring-boot-starter)
[![GitHub Repo stars](https://img.shields.io/github/stars/graphql/graphiql?style=social)](https://github.com/graphql/graphiql)

**Graph*i*QL** becomes accessible at root `/graphiql` (or as configured in `spring.graphql.graphiql.path`).

Available Spring Boot configuration parameters (either `application.yml`
or `application.properties`):

```yaml
spring:
  graphql:
    graphiql:
      enabled: true
      path: /graphiql
      query: |-
        query($id: ID!) {
          artifactRepository(id: $id) {
             name
             url
          }
        }
      default-editor-tools-visibility: VARIABLES
      variables:
        id: 1
      headers:
        x-test: test
      plugins: EXPLORER
      cdn: unpkg
      stylePath: /style.css
```

Since setting (large) query in the properties like this isn't very readable, you can provide a file path instead:

```yaml
spring:
  graphql:
    graphiql:
      enabled: true
      query: example/query.graphql
```

### Dependency

#### Maven

```xml

<dependency>
    <groupId>ru.sadv1r.spring.graphql</groupId>
    <artifactId>graphql-graphiql-spring-boot-starter</artifactId>
    <version>0.6.0</version>
</dependency>
```

#### Gradle

```groovy
implementation 'ru.sadv1r.spring.graphql:graphql-graphiql-spring-boot-starter:0.6.0'
```