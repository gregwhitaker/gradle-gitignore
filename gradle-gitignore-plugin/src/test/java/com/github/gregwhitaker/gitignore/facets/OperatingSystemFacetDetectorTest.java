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

import org.junit.Rule;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperatingSystemFacetDetectorTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties();

    @Test
    public void shouldSetCorrectFacetsForMac() {
        // Given
        System.setProperty("os.name", "Mac OS");

        final OperatingSystemFacetDetector detector = new OperatingSystemFacetDetector();
        final Set<String> facets = new HashSet<>();

        // When
        detector.facets(facets);

        // Then
        assertEquals(2, facets.size());
        assertTrue(facets.contains("macos"));
        assertTrue(facets.contains("osx"));
    }

    @Test
    public void shouldSetCorrectFacetsForDarwin() {
        // Given
        System.setProperty("os.name", "Darwin");

        final OperatingSystemFacetDetector detector = new OperatingSystemFacetDetector();
        final Set<String> facets = new HashSet<>();

        // When
        detector.facets(facets);

        // Then
        assertEquals(2, facets.size());
        assertTrue(facets.contains("macos"));
        assertTrue(facets.contains("osx"));
    }

    @Test
    public void shouldSetCorrectFacetsForWindows() {
        // Given
        System.setProperty("os.name", "Windows 10");

        final OperatingSystemFacetDetector detector = new OperatingSystemFacetDetector();
        final Set<String> facets = new HashSet<>();

        // When
        detector.facets(facets);

        // Then
        assertEquals(1, facets.size());
        assertTrue(facets.contains("windows"));
    }

    @Test
    public void shouldSetCorrectFacetsForLinux() {
        // Given
        System.setProperty("os.name", "Linux");

        final OperatingSystemFacetDetector detector = new OperatingSystemFacetDetector();
        final Set<String> facets = new HashSet<>();

        // When
        detector.facets(facets);

        // Then
        assertEquals(1, facets.size());
        assertTrue(facets.contains("linux"));
    }

    @Test
    public void shouldSetNoFacetsIfUnableToDetermineOS() {
        // Given
        System.setProperty("os.name", "FooBar");

        final OperatingSystemFacetDetector detector = new OperatingSystemFacetDetector();
        final Set<String> facets = new HashSet<>();

        // When
        detector.facets(facets);

        // Then
        assertTrue(facets.isEmpty());
    }
}
