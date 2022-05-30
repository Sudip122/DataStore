package  com.example.datastore1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.datastore1.InventoryApplication
import com.example.datastore1.R
import com.example.datastore1.data.Item
import com.example.datastore1.data.ItemDao
import com.example.datastore1.database.DbAccessObj
import com.example.datastore1.database.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * this activity demonstrates how data can be saved and retreived in android
 */
class MainActivity: AppCompatActivity() {
    lateinit var etTitle: EditText
    lateinit var  etNotes: EditText
    lateinit var itemDao : ItemDao
    lateinit var inventoryApplication : InventoryApplication
    lateinit var dao : DbAccessObj

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = DbAccessObj(this)
        dao.openDb()
        etTitle = findViewById(R.id.etTitle)
        etNotes = findViewById(R.id.etNotes)

    }


    override fun onPause() {
        super.onPause()
        saveData()
    }
    private fun saveData() {
        // TODO: get the data from edittexts
        var title = etTitle.text.toString()
        var notes = etNotes.text.toString()
        //create a file
        var sharedPrefs = getSharedPreferences("harmnanprefs", MODE_PRIVATE)
        //open the file in edit mode
        var editor = sharedPrefs.edit()
        //write to the file
        editor.putString("tkey",title)
        editor.putString("nkey",notes)
        //save the file
        editor.apply()   //apply will save the file asynchronously
    }
    override fun onResume() {
        super.onResume()
        restoreData()
    }
    private fun restoreData() {
        // TODO: open the file
        var sharedPrefs = getSharedPreferences("harmnanprefs", MODE_PRIVATE)
        //read from the file
        var title = sharedPrefs.getString("tkey","")
        var notes = sharedPrefs.getString("nkey","")
        //pu the data back into edittexts
        etTitle.setText(title)
        etNotes.setText(notes)
    }
    fun dbHandler(view: View) {
        val item = Item(1,"sugar",40.0,5)
        when(view.id){
            R.id.btnInsert ->
                //insertItem(item)}
                insertRow()
        }
    }

    private fun insertRow() {
        var title = etTitle.text.toString()
        var subTitle = etNotes.text.toString()
        var note = Note(title,subTitle)
        dao.createRow(note)

    }

    private fun insertItem(item: Item) {

        GlobalScope.launch {
            itemDao =inventoryApplication.database.itemDao()
            itemDao.insert(item) //launch is thread

        }
    }
}