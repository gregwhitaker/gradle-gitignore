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
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Prints the contents of the ".gitignore" file to the terminal.
 */
public class PrintGitIgnoreTask extends DefaultTask {

    public PrintGitIgnoreTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Prints the .gitignore file for the project.");
    }

    @TaskAction
    public void run() {
        final File file = Paths.get(getProject().getProjectDir().getAbsolutePath(), ".gitignore").toFile();

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                throw new GradleException("Project does not contain a '.gitignore' file.", e);
            }
        } else {
            throw new GradleException("Project does not contain a '.gitignore' file.");
        }
    }
}
