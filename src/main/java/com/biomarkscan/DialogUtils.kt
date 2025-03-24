object DialogUtils {
    fun showLoadingDialog(context: Context): AlertDialog {
        val progressDialog = AlertDialog.Builder(context)
            .setView(R.layout.dialog_loading)
            .setCancelable(false)
            .create()
        progressDialog.show()
        return progressDialog
    }
}
