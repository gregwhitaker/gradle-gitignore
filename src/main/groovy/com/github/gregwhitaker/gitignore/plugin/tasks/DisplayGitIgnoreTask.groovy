package com.github.gregwhitaker.gitignore.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.ParallelizableTask

@ParallelizableTask
@CacheableTask
class DisplayGitIgnoreTask extends DefaultTask {

}
