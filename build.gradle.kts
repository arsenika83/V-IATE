
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
	mainClass = "app\\src\\main\\java\\com\\AVASPP\\VIATE\\VIATEApplication.java"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

sourceSets {
	main {
		java {
			setSrcDirs(listOf(
				"app/src/main/java/com/AVASPP/VIATE",
				"app/src/main/java/com/AVASPP/VIATE/entity/user",
				"app/src/main/java/com/AVASPP/VIATE/repository",
				"app/src/main/java/com/AVASPP/VIATE/service",
				"app/src/main/java/com/AVASPP/VIATE/controller"
			))
		}
	}
	test {
		java {
			setSrcDirs(listOf("src/testJavaDir"))
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
