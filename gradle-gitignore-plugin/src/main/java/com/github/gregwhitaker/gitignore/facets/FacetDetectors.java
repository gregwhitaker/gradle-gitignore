package com.github.gregwhitaker.gitignore.facets;

import org.gradle.api.Project;

import java.util.Set;

public class FacetDetectors {

    public static void detect(Project project, Set<String> facets) {
        new OperatingSystemFacetDetector().facets(facets);
        new LanguageFacetDetector(project).facets(facets);
        new IdeFacetDetector(project).facets(facets);
        new LocalStackFacetDetector(project).facets(facets);
    }
}
