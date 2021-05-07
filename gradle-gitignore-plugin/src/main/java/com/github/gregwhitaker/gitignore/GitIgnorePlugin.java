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

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * GitIgnore Gradle Plugin
 */
public class GitIgnorePlugin implements Plugin<Project> {

    /**
     * GitIgnore Gradle task group name.
     */
    public static final String GROUP_NAME = "gitignore";

    @Override
    public void apply(Project project) {
        registerExtension(project);

        // Loading modules after evaluate because we are depending on some extension
        // properties to determine if we should register some tasks or not
        project.afterEvaluate(this::loadModules);
    }

    /**
     * Registers the "gitignore" extension with the project.
     *
     * @param project gradle project
     */
    private void registerExtension(Project project) {
        project.getLogger().info("Registering 'gitignore' extension");

        project.getExtensions().add(GitIgnoreExtension.NAME, new GitIgnoreExtension());
    }

    /**
     * Loads the modules containing tasks and configuration for this plugin.
     *
     * @param project gradle project
     */
    private void loadModules(Project project) {
        GitIgnoreModule.load(project);
    }
}
