package com.github.gregwhitaker.gitignore

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

class CreateGitIgnoreFromUrlFunctionalTest extends Specification {

    @Rule TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "should create gitignore file from url"() {
        given:
        buildFile << """
            plugins {
                id "idea"
                id "java"
                id "application"
                id "com.github.gregwhitaker.gitignore"
            }
            
            gitignore {
                url = "https://www.toptal.com/developers/gitignore/api/java,gradle"
            }
        """

        def gitignorePath = Paths.get(testProjectDir.root.getAbsolutePath(), ".gitignore")

        when:
        def result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(testProjectDir.root)
                .build()

        then:
        assertTrue(gitignorePath.toFile().exists())

        def contents = Files.readString(gitignorePath)
        assertTrue(contents.contains("### Gradle ###"))
        assertTrue(contents.contains("### Java ###"))
        assertFalse(contents.contains("### macOS ###"))
    }
}
