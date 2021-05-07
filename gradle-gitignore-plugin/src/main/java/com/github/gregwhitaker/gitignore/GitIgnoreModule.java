package com.github.gregwhitaker.gitignore;

import com.github.gregwhitaker.gitignore.tasks.CreateGitIgnoreTask;
import com.github.gregwhitaker.gitignore.tasks.DeleteGitIgnoreTask;
import com.github.gregwhitaker.gitignore.tasks.ListGitIgnoreFacetsTask;
import com.github.gregwhitaker.gitignore.tasks.PrintGitIgnoreTask;
import org.gradle.api.Project;

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
    }
}
