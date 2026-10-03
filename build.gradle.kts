plugins {
    java
    id("org.springframework.boot") version "4.1.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.github.chocobe"
version = "0.0.1-SNAPSHOT"
description = "ex1-hello-jpa"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-h2console")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Hibernate의 어노테이션 색인 라이브러리
    // => 이게 있어야 persistence.xml에 Entity를 직접 명시하지 않아도 자동으로 인식한다.
    runtimeOnly("org.hibernate.orm:hibernate-scan-jandex:7.4.5.Final")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
