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

package com.github.gregwhitaker.gitignore.plugin

class GitIgnorePluginExtension {

    boolean autoDetect = true
    String custom
    List<String> facets = new ArrayList<>()

    /**
     * Disables auto-detection of facets.
     */
    def noAutoDetect() {
        autoDetect = false
        return this
    }

    /**
     * Overrides all facets and instead pulls the .gitignore file from the location specified.
     *
     * @param path path to the file to use to override
     */
    def custom(String path) {
        custom = path
        autoDetect = false
        return this
    }

    /**
     * List of facets to use when generating the .gitignore file.
     *
     * @param facets the list of facets
     */
    def facets(String... facets) {
        this.facets << facets
        return this
    }

}
