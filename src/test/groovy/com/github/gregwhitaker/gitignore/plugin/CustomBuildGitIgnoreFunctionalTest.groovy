package com.github.gregwhitaker.gitignore.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class CustomBuildGitIgnoreFunctionalTest extends Specification {

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "buildGitIgnore creates a .gitignore with facets"() {
        given:
        buildFile << """
            plugins {
                id 'idea'
                id 'java'
                id 'com.github.gregwhitaker.gitignore'
            }

            gitignore {
                custom = 'https://github.com/gregwhitaker/gradle-gitignore-plugin/blob/master/src/test/templates/template-gitignore'
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
        result.task(":buildGitIgnore").outcome == SUCCESS
    }

}
