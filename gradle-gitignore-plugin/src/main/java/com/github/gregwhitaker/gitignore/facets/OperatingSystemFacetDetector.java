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
