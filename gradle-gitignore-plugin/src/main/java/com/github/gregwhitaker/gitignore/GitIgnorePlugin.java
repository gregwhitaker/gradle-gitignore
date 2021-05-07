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
