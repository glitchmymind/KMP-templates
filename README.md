
### Installations

1. install xcode
2. install brew (M1 if need -> google: brew install M1)
3. install cocoapods (M1 if need -> google: install cocoa pods M1 brew)
4. brew install kdoctor (for environment checking)
5. arch x86-64 pod install (for M1)

### Sign commit
documentation: [SignCommit.md](doc%2FSignCommit.md)

#### Check lib versions if don't work 
    - for kotlin and compose https://developer.android.com/jetpack/androidx/releases/compose-kotlin
    - for other lib see github 
---

### Running
#### Android 
```
:androidApp:assembleDebug
```
#### Desktop 
```
```
#### Ios
```
```
#### Web - none

---
### Dependencies
#### BuildSrc (convention gradle plugin)
Common gradle settings for other modules

- [multiplatform-setup.gradle.kts](buildSrc%2Fsrc%2Fmain%2Fkotlin%2Fmultiplatform-setup.gradle.kts) -
config for all platform 

- [multiplatform-compose-setup.gradle.kts](buildSrc%2Fsrc%2Fmain%2Fkotlin%2Fmultiplatform-compose-setup.gradle.kts) - 
config for compose platform (without iOS yet)

- [android-setup.gradle.kts](buildSrc%2Fsrc%2Fmain%2Fkotlin%2Fandroid-setup.gradle.kts) -
config for android only (as native android)

[Dependencies.kt](buildSrc%2FbuildSrc%2Fsrc%2Fmain%2Fkotlin%2FDependencies.kt) - file with list of libraries and versions

---
### Gradle files structure

(e.g. [build.gradle.kts](common%2Fcore%2Fbuild.gradle.kts)) 
<summary>

```kts
pugin {
  id("name of pugin")
  kotlin("kotlin.serialization")
//  Here we must to specify the convention plugin from BuildSrc
}
...
```
</summary>
<details><summary>more...</summary>

```kts
...
kotlin {
    sourceSets {
        commonMain {
            dependencies {
//                dependencies for all platform (all platform have access to it)
            }
        }
        androidMain {
            dependencies {
//                dependencies only for android (Doesn't have access to common)
            }
        }
        iosMain {
            dependencies {
//                dependencies only for ios (Doesn't have access to common)
            }
        }
        desktopMain {
            dependencies {
//                dependencies only for jvm (Doesn't have access to common)
            }
        }
        webMain {
            dependencies {
//                dependencies only for js (web) (Doesn't have access to common)
            }
        }
    }
}
```

</details>

---
### Modules structure

- [androidApp](androidApp) - android native module
- [buildSrc](buildSrc) - module for common gradle settings and versions of dependencies
- [:common](common) - shared root module
    - [:core](common%2Fcore) - common module (network, di, database) for all platform
    - [:compose-uikit](common%2Fcompose-uikit) - UiKit for compose (without iOS yet). Contains UI elements and theme with colors
    - [:dep-compose](common%2Fdep-compose) - common dependencies for compose multiplatform modules
    - [:dep-ios](common%2Fdep-ios) - common dependencies for iOS modules
    - [:features](common%2Ffeatures) - root module for feature modules (auth, payments etc.)
      - [:auth](common%2Ffeatures%2Fauth) - feature module
        - [:api](common%2Ffeatures%2Fauth%2Fapi) - API for other features. Interface only. Models (e.g. get data or screen for navigation)
        - [:domain](common%2Ffeatures%2Fauth%2Fdomain) - repository and data source (REST)
        - [:presentation](common%2Ffeatures%2Fauth%2Fpresentation) - view models, use case
        - [:ui](common%2Ffeatures%2Fauth%2Fui) - multiplatform or native layout (e.g. compose, iOS uiKit)

- [iosApp](iosApp) - native iOS module
  
---
### DI (Kodein)
>[Kodein doc](https://kosi-libs.org/kodein/7.17/index.html) / 
[Kodein repo](https://github.com/kosi-libs/Kodein)

Create module for each dependence e.g.:
```kotlin
internal val serializationModule = DI.Module("serializationModule") {
    bind<Json>() with singleton {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
}
```
[CoreModule.kt](common%2Fcore%2Fsrc%2FcommonMain%2Fkotlin%2FCoreModule.kt) - Container for other modules

---
### Fonts

FontProvider.kt - has expect functions from commonMain resources module

---
#### Issues
- [x] create the readme file
- [ ] add a known issues