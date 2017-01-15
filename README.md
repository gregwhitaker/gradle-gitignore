gradle-gitignore-plugin
===

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

###Configuration

####Configuring with Automatic Generation
When using the plugin with automatic configuration there is no need for a configuration block.  Simply apply 
the plugin and it will take care of the rest.

```$groovy
plugins {
  id "com.github.gregwhitaker.gitignore" version "1.0.0"
}
```

###Tasks

##Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore).

##License
Copyright 2017 Greg Whitaker

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
