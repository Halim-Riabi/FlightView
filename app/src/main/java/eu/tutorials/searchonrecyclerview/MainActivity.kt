package eu.tutorials.searchonrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<languageData>()
    private lateinit var adapter: LanguageAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

//        val inflater = layoutInflater
//        val container = findViewById<ConstraintLayout>(R.id.c1) // Replace with your actual container ID
//        val layout = inflater.inflate(R.layout.each_item, container, false)
//
//
//        val button = findViewById<Button>(R.id.btnBooking)
//        button.setOnClickListener {
//            val intent = Intent(this, Sharedprefe::class.java)
//            startActivity(intent)
//            Toast.makeText(this, "Hello MotherFucker", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<languageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList(){
        mList.add(languageData("Flight to France", R.drawable.img_8))
        mList.add(languageData("Flight to Canada", R.drawable.img_1))
        mList.add(languageData("Flight to Sweden", R.drawable.img_2))
        mList.add(languageData("Flight to Belgium", R.drawable.img_3))
        mList.add(languageData("Flight to America", R.drawable.img_4))
        mList.add(languageData("Flight to Suisse", R.drawable.img_5))
        mList.add(languageData("Flight to Italie", R.drawable.img_6))
        mList.add(languageData("Flight to Mexico", R.drawable.img_7))
    }


}