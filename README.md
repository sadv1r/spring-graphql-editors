# Spring GraphQL Editors

[![build](https://github.com/sadv1r/spring-graphql-editors/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/sadv1r/spring-graphql-editors/actions/workflows/build.yml)

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
    <version>0.4.0</version>
</dependency>
```

#### Gradle

```groovy
implementation 'ru.sadv1r.spring.graphql:graphql-voyager-spring-boot-starter:0.4.0'
```

## Graph*i*QL

[![Maven Central](https://img.shields.io/maven-central/v/ru.sadv1r.spring.graphql/graphql-graphiql-spring-boot-starter)](https://search.maven.org/artifact/ru.sadv1r.spring.graphql/graphql-graphiql-spring-boot-starter)
[![GitHub Repo stars](https://img.shields.io/github/stars/graphql/graphiql?style=social)](https://github.com/graphql/graphiql)

**Graph*i*QL** becomes accessible at root `/graphiql` (or as configured in `spring.graphql.graphiql.path`).

Available Spring Boot configuration parameters (either `application.yml`
or `application.properties`):

```yaml
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
    <version>0.4.0</version>
</dependency>
```

#### Gradle

```groovy
implementation 'ru.sadv1r.spring.graphql:graphql-graphiql-spring-boot-starter:0.4.0'
```

## GraphQL Playground

[![Maven Central](https://img.shields.io/maven-central/v/ru.sadv1r.spring.graphql/graphql-playground-spring-boot-starter)](https://search.maven.org/artifact/ru.sadv1r.spring.graphql/graphql-playground-spring-boot-starter)
[![GitHub Repo stars](https://img.shields.io/github/stars/prisma-labs/graphql-playground?style=social)](https://github.com/graphql/graphql-playground)

**GraphQL Playground** becomes accessible at root `/playground` (or as configured in `spring.graphql.playground.path`).

Available Spring Boot configuration parameters (either `application.yml`
or `application.properties`):

```yaml
graphql:
  playground:
    enabled: true
    path: /playground
    settings:
      editor:
        cursorShape: line
        fontFamily: '"Fira code", "Fira Mono", monospace'
        fontSize: 14
        theme: dark
      general:
        betaUpdates: false
      prettier:
        printWidth: 80
        tabWidth: 2
        useTabs: false
      request:
        credentials: omit
      schema:
        polling:
          enable: true
          endpointFilter: *localhost*
          interval: 2000
        disableComments: false
      tracing:
        hideTracingResponse: false
        tracingSupported: true
    headers:
      x-test: test
    tabs:
      - name: GraphQL
        endpoint: http://localhost:8080/graphql
        query: |-
          query($id: ID!) {
            artifactRepository(id: $id) {
               name
               url
            }
          }
        variables: |-
          {
            "id": 1
          }
        responses:
          - >
            {
              "data": {
                "artifactRepositories": [{
                  "name": "test",
                  "url": "http://localhost:8080"
                }]
              }
            }
        headers:
          x-test2: test2
    cdn: unpkg
```

Since setting (large) query in the properties like this isn't very readable, you can provide a file path instead:

```yaml
graphql:
  playground:
    enabled: true
    tabs:
      - endpoint: http://localhost:8080/graphql
        query: example/query.graphql
```

### Dependency

#### Maven

```xml

<dependency>
    <groupId>ru.sadv1r.spring.graphql</groupId>
    <artifactId>graphql-playground-spring-boot-starter</artifactId>
    <version>0.4.0</version>
</dependency>
```

#### Gradle

```groovy
implementation 'ru.sadv1r.spring.graphql:graphql-playground-spring-boot-starter:0.4.0'
```