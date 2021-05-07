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
package com.github.gregwhitaker.gitignore;

import java.util.ArrayList;
import java.util.List;

/**
 * GitIgnore Gradle Plugin extension properties.
 */
public class GitIgnoreExtension {
    public static final String NAME = "gitignore";

    private boolean autoDetect = true;
    private String url;
    private List<String> facets = new ArrayList<>();

    public void noAutoDetect() {
        this.autoDetect = false;
    }

    public boolean isAutoDetect() {
        return autoDetect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        noAutoDetect();
    }

    public List<String> getFacets() {
        return facets;
    }

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }
}
