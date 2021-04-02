package proj.ezcolet.views.courier

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.courier_qr_scan_activity.*
import proj.ezcolet.R
import proj.ezcolet.databinding.CourierQrScanActivityBinding

private const val CAMERA_REQUEST_CODE=101

class CourierQrScanActivity : AppCompatActivity()
{
    private lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courier_qr_scan_activity)
        val binding = CourierQrScanActivityBinding.inflate(layoutInflater)
        val input=binding.orderInfoTextView.text.toString()
        println(input);
        println("hello");
        setupPermissions()
        codeScanner()
    }

    private fun codeScanner()
    {
        codeScanner=CodeScanner(this,qrScannerView)
        codeScanner.apply {
            camera= CodeScanner.CAMERA_BACK
            formats=CodeScanner.ALL_FORMATS
            autoFocusMode=AutoFocusMode.SAFE
            scanMode=ScanMode.CONTINUOUS
            isAutoFocusEnabled=true
            isFlashEnabled=false

            decodeCallback= DecodeCallback {
                runOnUiThread {
                    orderInfoTextView.text=it.text
                }
            }

            errorCallback= ErrorCallback {
                runOnUiThread {
                    Log.e("Main","Camera initialization error: ${it.message}")
                }
            }
        }

            qrScannerView.setOnClickListener{
                codeScanner.startPreview()
        }
    }

    override fun onResume()
    {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause()
    {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions()
    {
        val permission= ContextCompat.checkSelfPermission(
            this,android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            makeRequest()
        }
    }

    private fun makeRequest()
    {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
        CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )
    {
        when(requestCode)
        {
            CAMERA_REQUEST_CODE -> {
        if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this,"Pentru a putea folosi aceasta functie trebuie sa acceptati permisiuniile pentru camera foto", Toast.LENGTH_SHORT).show()
        } }
        }
    }
}
