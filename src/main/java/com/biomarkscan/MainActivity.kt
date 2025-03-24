class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadingDialog = DialogUtils.showLoadingDialog(this)
        Handler(Looper.getMainLooper()).postDelayed({
            loadingDialog.dismiss()
        }, 2000)
    }
}
