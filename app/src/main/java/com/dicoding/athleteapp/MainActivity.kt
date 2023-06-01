package com.dicoding.athleteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAthlete: RecyclerView
    private val list = ArrayList<Athlete>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAthlete = findViewById(R.id.rv_athlete)
        rvAthlete.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Athlete> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataFoto = resources.getStringArray(R.array.data_foto)
        val dataBirth = resources.getStringArray(R.array.data_birth)
        val dataCountry = resources.getStringArray(R.array.data_country)
        val dataSports = resources.getStringArray(R.array.data_sport)
        val listAthlete = ArrayList<Athlete>()
        for (i in dataName.indices) {
            val atlet = Athlete(
                dataName[i],
                dataDescription[i],
                dataFoto[i],
                dataBirth[i],
                dataSports[i],
                dataCountry[i]
            )
            listAthlete.add(atlet)
        }
        return listAthlete
    }

    private fun showRecyclerList() {
        rvAthlete.layoutManager = LinearLayoutManager(this)
        val listAthleteAdapter = ListAthleteAdapter(list)
        rvAthlete.adapter = listAthleteAdapter

        listAthleteAdapter.setOnItemClickCallback(object : ListAthleteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Athlete) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })
    }
}