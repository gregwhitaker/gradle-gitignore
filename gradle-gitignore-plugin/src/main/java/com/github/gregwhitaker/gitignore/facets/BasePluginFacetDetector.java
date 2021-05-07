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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base class for all facet detectors that require checking applied gradle plugins.
 */
public abstract class BasePluginFacetDetector implements FacetDetector {
    private final Project project;

    public BasePluginFacetDetector(final Project project) {
        this.project = project;
    }

    /**
     * Returns a mapping of pluginId to the facets that should be applied if the plugin is detected.
     *
     * @return map of pluginId to facets
     */
    abstract Map<String, List<String>> pluginToFacetsMappings();

    @Override
    public void facets(final Set<String> facets) {
        pluginToFacetsMappings().forEach((pluginName, facetList) -> {
            if (project.getPlugins().hasPlugin(pluginName)) {
                facets.addAll(facetList);
            }
        });
    }
}
