gradle-gitignore-plugin
===
[![Build Status](https://travis-ci.org/gregwhitaker/gradle-gitignore-plugin.svg?branch=master)](https://travis-ci.org/gregwhitaker/gradle-gitignore-plugin)

Gradle plugin for generating .gitignore files using [gitignore.io](http://gitignore.io).

##Features
* Templates supplied by gitignore.io so they are always up to date.

* Automatic generation of the gitignore file based on the following project facets:
    * Programming Languages
    * Development Environment
    * Operating System
        
* Generation of the gitignore file is completely configurable.

* Support for pulling gitignore templates from locations other than gitignore.io.

##Usage
Please see the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore) for instructions on including this plugin in your project.

###Simple Configuration
When using the plugin with automatic configuration there is no need for a configuration block.  Simply apply 
the plugin, using the instructions in the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore) and it will take care of the rest.

###Enhanced Configuration
The plugin uses *"facets"* to describe what rules to add to the .gitignore file.  These facets correspond to the technology 
selections on the gitignore.io website.

When using enhanced configuration the plugin will continue to automatically discover project facets, but you are able to add 
your own facets to the generation process as well using the `facets` configuration parameter.

```$groovy
gitignore {
    facets = [
        'linux',
        'eclipse'
    ]
}   
```

###Manual Configuration
Automatic discovery of project facets can be disabled allowing you to configure all facets that will be used for generation 
of the .gitignore file by calling the `noAutoDetect()` configuration method.

```$groovy
gitignore {
    noAutoDetect()
    facets = [
        'java',
        'idea',
        'gradle'
    ]
}   
```

###External Configuration
In the event that you do not want to use gitignore.io to generate the .gitignore file you can insert any hosted document 
using the `url` configuration parameter.

```$groovy
gitignore {
    url = 'https://raw.githubusercontent.com/gregwhitaker/gradle-gitignore-plugin/master/src/test/templates/template-gitignore?token=AFw8vOxtg_dhIKrIKO1-aKjhpcvuxB6Kks5YhFcIwA%3D%3D'
}   

```

###Tasks
The plugin exposes the following tasks allowing you to create new .gitignore files, delete existing files, as well as display
the current .gitignore configuration.

Name                 | Description
---------------------|-----------------------------------------
buildGitIgnore       | Generates a .gitignore file for the project. 
cleanGitIgnore       | Deletes the .gitignore file for the project.
displayGitIgnore     | Displays the .gitignore file for the project.


##Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore).

##License
Copyright 2017 Greg Whitaker

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
