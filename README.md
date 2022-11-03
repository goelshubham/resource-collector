## Description
I have created a resource-collector service which collects system resources - CPU utilization and system memory utilization every 30 seconds and persists the data in an embeded database H2. This services exposes a REST endpoint `/api/resources/{hours}` that returns a response with the current state of system resources and also a historical view of system resources for the last N hours specified in API parameter.

Behind the scene, the service has been written using Java and Spring Boot framework. I am using Spring scheduler framework to trigger a job every 30 seconds to collect system data.

### Installing
I have created a docker image. Follow these commands to run locally. Docker should be installed on the local machine as a prerequisite. 

Pull Docker Image:
`docker pull goyalshub/resource-collector:latest`

Run Docker Image:
`docker run goyalshub/resource-collector:latest`

If above commands are successful, you should see following logs in console:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.5)

2022-11-03 03:13:44.018  INFO 1 --- [           main] c.r.ResourceCollectorApplication         : Starting ResourceCollectorApplication v0.0.1-SNAPSHOT using Java 11.0.16 on 49688b4e43a4 with PID 1 (/usr/local/lib/resource-collector.jar started by root in /)
2022-11-03 03:13:44.020  INFO 1 --- [           main] c.r.ResourceCollectorApplication         : No active profile set, falling back to 1 default profile: "default"
2022-11-03 03:13:44.416  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2022-11-03 03:13:44.444  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 22 ms. Found 1 JPA repository interfaces.
2022-11-03 03:13:44.944  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-11-03 03:13:44.954  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-11-03 03:13:44.954  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.68]
2022-11-03 03:13:45.009  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-11-03 03:13:45.009  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 930 ms
2022-11-03 03:13:45.036  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-11-03 03:13:45.224  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-11-03 03:13:45.233  INFO 1 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console/'. Database available at 'jdbc:h2:mem:testdb'
2022-11-03 03:13:45.383  INFO 1 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-11-03 03:13:45.422  INFO 1 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.12.Final
2022-11-03 03:13:45.548  INFO 1 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2022-11-03 03:13:45.622  INFO 1 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2022-11-03 03:13:45.931  INFO 1 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-11-03 03:13:45.936  INFO 1 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-11-03 03:13:46.241  WARN 1 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-11-03 03:13:46.470  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-11-03 03:13:46.478  INFO 1 --- [           main] c.r.ResourceCollectorApplication         : Started ResourceCollectorApplication in 2.859 seconds (JVM running for 3.207)
```

### Executing program
Make HTTP GET API call to following endpoint:

`http://localhost:8080/api/resources/{numberOfHours}`

You should a response like below:
```
{
    "currentState": {
        "cpuUtilization": 0.07848266841072596,
        "memoryUtilization": 64,
        "timestamp": null
    },
    "historicalState": [
        {
            "cpuUtilization": 0.053358233842459815,
            "memoryUtilization": 64,
            "timestamp": "2022-11-03T02:37:39.411822Z"
        },
    ]
}
```
