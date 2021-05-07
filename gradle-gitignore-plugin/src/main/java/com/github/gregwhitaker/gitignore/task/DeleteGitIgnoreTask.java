package com.github.gregwhitaker.gitignore.task;

import com.github.gregwhitaker.gitignore.GitIgnorePlugin;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class DeleteGitIgnoreTask extends DefaultTask {

    public DeleteGitIgnoreTask() {
        setGroup(GitIgnorePlugin.GROUP_NAME);
        setDescription("Deletes the .gitignore file from the project.");
    }

    @TaskAction
    public void run() {
        System.out.println("foo");
    }
}
