package com.github.gregwhitaker.gitignore.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import java.nio.file.Paths

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class CustomBuildGitIgnoreFunctionalTest extends Specification {

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile
    File gitignoreFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        gitignoreFile = Paths.get(testProjectDir.root.absolutePath, '.gitignore').toFile()
    }

    def "buildGitIgnore creates a .gitignore from a custom url"() {
        given:
        buildFile << """
            plugins {
                id 'idea'
                id 'java'
                id 'com.github.gregwhitaker.gitignore'
            }

            gitignore {
                url = 'https://raw.githubusercontent.com/gregwhitaker/gradle-gitignore-plugin/master/src/test/templates/template-gitignore?token=AFw8vOxtg_dhIKrIKO1-aKjhpcvuxB6Kks5YhFcIwA%3D%3D'
            }   
        """

        when:
        def result = GradleRunner.create()
                .withDebug(true)
                .withProjectDir(testProjectDir.root)
                .withArguments('buildGitIgnore')
                .withPluginClasspath()
                .build()

        then:
        gitignoreFile.exists()
        result.task(":buildGitIgnore").outcome == SUCCESS
    }

}
