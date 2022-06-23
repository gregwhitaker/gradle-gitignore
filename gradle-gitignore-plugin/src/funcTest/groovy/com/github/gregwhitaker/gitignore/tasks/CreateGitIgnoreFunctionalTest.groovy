/*
 * Copyright 2017-Present Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gregwhitaker.gitignore.tasks

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.UnexpectedBuildFailure
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

class CreateGitIgnoreFunctionalTest extends Specification {

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
        assertTrue(contents.contains("### Intellij+all ###"))
        assertTrue(contents.contains("### Java ###"))
    }

    def "should create gitignore with autodetected and manual facets"() {
        given:
        buildFile << """
            plugins {
                id "idea"
                id "java"
                id "application"
                id "com.github.gregwhitaker.gitignore"
            }

            gitignore {
                facets = [ "localstack" ]
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
        assertTrue(contents.contains("### Intellij+all ###"))
        assertTrue(contents.contains("### Java ###"))
        assertTrue(contents.contains("### LocalStack ###"))
    }

    def "should create gitignore file with manual facets only"() {
        given:
        buildFile << """
            plugins {
                id "idea"
                id "java"
                id "application"
                id "com.github.gregwhitaker.gitignore"
            }

            gitignore {
                noAutoDetect()
                facets = [ "localstack" ]
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
        assertTrue(contents.contains("### Gradle ###"))         // Gradle facet is always applied
        assertTrue(contents.contains("### LocalStack ###"))
        assertFalse(contents.contains("### Intellij+all ###"))
        assertFalse(contents.contains("### Java ###"))
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

    def "should abort the build if the gitignore file cannot be downloaded from the given URL"(String url, String expectedMessage) {
        given:
        buildFile << """
            plugins {
                id "idea"
                id "java"
                id "application"
                id "com.github.gregwhitaker.gitignore"
            }

            gitignore {
                url = "${url}"
            }
        """

        when:
        def result = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(testProjectDir.root)
                .build()

        then:
        UnexpectedBuildFailure exception = thrown(UnexpectedBuildFailure)
        exception.buildResult.output.contains(expectedMessage)

        where:
        url                              || expectedMessage
        'http://www.invalid-url.tld/'    || 'Invalid url. [url: \'http://www.invalid-url.tld/\']'
        'http://www.invalid-domain.com/' || 'Error occurred while retrieving data from url. [url: \'http://www.invalid-domain.com/\']'
        'https://httpstat.us/404'        || 'Resource not found. [url: \'https://httpstat.us/404\']'
        'https://httpstat.us/500'        || 'Error occurred while retrieving data from url. [code: \'500\', url: \'https://httpstat.us/500\']'
    }
}
