import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ampelmaennchen"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        jcenter()
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.version.kotlin}")
    }
}

allprojects {
    repositories {
        jcenter()
        maven { url = uri("http://kotlin.bintray.com/ktor") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}