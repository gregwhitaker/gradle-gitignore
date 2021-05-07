package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageFacetDetector extends BasePluginFacetDetector {

    public LanguageFacetDetector(final Project project) {
        super(project);
    }

    @Override
    Map<String, List<String>> pluginToFacetsMappings() {
        final Map<String, List<String>> mappings = new HashMap<>();
        mappings.put("java", Arrays.asList("java"));
        mappings.put("java-library", Arrays.asList("java"));
        mappings.put("java-platform", Arrays.asList("java"));
        mappings.put("groovy", Arrays.asList("java"));
        mappings.put("grails", Arrays.asList("grails"));

        return mappings;
    }
}
