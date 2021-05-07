package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adds facets if the localstack gradle plugin is detected.
 */
public class LocalStackFacetDetector extends BasePluginFacetDetector {

    public LocalStackFacetDetector(Project project) {
        super(project);
    }

    @Override
    Map<String, List<String>> pluginToFacetsMappings() {
        final Map<String, List<String>> mappings = new HashMap<>();
        mappings.put("com.nike.pdm.localstack", Arrays.asList("localstack"));

        return mappings;
    }
}
