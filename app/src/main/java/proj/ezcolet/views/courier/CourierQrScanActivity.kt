package proj.ezcolet.views.courier

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import kotlinx.android.synthetic.main.courier_qr_scan_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import proj.ezcolet.R
import proj.ezcolet.contracts.CourierQrScanContract
import proj.ezcolet.presenters.courier.CourierQrScanPresenter
import kotlin.coroutines.CoroutineContext

private const val CAMERA_REQUEST_CODE = 101

class CourierQrScanActivity(override val coroutineContext: CoroutineContext = Dispatchers.Main) :
    AppCompatActivity(), CoroutineScope {
    private lateinit var codeScanner: CodeScanner
    private lateinit var courier_qr_scan_Presenter: CourierQrScanContract.Presenter
    private lateinit var username: String
    private var add_order_count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courier_qr_scan_activity)
        courier_qr_scan_Presenter = CourierQrScanPresenter(this)
        username = intent.getStringExtra("Username").toString()

        setupPermissions()
        codeScanner()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, qrScannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    if (it.text != orderInfoTextView.text) {
                        orderInfoTextView.text = it.text
                        var addButton = findViewById<Button>(R.id.addBtn)
                        addButton.setOnClickListener() {
                            launch {
                                addOrderDetails(orderInfoTextView.text.toString())
                            }
                        }

                        add_order_count = 0;
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Eroare la inițializarea camerei: ${it.message}")
                }
            }
        }

        qrScannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private suspend fun addOrderDetails(info: String) {

        if (add_order_count == 0) {

            courier_qr_scan_Presenter.splitOrderInfos(info)
            Toast.makeText(
                this@CourierQrScanActivity,
                "Comandă adăugată",
                Toast.LENGTH_SHORT
            ).show()

            courier_qr_scan_Presenter.addOrderInfo(username)
        }

        if (add_order_count > 0) {
            Toast.makeText(
                this@CourierQrScanActivity,
                "Comanda a fost deja adăugată",
                Toast.LENGTH_SHORT
            ).show()
        }
        add_order_count++;
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "Pentru a putea folosi această funcție trebuie să acceptați permisiunile pentru camera foto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
