package com.github.gregwhitaker.gitignore.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.ParallelizableTask
import org.gradle.api.tasks.TaskAction

@ParallelizableTask
@CacheableTask
class DisplayGitIgnoreTask extends DefaultTask {

    @TaskAction
    void run() {
        System.out.println("This is the displayGitIgnore task")
    }

}
