plugins {
    scala
    application
}

group = "fr.xebia.spark"
version = "0.0.1-SNAPSHOT"

val scalaVersion = "2.13.18"
val scalaDepVersion = "2.13"
val sparkVersion = "4.2.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala-library:$scalaVersion")
    implementation("org.apache.spark:spark-core_$scalaDepVersion:$sparkVersion")
    implementation("org.apache.spark:spark-sql_$scalaDepVersion:$sparkVersion")
    implementation("org.apache.spark:spark-mllib_$scalaDepVersion:$sparkVersion")
}

application {
    mainClass = "fr.xebia.sparkml.Titanic"
}

tasks.test {
    enabled = false
}

tasks.named<JavaExec>("run") {
    // Spark needs access to JDK internals on Java 17+
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
        "--add-opens=java.base/java.nio=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED",
        "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
    )
}
