plugins {
    `java`
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
}

group = "com.gitee.yuhun"
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
            id = "com.gitee.yuhun.appreciate-aliyun-mirror"
            implementationClass = "com.gitee.yuhun.switchmirror.AppreciateAliyunMirrorPlugin"
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
    vcsUrl = "svn://gitee.com/YuHun/appreciate-aliyun-mirror"
    tags = listOf("china", "repo", "mirror")
}

tasks.withType<JavaCompile>() {
    targetCompatibility = "1.8"
    sourceCompatibility = "1.8"
}