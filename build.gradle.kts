plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-websocket:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.5")
    compileOnly("org.projectlombok:lombok:1.18.28") // Замените на последнюю версию, если нужно
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    runtimeOnly("com.h2database:h2:2.3.232")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}