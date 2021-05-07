/**
 * Copyright 2017-Present Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
