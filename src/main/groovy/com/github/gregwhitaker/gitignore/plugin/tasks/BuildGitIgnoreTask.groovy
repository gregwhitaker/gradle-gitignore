/*
 * Copyright 2017 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.gregwhitaker.gitignore.plugin.tasks


import com.github.gregwhitaker.gitignore.plugin.internal.FacetDetectors
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.ParallelizableTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Paths

@ParallelizableTask
@CacheableTask
class BuildGitIgnoreTask extends DefaultTask {

    @TaskAction
    void run() {
        def autoDetect = project.gitignore.autoDetect as boolean
        def url = project.gitignore.url as String
        def facets = project.gitignore.facets as List<String>

        if (url?.trim()) {
            failIfInvalidUrl(url)
            buildGitIgnoreFromUrl(url)
        } else {
            if (autoDetect) {
                FacetDetectors.detect(facets)
            }

            failIfNoFacets(facets)
            buildGitIgnoreFile(facets)
        }
    }

    private void buildGitIgnoreFile(List<String> facets) {
        if (!facets) {
            throw new GradleException("No facets configured in the 'facets' parameter")
        }
    }

    private void buildGitIgnoreFromUrl(String url) {
        def http = new HTTPBuilder(url)
        http.request(Method.GET, ContentType.ANY) {
            response.success = { resp, content ->
                try {
                    File f = Paths.get(project.projectDir.absolutePath, '.gitignore').toFile()
                    f.createNewFile()

                    def fout = f.newOutputStream()
                    fout << content
                    fout.close()
                } catch (IOException e) {
                    throw new GradleException('Unable to write gitignore file in project directory', e)
                }
            }

            response.'401' = {
                throw new GradleException("Access denied attempting to access url configured in the 'url' parameter")
            }

            response.'404' = { resp ->
                throw new GradleException("No gitignore file template found at location configured in the 'url' parameter")
            }

            response.failure = { resp ->
                throw new GradleException("Unhandled error occurred when creating gitignore file from template in the 'url' parameter")
            }
        }
    }

    /**
     * Validates that the configured custom .gitignore url is valid.
     *
     * @param url url to validate
     */
    private void failIfInvalidUrl(String url) {
        try {
            logger.debug("Validating URL: '${url}'")
            new URL(url).toURI()
        } catch (URISyntaxException e) {
            throw new GradleException("The 'url' configuration parameter specified is not a valid URL.", e)
        }
    }

    private void failIfNoFacets(List<String> facets) {
        if (!facets) {
            throw new GradleException("The 'facets' configuration parameter is required when 'autoDetect' is disabled")
        }
    }

}
