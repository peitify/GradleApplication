package cc.peitify.gradle.dsl

import cc.peitify.gradle.tasks.ExtensionTask
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.Variant
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

class ShowCase {

    static void dslBuildType(AndroidComponentsExtension components) {
        // configure dsl, add build type "Gen"
        components.finalizeDSl({ CommonExtension dsl ->
            BuildType addonBuildType = (BuildType) dsl.buildTypes.create('addon')
            addonBuildType.setJniDebuggable(true)
        })
    }

    static void dslBuildTypeExtension(Project project, ApplicationExtension androidExtension, AndroidComponentsExtension components) {
        androidExtension.buildTypes.all { BuildType addonBuildType ->
            addonBuildType.extensions.add('addonDsl', BuildTypeExtension.class)
        }

        components.onVariants(components.selector().withBuildType('debug')) { Variant variant ->
            println("== onVariants == ${variant.name}")
            ExtensionAware buildTypeDsl = androidExtension.buildTypes.findByName(variant.name)
            BuildTypeExtension buildTypeExtension = buildTypeDsl.extensions.findByName('addonDsl')
            println("== onVariants == ${buildTypeExtension.arguments}")
            project.tasks.register(variant.name + "Dsl", ExtensionTask.class) {
                it.arguments = buildTypeExtension.arguments
                it.doLast {
                    println("== onVariants == ExtensionTask do last")
                }
            }
        }

    }

}

