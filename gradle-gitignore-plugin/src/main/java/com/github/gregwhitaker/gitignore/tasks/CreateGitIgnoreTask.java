package com.github.gregwhitaker.gitignore.tasks;

import com.github.gregwhitaker.gitignore.GitIgnoreExtension;
import com.github.gregwhitaker.gitignore.GitIgnorePlugin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Creates a .gitignore file in the project directory.
 */
public class CreateGitIgnoreTask extends DefaultTask {
    private static final String GITIGNORE_BASE_URL = "https://www.toptal.com/developers/gitignore/api/";
    private static final List<String> DEFAULT_FACETS = Arrays.asList("gradle");

    public CreateGitIgnoreTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Creates a .gitignore file for the project.");
    }

    @TaskAction
    public void run() {
        final GitIgnoreExtension ext = getProject().getExtensions().findByType(GitIgnoreExtension.class);

        // Get the contents of the .gitignore file
        String contents = null;
        if (ext.getUrl() != null) {
            contents = getUrl(ext.getUrl());
        } else {
            final Set<String> facets = new HashSet<>(DEFAULT_FACETS);

            // Add auto-detected facets
            if (ext.isAutoDetect()) {
                facets.addAll(autodetectFacets());
            }

            // Add manually entered facets
            if (ext.getFacets() != null) {
                facets.addAll(ext.getFacets());
            }

            // Send the facets to gitignore.io to create the contents of the file
            contents = getUrl(gitignoreUrl(facets));
        }

        // Write the .gitignore file
        if (contents.length() > 0) {
            final File file = Paths.get(getProject().getProjectDir().getAbsolutePath(), ".gitignore").toFile();
            try {
                FileUtils.writeStringToFile(file, contents, Charset.defaultCharset());
            } catch (IOException e) {
                throw new GradleException(String.format("Error occurred while writing '.gitignore' file. [file: '%s']", file.getAbsolutePath()));
            }
        }
    }

    /**
     * Auto-detects project facets to add based on the plugins applied to the project.
     *
     * @return a set of facet names
     */
    private Set<String> autodetectFacets() {
        Set<String> detectedFacets = new HashSet<>();

        return detectedFacets;
    }

    /**
     * Builds the gitignore.io url with facets.
     *
     * @param facets project facets to add to url
     * @return a url for the gitignore.io api
     */
    private String gitignoreUrl(final Set<String> facets) {
        String url = GITIGNORE_BASE_URL;
        if (facets != null) {
            url += String.join(",", facets);
        }

        return url;
    }

    /**
     * Gets the contents of a url as a string.
     */
    private String getUrl(final String url) {
        if (!UrlValidator.getInstance().isValid(url)) {
            throw new GradleException(String.format("Invalid url. [url: '%s']", url));
        }

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
            .get()
            .url(url)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                if (response.code() == 404) {
                    throw new GradleException(String.format("Resource not found. [url: '%s']"));
                }

                throw new GradleException(String.format("Error occurred while retrieving data from url. [code: '%s', url: '%s']", response.code(), url));
            }
        } catch (IOException e) {
            throw new GradleException(String.format("Error occurred while retrieving data from url. [url: '%s']", url));
        }
    }
}
