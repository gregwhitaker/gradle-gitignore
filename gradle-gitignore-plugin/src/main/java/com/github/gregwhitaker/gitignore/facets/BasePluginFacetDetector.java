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
