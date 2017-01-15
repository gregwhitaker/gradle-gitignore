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

package com.github.gregwhitaker.gitignore.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.ParallelizableTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Paths

@ParallelizableTask
@CacheableTask
class DisplayGitIgnoreTask extends DefaultTask {

    @TaskAction
    void run() {
        File gitignoreFile = Paths.get(project.projectDir.absolutePath, '.gitignore').toFile()

        if (gitignoreFile.exists()) {
            println(gitignoreFile.text)
        } else {
            throw new GradleException("The project does not contain a .gitignore file to display")
        }
    }

}
