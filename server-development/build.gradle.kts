group = "ampelmaennchen"
version = "1.0-SNAPSHOT"

plugins {
    application
    kotlin("jvm")
}

dependencies {
    compile(project(":server-core"))
}

application {
    mainClassName = "ampelmaennchen.ApplicationKt"
}

tasks {
    "jar"(Jar::class) {
        manifest {
            attributes["Main-Class"] = "ampelmaennchen.ApplicationKt"
        }

        from(configurations.compile.map { if (it.isDirectory) it else zipTree(it) })
    }
}