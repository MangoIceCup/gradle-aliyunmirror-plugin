plugins {
    `java`
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
}

group = "com.gmail.jfkudg"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
}
gradlePlugin {
    plugins {
        create("AppreciateAliyunMirrorPlugin") {
            id = "com.gmail.jfkudg.appreciate-aliyun-mirror"
            implementationClass = "com.gmail.jfkudg.aliyunmirror.AppreciateAliyunMirrorPlugin"
            displayName = "Appreciate Aliyun Mirror Plugin"
            description = "Using Aliyun Mirrored Repositories"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("myLibrary") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "myRepo"
            url = uri("file://${buildDir}/repo")
        }
    }
}




pluginBundle {
    website = "https://github.com/MangoIceCup/gradle-aliyunmirror-plugin"
    vcsUrl = "https://github.com/MangoIceCup/gradle-aliyunmirror-plugin.git"
    tags = listOf("aliyun", "repo", "mirror")
}

tasks.withType<JavaCompile>() {
    targetCompatibility = "1.8"
    sourceCompatibility = "1.8"
}