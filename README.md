# Phonebook

A Simple Android Library for Contact Picker.

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
    implementation 'com.github.saqibsufian04:phonebook:0.1.0'
}
```

### Usage

#### Get Contact

```kotlin
val contactPicker: ContactPicker? = ContactPicker.create(
    activity = activity,
    onContactPicked = {
        Toast.makeText(activity, "${it.name}: ${it.number}", Toast.LENGTH_LONG).show()
    },
    onFailure = { Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_LONG).show() })

contactPicker?.pick() 
```