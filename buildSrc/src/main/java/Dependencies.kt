import org.gradle.kotlin.dsl.provideDelegate

object Dependencies {
    object ApplicationID {
        const val testLib = "com.achmadss.testlib"
    }
    object AndroidX {
        object Core {
            const val coreKtx = "androidx.core:core-ktx:${Version.Core.version}"
        }
        object Lifecycle {
            const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Lifecycle.version}"
        }
        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:${Version.Activity.version}"
        }
        object Compose {
            object Ui {
                const val ui = "androidx.compose.ui:ui:${Version.Compose.version}"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.Compose.version}"
                const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Version.Compose.version}"
                const val uiTooling = "androidx.compose.ui:ui-tooling:${Version.Compose.version}"
                const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Version.Compose.version}"
            }
            object Material {
                const val material = "androidx.compose.material:material:${Version.Compose.version}"
            }
        }
        object Test {
            object Ext {
                const val junit = "androidx.test.ext:junit:${Version.Test.ExtJunit.version}"
            }
            object Espresso {
                const val core = "androidx.test.espresso:espresso-core:${Version.Espresso.Core.version}"
            }
        }
    }
    object Junit {
        const val junit = "junit:junit:${Version.Junit.version}"
    }
    object Com {
        object Google {
            object Firebase {
                val bom by lazy { "com.google.firebase:firebase-bom:${Version.Firebase.Bom.version}" }
                val auth by lazy { "com.google.firebase:firebase-auth-ktx" }
                val fireStore by lazy { "com.google.firebase:firebase-firestore-ktx" }
                val storage by lazy { "com.google.firebase:firebase-storage-ktx" }
                val messaging by lazy { "com.google.firebase:firebase-messaging-ktx" }
                val crashlytics by lazy { "com.google.firebase:firebase-crashlytics-ktx" }
                val analytics by lazy { "com.google.firebase:firebase-analytics-ktx" }
            }
        }
        object Cexup {
            const val ui = "com.cexup:ui:${Version.Cexup.Ui.version}"
        }
    }
}