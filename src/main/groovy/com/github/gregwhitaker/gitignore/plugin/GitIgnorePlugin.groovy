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

import com.github.gregwhitaker.gitignore.plugin.tasks.CleanGitIgnoreTask
import com.github.gregwhitaker.gitignore.plugin.tasks.DisplayGitIgnoreTask
import com.github.gregwhitaker.gitignore.plugin.tasks.BuildGitIgnoreTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class GitIgnorePlugin implements Plugin<Project> {
    private static final String GROUP = 'gitignore'

    @Override
    void apply(final Project project) {
        project.extensions.create('gitignore', GitIgnorePluginExtension.class)
        applyTasks(project)
    }

    void applyTasks(final Project project) {
        project.task('buildGitIgnore',
                type: BuildGitIgnoreTask,
                group: GROUP,
                description: 'Generates a .gitignore file for the project.', dependsOn: 'build')

        project.task('cleanGitIgnore',
                type: CleanGitIgnoreTask,
                group: GROUP,
                description: 'Deletes the .gitignore file for the project.')

        project.task('displayGitIgnore',
                type: DisplayGitIgnoreTask,
                group: GROUP,
                description: 'Displays the .gitignore file for the project.')
    }

}
