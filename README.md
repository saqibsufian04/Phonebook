# Phonebook

A Simple Android Library for Toast Message show.

### Step 1. Add the JitPack repository to your settings file

Add it in your root settings.gradle:

```Kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency

```Kotlin
dependencies {
    implementation 'com.github.saqibsufian04:Phonebook:0.1.0'
}
```

### Usage

#### Show Toaster Message

```kotlin
    ToasterMessage().show(context, name)    
```