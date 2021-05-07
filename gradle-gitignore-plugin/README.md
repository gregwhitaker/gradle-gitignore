# gradle-gitignore-plugin
Gradle plugin for generating .gitignore files using [gitignore.io](http://gitignore.io).

## Features
* Templates supplied by gitignore.io so they are always up to date.

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
    id "com.github.gregwhitaker.gitignore"    version "2.0.0"
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
    classpath "gradle.plugin.com.github.gregwhitaker:gradle-gitignore-plugin:2.0.0"
  }
}

apply plugin: "com.github.gregwhitaker.gitignore"
```

### Simple Configuration
When using the plugin with automatic configuration there is no need for a configuration block.  Simply apply
the plugin, using the instructions in the [Gradle Plugin Portal](https://plugins.gradle.org/plugin/com.github.gregwhitaker.gitignore) and a `.gitignore` file will
be automatically generated.

### Enhanced Configuration
The plugin uses *"facets"* to describe what rules to add to the .gitignore file.  These facets correspond to the technology
selections on the gitignore.io website.

When using enhanced configuration the plugin will continue to automatically discover project facets, but you are also able to add
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

### Tasks
The plugin exposes the following tasks allowing you to create new .gitignore files, delete existing files, as well as display
the current .gitignore configuration.

Name                | Description
--------------------|-----------------------------------------
createGitIgnore     | Creates a .gitignore file for the project.
deleteGitIgnore     | Deletes the .gitignore file for the project.
listGitIgnoreFacets | Lists all of the supported project facets.
printGitIgnore      | Prints the .gitignore file for the project.
