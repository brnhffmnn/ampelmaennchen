group = "ampelmaennchen"
version = "1.0-SNAPSHOT"

plugins {
    application
    kotlin("jvm")
}

repositories {
    maven { url = uri("https://oss.sonatype.org/content/groups/public") }
}

dependencies {
    compile(project(":server-core"))
    compile("com.pi4j:pi4j-core:1.2-SNAPSHOT")
}

application {
    mainClassName = "ampelmaennchen.ApplicationKt"
}

tasks {
    "jar"(Jar::class) {
        manifest {
            attributes["Main-Class"] = "ampelmaennchen.ApplicationKt"
        }

        from(configurations.compile.map { if (it.isDirectory) it as File else zipTree(it) })
    }
}