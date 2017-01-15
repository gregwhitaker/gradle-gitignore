/*
 * Copyright 2017 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.gregwhitaker.gitignore.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class AutoDetectBuildGitIgnoreFunctionalTest extends Specification {

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
