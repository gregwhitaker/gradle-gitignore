package com.github.gregwhitaker.gitignore.tasks;

import com.github.gregwhitaker.gitignore.GitIgnorePlugin;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class CreateGitIgnoreTask extends DefaultTask {

    public CreateGitIgnoreTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Creates a .gitignore file for the project.");
    }

    @TaskAction
    public void run() {
        System.out.println("createGitIgnore");
    }
}
