package com.example.buahsayur

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteClassifier(
    assetManager: AssetManager,
    modelPath: String,
    labelPath: String
) {
    private var interpreter: Interpreter
    private var labels: List<String>
    private val imgSize = 100

    init {
        interpreter = Interpreter(loadModel(assetManager, modelPath))
        labels = loadLabels(assetManager, labelPath)
    }

    private fun loadModel(assetManager: AssetManager, path: String): MappedByteBuffer {
        val fileDescriptor: AssetFileDescriptor = assetManager.openFd(path)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        inputStream.use { stream ->
            val fileChannel = stream.channel
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        }
    }

    private fun loadLabels(assetManager: AssetManager, path: String): List<String> {
        assetManager.open(path).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                return reader.readLines()
            }
        }
    }

    fun classify(bitmap: Bitmap): String {
        val resized = Bitmap.createScaledBitmap(bitmap, imgSize, imgSize, true)
        val input = convertBitmapToByteBuffer(resized)
        val output = Array(1) { FloatArray(labels.size) }
        interpreter.run(input, output)

        val maxIdx = output[0].indices.maxByOrNull { output[0][it] } ?: -1
        val confidence = output[0][maxIdx] * 100
        return "${labels[maxIdx]} (${String.format("%.1f", confidence)}%)"
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * imgSize * imgSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val pixels = IntArray(imgSize * imgSize)
        bitmap.getPixels(pixels, 0, imgSize, 0, 0, imgSize, imgSize)
        for (pixel in pixels) {
            byteBuffer.putFloat(((pixel shr 16) and 0xFF) / 255.0f)
            byteBuffer.putFloat(((pixel shr 8) and 0xFF) / 255.0f)
            byteBuffer.putFloat((pixel and 0xFF) / 255.0f)
        }
        return byteBuffer
    }
}
