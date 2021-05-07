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

import com.github.gregwhitaker.gitignore.tasks.CreateGitIgnoreTask;
import com.github.gregwhitaker.gitignore.tasks.DeleteGitIgnoreTask;
import com.github.gregwhitaker.gitignore.tasks.ListGitIgnoreFacetsTask;
import com.github.gregwhitaker.gitignore.tasks.PrintGitIgnoreTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskCollection;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads tasks and configuration for GitIgnore plugin.
 */
public class GitIgnoreModule {

    // Task Names
    public static final String CREATE_GITIGNORE_TASK_NAME = "createGitIgnore";
    public static final String DELETE_GITIGNORE_TASK_NAME = "deleteGitIgnore";
    public static final String PRINT_GITIGNORE_TASK_NAME = "printGitIgnore";
    public static final String LIST_GITIGNORE_FACETS_TASK_NAME = "listGitIgnoreFacets";

    /**
     * Loads and configures all tasks in the GitIgnore group.
     *
     * @param project gradle project
     */
    public static void load(Project project) {
        project.getLogger().info("Loading Gitignore Module");

        final Map<String, Class> tasks = new HashMap<>();
        tasks.put(CREATE_GITIGNORE_TASK_NAME, CreateGitIgnoreTask.class);
        tasks.put(DELETE_GITIGNORE_TASK_NAME, DeleteGitIgnoreTask.class);
        tasks.put(PRINT_GITIGNORE_TASK_NAME, PrintGitIgnoreTask.class);
        tasks.put(LIST_GITIGNORE_FACETS_TASK_NAME, ListGitIgnoreFacetsTask.class);

        tasks.forEach((name, clazz) -> {
            // Register the default tasks with the project
            project.getTasks().create(name, clazz);
        });

        autoCreateGitIgnoreOnApply(project);
    }

    /**
     * Automatically creates a basic .gitignore file on application of plugin.
     *
     * @param project gradle project
     */
    private static void autoCreateGitIgnoreOnApply(final Project project) {
        final File file = Paths.get(project.getProjectDir().getAbsolutePath(), ".gitignore").toFile();

        if (!file.exists()) {
            final TaskCollection<CreateGitIgnoreTask> createGitIgnoreTasks = project.getTasks().withType(CreateGitIgnoreTask.class);
            final CreateGitIgnoreTask task = createGitIgnoreTasks.getByName(CREATE_GITIGNORE_TASK_NAME);
            task.run();
        }
    }
}
