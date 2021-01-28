package party.smileface.switchmirror;

import org.gradle.api.Plugin;
import org.gradle.api.artifacts.repositories.ArtifactRepository;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.logging.LogLevel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Properties;

public class AppreciateAliyunMirrorPlugin implements Plugin<Gradle> {
    private final Properties properties;

    public AppreciateAliyunMirrorPlugin() {
        properties = new Properties();
        try {
            properties.loadFromXML(getClass().getResourceAsStream("/url-transform.xml"));
        } catch (IOException ioException) {
            System.err.println("Switch Mirror Plugin Load 'url-transform.xml' failed.");
        }
    }

    /**
     * make sure uri end with /
     *
     * @param uri url
     * @return transformed uri
     */
    public URI tidyURI(URI uri) {
        String uriString = uri.toString();
        if (!uriString.endsWith("/")) {
            uriString = uriString + "/";
        }
        return URI.create(uriString);
    }

    /**
     * transform uri if possible
     *
     * @param from uri to transform
     * @return transformed uri
     */
    public URI transformUri(URI from) {
        final String property = properties.getProperty(tidyURI(from).toString());
        if (property != null) {
            return tidyURI(URI.create(property));
        }
        return from;
    }

    @Override
    public void apply(Gradle gradle) {

        gradle.beforeSettings(settings -> {
            settings.pluginManagement(pluginManagementSpec -> {
                pluginManagementSpec.repositories(artifactRepositories -> {
                    artifactRepositories.all(repository -> {
                        final Class<? extends ArtifactRepository> clazz = repository.getClass();
                        try {
                            final Method getUrl = clazz.getMethod("getUrl");
                            final URI url = (URI) getUrl.invoke(repository);
                            final URI transformedUri = transformUri(url);
                            final Method setUrl = clazz.getMethod("setUrl", URI.class);
                            setUrl.invoke(repository, transformedUri);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                });
            });
        });

        setupReplaceProjectRepositoryUrlLogical(gradle);
    }

    private void setupReplaceProjectRepositoryUrlLogical(Gradle gradle) {
        gradle.allprojects(project -> {
            project.afterEvaluate(prj -> {
                for (ArtifactRepository repository : prj.getRepositories()) {
                    final Class<? extends ArtifactRepository> clazz = repository.getClass();
                    try {
                        final Method getUrl = clazz.getMethod("getUrl");
                        final URI url = (URI) getUrl.invoke(repository);
                        final URI transformedUri = transformUri(url);
                        final Method setUrl = clazz.getMethod("setUrl", URI.class);
                        setUrl.invoke(repository, transformedUri);
                        prj.getLogger().log(LogLevel.INFO, "Transformed URI " + url + " to " + transformedUri);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
