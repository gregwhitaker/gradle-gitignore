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

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.ParallelizableTask
import org.gradle.api.tasks.TaskAction

@ParallelizableTask
@CacheableTask
class BuildGitIgnoreTask extends DefaultTask {

    @TaskAction
    void run() {
        def autoDetect = project.gitignore.autoDetect
        def url = project.gitignore.url
        def facets = project.gitignore.facets

        if (url?.trim()) {
            failIfInvalidUrl((String) url)

            logger.info("Loading .gitignore from: ${url}")

        } else {
            if (autoDetect) {
                logger.info('Auto-detecting facets to apply to generated .gitignore file')
            }

            if (facets) {
                facets.each {
                    logger.warn(it)
                }
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

}
