# Discord-Gateway-Listener

[![Generic badge](https://img.shields.io/badge/Java-17-white.svg)]()
[![Generic badge](https://img.shields.io/nexus/r/de.hypercdn.dgl/Discord-Gateway-Listener?color=white&label=Latest%20Version&nexusVersion=3&server=https%3A%2F%2Fcd.voigt.app%2F)]()

## Installation

Grab the latest version from our [Nexus-Proxy](https://nexus.hypercdn.de/) using Gradle or Maven.  
⚠️Be aware that this instance acts as proxy for load distribution.  
⚠️Artifacts might not be listed till requested and the initial load might be very slow.

### Gradle

```groovy
repositories {
    maven {
        name 'maven-hypercdn'
        url 'https://nexus.hypercdn.de/repository/maven-hypercdn'
    }
}
```

```groovy
dependencies {
    implementation group: 'de.hypercdn.dgl', name: 'Discord-Gateway-Listener', version: 'REPLACE WITH LATEST VERSION'
}
```

### Maven

```xml

<repositories>
    <repository>
        <id>maven-hypercdn</id>
        <name>maven-hypercdn</name>
        <url>https://nexus.hypercdn.de/repository/maven-hypercdn</url>
    </repository>
</repositories>
```

```xml

<dependencies>
    <dependency>
        <groupId>de.hypercdn.dgl</groupId>
        <artifactId>Discord-Gateway-Listener</artifactId>
        <version>REPLACE WITH LATEST VERSION</version>
    </dependency>
</dependencies>

```