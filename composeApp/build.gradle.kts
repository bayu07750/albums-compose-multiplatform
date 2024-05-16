import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"
    id("androidx.room") version "2.7.0-alpha02"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    val ktorVersion = "2.3.8"
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.7.0-alpha03")

            implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.0")
            implementation("androidx.room:room-runtime:2.7.0-alpha02")
            implementation("androidx.sqlite:sqlite-bundled:2.5.0-alpha02")

            implementation("media.kamel:kamel-image:0.9.4")
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.1")
            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
        }
    }
}

android {
    namespace = "com.bayu07750.albumsplaceholderkmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.bayu07750.albumsplaceholderkmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.bayu07750.albumsplaceholderkmp"
            packageVersion = "1.0.0"
        }
    }
}

dependencies {
    add("kspAndroid", "androidx.room:room-compiler:2.7.0-alpha02")
    add("kspIosSimulatorArm64", "androidx.room:room-compiler:2.7.0-alpha02")
    add("kspIosX64", "androidx.room:room-compiler:2.7.0-alpha02")
    add("kspIosArm64", "androidx.room:room-compiler:2.7.0-alpha02")
    add("kspDesktop", "androidx.room:room-compiler:2.7.0-alpha02")
}

room {
    schemaDirectory("$projectDir/schemas")
}