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
