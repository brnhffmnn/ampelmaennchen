import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    kotlin("jvm")
}

dependencies {
    compile(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))

    Dependencies.version.ktor.let {
        compile("io.ktor:ktor-server-jetty:$it")
        compile("io.ktor:ktor-jackson:$it")

        testCompile("io.ktor:ktor-server-test-host:$it")
    }

    compile("ch.qos.logback:logback-classic:1.2.1")

    testCompile("io.kotlintest:kotlintest:2.0.4")

}

group = "ampelmaennchen"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}