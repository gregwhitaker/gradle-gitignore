package com.github.gregwhitaker.gitignore.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class CleanGitIgnoreFunctionalTest extends Specification {

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile
    File gitignoreFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        gitignoreFile = testProjectDir.newFile('.gitignore')
    }

    def "cleanGitIgnore deletes the .gitignore file for the project"() {
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

        gitignoreFile << """
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

        when:
        def result = GradleRunner.create()
                .withDebug(true)
                .withProjectDir(testProjectDir.root)
                .withArguments('cleanGitIgnore')
                .withPluginClasspath()
                .build()

        then:
        !gitignoreFile.exists()
        result.task(":cleanGitIgnore").outcome == SUCCESS
    }

}
