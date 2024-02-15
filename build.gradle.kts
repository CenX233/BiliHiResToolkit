plugins {
    id("java")
}

group = "top.cenx"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-exec:1.4.0")

    implementation("org.jetbrains:annotations:24.1.0")

    implementation("com.formdev:flatlaf:3.3")
    implementation("com.formdev:flatlaf-extras:3.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}