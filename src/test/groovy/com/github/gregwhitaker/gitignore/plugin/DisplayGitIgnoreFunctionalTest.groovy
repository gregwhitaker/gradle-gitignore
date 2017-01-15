package com.github.gregwhitaker.gitignore.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class DisplayGitIgnoreFunctionalTest extends Specification {

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile
    File gitignoreFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        gitignoreFile = testProjectDir.newFile('.gitignore')
    }

    def "displayGitIgnore displays the .gitignore file in the project"() {
        given:
        buildFile << """
            plugins {
                id 'idea'
                id 'java'
                id 'com.github.gregwhitaker.gitignore'
            }

            gitignore {
                facets = [
                    'idea',
                    'java'
                ]
            }   
        """

        def contents = """
            # Created by https://www.gitignore.io/api/java

            ### Java ###
            *.class

            # BlueJ files
            *.ctxt

            # Mobile Tools for Java (J2ME)
            .mtj.tmp/

            # Package Files #
            *.jar
            *.war
            *.ear

            # virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
            hs_err_pid*

            # End of https://www.gitignore.io/api/java
        """

        gitignoreFile << contents

        when:
        def result = GradleRunner.create()
                .withDebug(true)
                .withProjectDir(testProjectDir.root)
                .withArguments('displayGitIgnore')
                .withPluginClasspath()
                .build()

        then:
        result.output.contains(contents)
        result.task(":displayGitIgnore").outcome == SUCCESS
    }

}
