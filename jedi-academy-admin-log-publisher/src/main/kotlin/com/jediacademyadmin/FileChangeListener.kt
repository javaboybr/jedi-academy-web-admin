package com.jediacademyadmin

import org.apache.commons.vfs2.FileChangeEvent
import org.apache.commons.vfs2.FileListener
import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.VFS
import org.apache.commons.vfs2.impl.DefaultFileMonitor
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.io.File

@Component
class FileChangeListener(
        val logFile : File = File("c:/server.log"),
        val applicationEventPublisher : ApplicationEventPublisher
) : FileListener {

    lateinit var fm: DefaultFileMonitor

    init {
        startListening()
    }

    @Throws(FileSystemException::class)
    private fun startListening() {
        val fsManager = VFS.getManager()
        val listendir: FileObject = fsManager.toFileObject(logFile)
        fm = DefaultFileMonitor(this)
        fm.addFile(listendir)
        fm.delay = 1000
        fm.start()
    }

    @Throws(Exception::class)
    override fun fileCreated(fce: FileChangeEvent) {
        fileChanged(fce)
    }

    @Throws(Exception::class)
    override fun fileDeleted(fce: FileChangeEvent) {
    }

    @Throws(Exception::class)
    override fun fileChanged(fce: FileChangeEvent) {
        println("fileChanged executed")
    }

}