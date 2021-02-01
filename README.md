### A plugin to replace repo URI to outstanding aliyun's

<br/>

#### How To Use:
* Edit `init.gradle` at `user_home/.gradle/` .
* Copy and paste below code to init.gradle .
<pre>
    initscript {
        repositories {
            maven {
                url "https://plugins.gradle.org/m2/"
            }
        }
        dependencies {
            classpath "com.gmail.jfkudg:aliyunmirror:1.0"
        }
    }
    import com.gmail.jfkudg.aliyunmirror.AppreciateAliyunMirrorPlugin
    apply plugin: AppreciateAliyunMirrorPlugin
</pre>



#### Details of URI replacement:

|From URI|To URI|
|  ----  | ----  |
|    https://repo1.maven.org/maven2/        |    https://maven.aliyun.com/repository/central    |
|    https://repo.maven.apache.org/maven2/        |    https://maven.aliyun.com/repository/central    |
|    http://jcenter.bintray.com/        |    https://maven.aliyun.com/repository/public    |
|    https://maven.google.com/        |    https://maven.aliyun.com/repository/google    |
|    https://plugins.gradle.org/m2/        |    https://maven.aliyun.com/repository/gradle-plugin    |
|    http://repo.spring.io/libs-milestone/        |    https://maven.aliyun.com/repository/spring    |
|    http://repo.spring.io/plugins-release/        |    https://maven.aliyun.com/repository/spring-plugin    |
|    https://repo.grails.org/grails/core        |    https://maven.aliyun.com/repository/grails-core    |
|    https://repository.apache.org/snapshots/        |    https://maven.aliyun.com/repository/apache-snapshots    |
