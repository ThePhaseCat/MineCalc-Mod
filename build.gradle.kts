plugins {
	java
	kotlin("jvm")
	id("fabric-loom")
}

group = property("maven_group")!!
version = property("mod_version")!!

repositories {
	// Repositories for dependencies
	mavenCentral()
	maven("https://server.bbkr.space/artifactory/libs-release")
	maven("https://api.modrinth.com/maven")
	maven("https://maven.wispforest.io")
	maven(uri("https://maven.gegy.dev"))
}

dependencies {
	// To change versions, see gradle.properties
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")

	modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}+${property("minecraft_version")}")

	//modImplementation("io.github.cottonmc:LibGui:9.2.2+1.20.2")
	//include("io.github.cottonmc:LibGui:9.2.2+1.20.2")

	modImplementation("dev.lambdaurora:spruceui:${property("spruceui_version")}")
	include("dev.lambdaurora:spruceui:${property("spruceui_version")}")
}

tasks {
	processResources {
		inputs.property("version", project.version)
		filesMatching("fabric.mod.json") {
			expand(mutableMapOf("version" to project.version))
		}
	}

	jar {
		from("LICENSE")
	}
}

java {
	withSourcesJar()
}

kotlin {
	jvmToolchain(21) // java version
}
