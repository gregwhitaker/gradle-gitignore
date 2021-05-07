package com.github.gregwhitaker.gitignore.facets;

import java.util.Set;

/**
 * Interface that all facet detectors must implement.
 */
public interface FacetDetector {

    /**
     * Detects facets and adds them to the facets set.
     *
     * @param facets set to which to add detected facets
     */
    void facets(Set<String> facets);
}
