package com.github.gregwhitaker.gitignore.tasks;

import com.github.gregwhitaker.gitignore.GitIgnorePlugin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.validator.routines.UrlValidator;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.util.Arrays;

/**
 * Lists all facets supported by gitignore.io.
 */
public class ListGitIgnoreFacetsTask extends DefaultTask {
    private static final String GITIGNORE_BASE_URL = "https://www.toptal.com/developers/gitignore/api/";

    public ListGitIgnoreFacetsTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Lists all of the supported project facets.");
    }

    @TaskAction
    public void run() {
        final String contents = getUrl(GITIGNORE_BASE_URL + "list");

        if (contents.length() > 0) {
            final String[] facets = contents.split(",");
            Arrays.stream(facets)
                .forEach(System.out::println);
        }
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
