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
