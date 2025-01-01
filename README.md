# Spring boot 프로파일별 로깅

spring boot에서 프로파일별 로깅하는 예제입니다.  

## application 설정

```yml
spring:
  application:
    name: bootlogging
  profiles:
    active: local
---
spring.config.activate.on-profile: local

---
spring.config.activate.on-profile: dev 

logging:
    file:
        name: /users/hyoungdonju/logs/${spring.application.name}.log
    logback.rollingpolicy:
        max-file-size: 1MB
        max-history: 5
```

## logback-spring.xml 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<include resource="org/springframework/boot/logging/logback/defaults.xml" />

	<springProfile name="local">
		<include resource="org/springframework/boot/logging/logback/console-appender.xml" />
		<root level="DEBUG">
			<appender-ref ref="CONSOLE" />
		</root>
	</springProfile>

	<springProfile name="dev">
		<include resource="org/springframework/boot/logging/logback/file-appender.xml" />
		<root level="trace">
			<appender-ref ref="FILE" />
		</root>
	</springProfile>
</configuration>
```

## 테스트

```java
// BootloggingApplication.java 참고

@Value("${spring.profiles.active}")
	private String profiles;

	@Bean
	CommandLineRunner startBoot() {
		return args -> {
			log.info("hi, there! this is logging. Profiles are " + profiles);
			log.error("Oops, something went wrong");

		};
	}
```