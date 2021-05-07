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
package com.github.gregwhitaker.gitignore.tasks;

import com.github.gregwhitaker.gitignore.GitIgnorePlugin;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.nio.file.Paths;

/**
 * Deletes the ".gitignore" file from the project directory.
 */
public class DeleteGitIgnoreTask extends DefaultTask {

    public DeleteGitIgnoreTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Deletes the .gitignore file for the project.");
    }

    @TaskAction
    public void run() {
        final File file = Paths.get(getProject().getProjectDir().getAbsolutePath(), ".gitignore").toFile();

        if (file.exists()) {
            file.delete();
            System.out.println("Deleted: " + file.getAbsolutePath());
        } else {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }
}
