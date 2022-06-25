# gradle-gitignore-plugin
[![][actions img]][actions]
[![][docs img]][docs]
[![][pluginportal img]][pluginportal]
[![][license img]][license]

Gradle plugin for generating .gitignore files using [gitignore.io](http://gitignore.io).

## Features
* Templates supplied by gitignore.io, so they are always up-to-date.

* Automatic generation of the gitignore file based on the following project facets:
    * Programming Languages
    * Development Environment
    * Operating System
        
* Generation of the gitignore file is completely configurable.

* Support for pulling gitignore templates from locations other than gitignore.io.

## Usage
The plugin can be applied with either the plugin or legacy buildscript DSL. For more information on applying the plugin and available plugin versions please refer to the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore).

### Applying the Plugin with Plugin DSL
```
plugins {
    id "com.github.gregwhitaker.gitignore"    version "2.2.0"
}
```

### Applying the Plugin with Legacy Buildscript DSL
```
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.github.gregwhitaker:gradle-gitignore-plugin:2.2.0"
  }
}

apply plugin: "com.github.gregwhitaker.gitignore"
```

### Simple Configuration
When using the plugin with automatic configuration there is no need for a configuration block.  Simply apply
the plugin, as detailed above, and a `.gitignore` file will be automatically generated.

### Advanced Configuration
The plugin uses *"facets"* to describe what rules to add to the .gitignore file.  These facets correspond to the technology 
selections on the gitignore.io website.

When using advanced configuration the plugin will continue to automatically discover project facets, but you are also able to add 
your own facets to the generation process by using the `facets` configuration parameter.

```$groovy
gitignore {
    facets = [
        'linux',
        'eclipse'
    ]
}   
```

### Manual Configuration
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

### External Configuration
In the event that you do not want to use gitignore.io to generate the .gitignore file you can insert any hosted document 
using the `url` configuration parameter.

```$groovy
gitignore {
    url = 'https://raw.githubusercontent.com/gregwhitaker/gradle-gitignore-plugin/master/src/test/templates/template-gitignore?token=AFw8vOxtg_dhIKrIKO1-aKjhpcvuxB6Kks5YhFcIwA%3D%3D'
}   

```

## Documentation
For detailed documentation on this plugin and its features please refer to the [GitIgnore Gradle Plugin User Guide](https://gregwhitaker.github.io/gradle-gitignore/).

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/gradle-gitignore-plugin/issues).

## License
Copyright 2017-Present Greg Whitaker

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[actions]:https://github.com/gregwhitaker/gradle-gitignore/actions/workflows/gradle-build.yml
[actions img]:https://github.com/gregwhitaker/gradle-gitignore/actions/workflows/gradle-build.yml/badge.svg

[docs]:https://gregwhitaker.github.io/gradle-gitignore/
[docs img]:https://img.shields.io/badge/Documentation-yes-green.svg

[pluginportal]:https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore
[pluginportal img]:https://img.shields.io/badge/Gradle%20Plugin%20Portal-v2.2.0-blue.svg

[license]:LICENSE
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg
