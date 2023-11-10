plugins {
    id("java")
    id ("org.liquibase.gradle") version "2.2.0"
    id ("war")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.hibernate:hibernate-core:5.6.15.Final")
    implementation("org.postgresql:postgresql:42.6.0")
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    implementation("ma.glasnost.orika:orika-core:1.5.4")
    liquibaseRuntime("org.liquibase:liquibase-core:3.8.1")
    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:2.1.1")
    liquibaseRuntime("ch.qos.logback:logback-classic:1.2.6")
    liquibaseRuntime("jakarta.xml.bind:jakarta.xml.bind-api:2.3.2")
    liquibaseRuntime("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework:spring-webmvc:5.3.15")
    implementation("org.springframework:spring-jdbc:5.3.15")
    implementation("org.springframework.data:spring-data-jpa:2.7.8")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("jstl:jstl:1.2")


}
tasks.test {
    useJUnitPlatform()
}
