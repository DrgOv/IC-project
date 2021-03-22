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


    private var add_order_count = 0
    val delimiter1 = "\n"
    val delimiter2 = "Comanda:"
    val delimiter3 = "Nume:"
    val delimiter4 = "Prenume:"
    val delimiter5 = "Telefon:"
    val delimiter6 = "Strada:"
    val delimiter7 = "Oras:"
    val delimiter8 = "Judet:"
    val delimiter9 = "Suma:"

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

                            if (add_order_count == 0) {
                                val orderDetails = orderInfoTextView.text.split(
                                    delimiter1,
                                    delimiter2,
                                    delimiter3,
                                    delimiter4,
                                    delimiter5,
                                    delimiter6,
                                    delimiter7,
                                    delimiter8,
                                    delimiter9
                                )
                                println(orderDetails[1])
                                println(orderDetails[3])
                                println(orderDetails[5])
                                println(orderDetails[7])
                                println(orderDetails[9])
                                println(orderDetails[11])
                                println(orderDetails[13])
                                println(orderDetails[15])

                                add_order_count++;
                            }
                        }
                        add_order_count = 0;
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
