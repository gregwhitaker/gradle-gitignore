package com.github.gregwhitaker.gitignore.tasks;

import com.github.gregwhitaker.gitignore.GitIgnorePlugin;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class PrintGitIgnoreTask extends DefaultTask {

    public PrintGitIgnoreTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Prints the .gitignore file for the project.");
    }

    @TaskAction
    public void run() {
        System.out.println("printGitIgnore");
    }
}
