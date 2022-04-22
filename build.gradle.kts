plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("io.papermc.paperweight.patcher") version "1.2.0"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    remapper("net.fabricmc:tiny-remapper:0.8.1:fat")
    decompiler("org.quiltmc.quiltflower:1.8.0")
    paperclip("io.papermc:paperclip:2.0.1")
}

subprojects {
    apply(plugin = "java")

    // java { toolchain { languageVersion.set(JavaLanguageVersion.of(16)) } } // uncommenting this line makes gradle download and use it's own java version

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(16)
        options.compilerArgs = listOf(
                "--add-modules", "jdk.incubator.vector" 
        )
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://ci.emc.gs/nexus/content/groups/aikar/")
        maven("https://repo.aikar.co/content/groups/aikar")
        maven("https://repo.md-5.net/content/repositories/releases/")
        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
        maven("https://jitpack.io")
    }
}

paperweight {
    serverProject.set(project(":Fiadelity-Server"))

    useStandardUpstream("airplane") {
        remapRepo.set("https://maven.fabricmc.net/")
        decompileRepo.set("https://maven.quiltmc.org/")

        url.set(github("fakesusdev", "Airplane"))
        ref.set(providers.gradleProperty("AirplaneCommit"))

        withStandardPatcher {
            baseName("Airplane")

            apiOutputDir.set(layout.projectDirectory.dir("Fiadelity-API"))
            serverOutputDir.set(layout.projectDirectory.dir("Fiadelity-Server"))
        }
    }
}
