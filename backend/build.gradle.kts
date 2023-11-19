plugins {
    id("java")
    id ("org.liquibase.gradle") version "2.0.4"
    id("org.springframework.boot") version "2.3.4.RELEASE"
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
    implementation("org.springframework.boot:spring-boot-starter-web:2.3.4.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.3.4.RELEASE")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("jstl:jstl:1.2")
    implementation("org.springframework.boot:spring-boot-starter-aop:2.3.4.RELEASE")
    implementation("co.elastic.logging:logback-ecs-encoder:1.5.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.3.4.RELEASE")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor:2.3.4.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter:2.3.4.RELEASE")


}
tasks.test {
    useJUnitPlatform()
}
extra.apply {
    set("db_url", "jdbc:postgresql://localhost:5432/postgres")
    set("db_user", "postgres")
    set("db_pass", "Postgres")
}


liquibase {
    activities.register("main") {
        val db_url by project.extra.properties
        val db_user by project.extra.properties
        val db_pass by project.extra.properties
        this.arguments = mapOf(
                "logLevel" to "info",
                "changeLogFile" to "src/main/resources/migrations/db.changelog-master.xml",
                "url" to db_url,
                "username" to db_user,
                "password" to db_pass,
                "driver" to "org.postgresql.Driver"
        )
    }
    runList = "main"
}