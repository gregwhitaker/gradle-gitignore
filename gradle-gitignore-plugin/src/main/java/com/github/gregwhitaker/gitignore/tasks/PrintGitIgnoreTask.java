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
