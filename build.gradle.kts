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
    implementation("org.hibernate.orm:hibernate-core:6.2.7.Final")
    implementation("org.postgresql:postgresql:42.6.0")
    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    implementation("ma.glasnost.orika:orika-core:1.5.4")
    liquibaseRuntime("org.liquibase:liquibase-core:3.8.1")
    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:2.1.1")
    liquibaseRuntime("ch.qos.logback:logback-classic:1.2.6")
    liquibaseRuntime("jakarta.xml.bind:jakarta.xml.bind-api:2.3.2")
    liquibaseRuntime("org.postgresql:postgresql:42.6.0")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    // https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:5.3.30")


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
    activities.register("main"){
        val db_url by project.extra.properties
        val db_user by project.extra.properties
        val db_pass by project.extra.properties
        this.arguments= mapOf(
                "logLevel" to "info",
                "changeLogFile" to "src/main/resources/migrations/db.changelog-master.xml",
                "url" to db_url,
                "username" to db_user,
                "password" to db_pass,
                "driver" to "org.postgresql.Driver"
        )
        runList = "main"
    }
}
