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
package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;

import java.util.Set;

/**
 * Detects facets to add based on the project's structure and applied plugins.
 */
public class FacetDetectors {

    /**
     * Detects facets to add.
     *
     * @param project gradle project
     * @param facets set of facets to populate
     */
    public static void detect(final Project project, final Set<String> facets) {
        new OperatingSystemFacetDetector().facets(facets);
        new LanguageFacetDetector(project).facets(facets);
        new IdeFacetDetector(project).facets(facets);
        new LocalStackFacetDetector(project).facets(facets);
    }
}
