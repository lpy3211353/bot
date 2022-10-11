import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    val kotlinVersion = "1.6.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.12.3"
}

group = "com.lpy"
version = "demo"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}
dependencies{
    implementation("org.json:json:20220320")
    implementation("com.alibaba:fastjson:2.0.14.graal")
    implementation("redis.clients:jedis:4.3.0-m2")
    implementation("org.slf4j:slf4j-api:2.0.3")
//    implementation("org.projectlombok:lombok:1.18.24")
    implementation("com.baomidou:mybatis-plus:3.5.2")
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.springframework:spring-web:5.3.23")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testCompileOnly ("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
}

