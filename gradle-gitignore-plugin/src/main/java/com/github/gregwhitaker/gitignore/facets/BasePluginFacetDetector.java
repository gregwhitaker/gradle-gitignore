package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BasePluginFacetDetector implements FacetDetector {
    private final Project project;

    public BasePluginFacetDetector(final Project project) {
        this.project = project;
    }

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
