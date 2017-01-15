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

/**
 * Base class for all facet detectors.
 */
abstract class BaseFacetDetector {

    /**
     * Adds detected facets to the supplied facets collection
     *
     * @param facets collection to which to add detected facets
     */
    abstract void facets(List<String> facets)

    /**
     * Adds a facet to the facet list if the list does not already contain the facet.
     *
     * @param newFacet facet to add
     * @param facets list to which to add the facet
     */
    void addFacetIfNotExists(String newFacet, List<String> facets) {
        if (!facets.contains(newFacet)) {
            facets << newFacet
        }
    }

    /**
     * Adds multiple facets to the list of facets if the list does not already contain the facets.
     *
     * @param newFacets facets to add
     * @param facets list to which to add the facet
     */
    void addFacetsIfNotExists(List<String> newFacets, List<String> facets) {
        newFacets?.each {
            if (!facets.contains(it)) {
                facets << it
            }
        }
    }

}