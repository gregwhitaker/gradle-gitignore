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

package com.github.gregwhitaker.gitignore.plugin.internal

import org.gradle.api.Project

/**
 * Base class for all facet detectors that are based on plugins applied to the project.
 */
abstract class BasePluginFacetDetector extends BaseFacetDetector {

    Project project

    BasePluginFacetDetector(final Project project) {
        this.project = project
    }

    /**
     * @return mapping of plugin to facets
     */
    abstract Map<String, List<String>> pluginToFacetsMappings()

    /**
     * Adds facets to the list based on the plugins applied to the project.
     *
     * @param facets list to which to add facets
     */
    void facets(List<String> facets) {
        def mappings = pluginToFacetsMappings()

        mappings.each {
            if (project.plugins.hasPlugin(it.key)) {
                addFacetsIfNotExists(it.value, facets)
            }
        }
    }

}
