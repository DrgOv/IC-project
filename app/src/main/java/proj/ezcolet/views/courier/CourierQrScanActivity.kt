package proj.ezcolet.views.courier

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import kotlinx.android.synthetic.main.courier_qr_scan_activity.*
import org.w3c.dom.Text
import proj.ezcolet.R
import proj.ezcolet.databinding.CourierQrScanActivityBinding

private const val CAMERA_REQUEST_CODE = 101

class CourierQrScanActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    //private lateinit var orderInfoTextView: TextView
    //private lateinit var addBtuun: Button
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courier_qr_scan_activity)

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
                            //println("hellooo")
                            if (count == 0) {
                                println(orderInfoTextView.text.toString());
                                count++;
                            }
                        }
                        count = 0;
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        qrScannerView.setOnClickListener {
            codeScanner.startPreview()
        }
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
