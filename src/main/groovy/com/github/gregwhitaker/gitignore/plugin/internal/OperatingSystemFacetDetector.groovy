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

class OperatingSystemFacetDetector {

    // Facets
    private static final String WINDOWS = 'windows'
    private static final String MAC_OS = 'macos'
    private static final String MAC_OSX = 'osx'
    private static final String LINUX = 'linux'

    static void facets(List<String> facets) {
        def os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH)

        if (os.contains('mac') || os.contains('darwin')) {
            facets << [ MAC_OS, MAC_OSX ]
        } else if (os.contains('win')) {
            facets << WINDOWS
        } else if (os.contains('nux')) {
            facets << LINUX
        }
    }

}
