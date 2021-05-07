package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdeFacetDetector extends BasePluginFacetDetector {

    public IdeFacetDetector(final Project project) {
        super(project);
    }

    @Override
    Map<String, List<String>> pluginToFacetsMappings() {
        final Map<String, List<String>> mappings = new HashMap<>();
        mappings.put("idea", Arrays.asList("intellij+all"));
        mappings.put("eclipse", Arrays.asList("eclipse"));

        return mappings;
    }
}
