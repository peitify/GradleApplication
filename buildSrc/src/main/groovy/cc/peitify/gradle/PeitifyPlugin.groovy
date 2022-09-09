package cc.peitify.gradle

import cc.peitify.gradle.dsl.ShowCase
import cc.peitify.gradle.tasks.DataGeneratorTask
import cc.peitify.gradle.tasks.ExtensionTask
import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationVariant
import com.android.build.api.variant.Variant
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer

class PeitifyPlugin implements Plugin<Project> {

    void apply(Project project) {

        ExtensionContainer extensionContainer = project.getExtensions()
        ApplicationExtension androidExtension = extensionContainer.getByType(ApplicationExtension.class)

        AndroidComponentsExtension componentsExtension = extensionContainer.getByType(AndroidComponentsExtension.class)
        project.logger.info("pluginVersion:  ${componentsExtension.pluginVersion}")

        ShowCase.dslBuildType(componentsExtension)

        ShowCase.dslBuildTypeExtension(project, androidExtension, componentsExtension)

        componentsExtension.onVariants(componentsExtension.selector().all()) { Variant variant ->
            def genTask = project.tasks.register(variant.name + "GeneratorData", DataGeneratorTask.class) {
                it.doLast {
                    println("== ${variant.name}GeneratorData ==")
                }

            }

        }

        if (androidExtension instanceof AppExtension) {
            androidExtension.applicationVariants.all { variant ->
                println("== addon 1223== $variant")
                if (variant instanceof ApplicationVariant) {
                    println("== addon variant instanceof ApplicationVariant ==")
                }
            }
        }

    }

}



