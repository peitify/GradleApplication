package cc.peitify.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction


abstract class ExtensionTask extends DefaultTask {
    @Input
    abstract Property<String> getArguments()

    @TaskAction
    void doTaskAction() {
        println("== ExtensionTask == ${getArguments().get()}")
    }
}