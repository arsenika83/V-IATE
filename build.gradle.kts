
plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.VIATE"
version = "0.0.1-SNAPSHOT"
description = "Student oriented web-portal"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

springBoot {
	mainClass = "src/main/java/com/AVASPP/VIATE/VIATEApplication.java"
}

repositories {
	mavenCentral()
}

dependencies {
	//implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

	runtimeOnly("org.postgresql:postgresql")

	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sourceSets {
	main {
		java {
			setSrcDirs(listOf(
				"src/main/java/com/AVASPP/VIATE",
				"src/main/java/com/AVASPP/VIATE/entity",
				"src/main/java/com/AVASPP/VIATE/repository",
				"src/main/java/com/AVASPP/VIATE/service",
				"src/main/java/com/AVASPP/VIATE/controller"
			))
		}
	}
	test {
		java {
			setSrcDirs(listOf("src/test"))
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
