package com.github.gregwhitaker.gitignore;

import com.github.gregwhitaker.gitignore.task.DeleteGitIgnoreTask;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

/**
 * Loads tasks and configuration for GitIgnore plugin.
 */
public class GitIgnoreModule {

    // Task Names
    public static final String DELETE_GITIGNORE_TASK_NAME = "deleteGitIgnore";

    public static void load(Project project) {
        project.getLogger().info("Loading Gitignore Module");

        final Map<String, Class> tasks = new HashMap<>();
        tasks.put(DELETE_GITIGNORE_TASK_NAME, DeleteGitIgnoreTask.class);

        tasks.forEach((name, clazz) -> {
            // Register the default tasks with the project
            project.getTasks().create(name, clazz);
        });
    }
}
