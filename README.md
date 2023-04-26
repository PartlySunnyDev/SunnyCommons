# SunnyCommons

[![](https://jitpack.io/v/PartlySunnyDev/SunnyCommons.svg)](https://jitpack.io/#PartlySunnyDev/SunnyCommons)
![](https://img.shields.io/github/languages/top/PartlySunnyDev/SunnyCommons)
![](https://img.shields.io/github/v/release/PartlySunnyDev/SunnyCommons)
![](https://img.shields.io/github/stars/PartlySunnyDev/SunnyCommons?style=social)

## All in one library for developing plugins and mods

Contains many useful classes and utilities for developing plugins and mods. Also contains libraries

- Commandeer https://github.com/PartlySunnyDev/Commandeer
- Configurate https://github.com/PartlySunnyDev/Configurate
- Checkify https://github.com/PartlySunnyDev/Checkify

### Features

- [x] Many useful classes and utilities
- [x] Builders and reflection utils
- [x] Libraries to simplify development
- [x] Easy to use

### Setup

1. Add the jitpack repository to your pom.xml

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url> <!-- Add this repository -->
    </repository>
</repositories>
```

2. Add the dependency to your pom.xml

```xml

<dependencies>
    <dependency>
        <groupId>com.github.PartlySunnyDev</groupId>
        <artifactId>SunnyCommons</artifactId>
        <version>VERSION</version> <!-- Add this dependency -->
    </dependency>
</dependencies>
```

3. Shade the dependency into your plugin

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId> <!-- Add this plugin -->
            <version>3.2.4</version>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>me.partlysunny.commons</pattern>
                        <shadedPattern>your.plugin.package.commons</shadedPattern> <!-- Add this relocation -->
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```
