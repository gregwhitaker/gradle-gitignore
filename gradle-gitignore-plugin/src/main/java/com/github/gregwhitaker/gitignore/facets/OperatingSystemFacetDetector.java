package com.github.gregwhitaker.gitignore.facets;

import java.util.Locale;
import java.util.Set;

/**
 * Adds facets based on the operating system.
 */
public class OperatingSystemFacetDetector implements FacetDetector {

    // Facets
    private static final String WINDOWS = "windows";
    private static final String MAC_OS = "macos";
    private static final String MAC_OSX = "osx";
    private static final String LINUX = "linux";

    @Override
    public void facets(Set<String> facets) {
        final String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (os.contains("mac") || os.contains("darwin")) {
            facets.add(MAC_OS);
            facets.add(MAC_OSX);
        } else if (os.contains("win")) {
            facets.add(WINDOWS);
        } else if (os.contains("nux")) {
            facets.add(LINUX);
        }
    }
}
