rootProject.name = "Fiadelity"
include("Fiadelity-API", "Fiadelity-Server")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://mvn.thearcanebrony.net/repository/maven-public/")
        maven("https://jitpack.io/")
        mavenCentral()
    }
}
