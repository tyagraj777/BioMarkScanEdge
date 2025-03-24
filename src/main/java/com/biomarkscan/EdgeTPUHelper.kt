object EdgeTPUHelper {
    fun isEdgeTPUAvailable(context: Context): Boolean {
        val pm = context.packageManager
        return pm.hasSystemFeature("com.google.android.feature.EDGETPU")
    }

    fun getBestModel(context: Context): String {
        return if (isEdgeTPUAvailable(context)) {
            "fingerprint_edgetpu.tflite"  // 4x faster inference
        } else {
            "fingerprint_quant.tflite"    // Fallback to quantized
        }
    }
}
