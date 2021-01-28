plugins {
    `java`
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
}

group = "party.smileface"
version = "0.9"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
}
gradlePlugin {
    plugins {
        create("AppreciateAliyunMirrorPlugin") {
            id = "party.smileface.appreciate-aliyun-mirror"
            implementationClass = "party.smileface.switchmirror.AppreciateAliyunMirrorPlugin"
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
    website = "https://gitee.com/YuHun/appreciate-aliyun-mirror"
    vcsUrl = "https://gitee.com/YuHun/appreciate-aliyun-mirror.git"
    tags = listOf("china", "repo", "mirror")
}

tasks.withType<JavaCompile>(){
    targetCompatibility = "1.8"
    sourceCompatibility="1.8"
}