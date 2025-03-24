class TFLiteQuantAnalyzer(context: Context) {
    private val edgeTPUDelegate: Delegate? = try {
        EdgeTpuDelegate(EdgeTpuDelegate.Options().apply {
            device = EdgeTpuDelegate.Device.EDGETPU
        })
    } catch (e: IllegalArgumentException) {
        Log.e("EdgeTPUHelper", "Edge TPU not available: ${e.message}")
        null
    }

    private val options = Interpreter.Options().apply {
        numThreads = Runtime.getRuntime().availableProcessors()
        setUseXNNPACK(true)
        edgeTPUDelegate?.let { addDelegate(it) }
    }

    private val interpreter = Interpreter(
        loadModelFile(context, EdgeTPUHelper.getBestModel(context)),
        options
    )

    fun analyze(bitmap: Bitmap): Map<String, Float> {
        val inputBuffer = convertBitmapToByteBuffer(bitmap)
        val output = Array(1) { FloatArray(labels.size) }
        try {
            interpreter.run(inputBuffer, output)
        } catch (e: Exception) {
            Log.e("TFLiteAnalyzer", "Inference error: ${e.message}")
        }
        return processOutput(output[0])
    }
}
