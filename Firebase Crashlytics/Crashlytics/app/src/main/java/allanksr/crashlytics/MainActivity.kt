package allanksr.crashlytics

import android.os.Bundle
import android.text.InputFilter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mCrash: FirebaseCrashlytics
    private var from = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        from = packageName.toString()
        mCrash =  FirebaseCrashlytics.getInstance()
        edtTofatalTest.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(20))
        edtTofatalTestCustom.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(20))

        edtTofatalTest.hint = "IllegalArgumentException fatalTest"
        fatalTest.setOnClickListener{
            if(edtTofatalTest.text.toString().isNotEmpty()){
                throw IllegalArgumentException("IllegalArgumentException Text : ${edtTofatalTest.text.toString()}")
            }
        }

        edtTofatalTestCustom.hint = "IllegalArgumentException fatalTestCustom"
        fatalTestCustom.setOnClickListener{
            if(edtTofatalTestCustom.text.toString().isNotEmpty()){
                mCrash.setUserId(from)
                mCrash.setCustomKey("fatalTestCustom",edtTofatalTestCustom.text.toString())

                throw IllegalArgumentException("IllegalArgumentException Text : ${edtTofatalTestCustom.text.toString()}")
            }

        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
