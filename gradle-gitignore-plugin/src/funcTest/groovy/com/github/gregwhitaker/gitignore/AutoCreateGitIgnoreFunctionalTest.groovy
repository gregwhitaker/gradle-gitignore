package com.github.gregwhitaker.gitignore

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.junit.Assert.assertTrue

class AutoCreateGitIgnoreFunctionalTest extends Specification {

    @Rule TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "should automatically create gitignore file"() {
        given:
        buildFile << """
            plugins {
                id "idea"
                id "java"
                id "application"
                id "com.github.gregwhitaker.gitignore"
            }
        """

        when:
        def result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(testProjectDir.root)
                .build()

        then:
        assertTrue(new File(testProjectDir.root.getAbsolutePath() + "/.gitignore").exists())
    }
}
