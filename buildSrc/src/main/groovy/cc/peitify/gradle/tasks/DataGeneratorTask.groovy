package cc.peitify.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class DataGeneratorTask extends DefaultTask {

    @OutputFile
//    abstract RegularFileProperty getData()

    @TaskAction
    void doTaskAction() {
        println("== ${variant.name}GeneratorData doTaskAction ==")
    }
}