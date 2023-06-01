package com.dicoding.athleteapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ivAtlit: ImageView = findViewById(R.id.iv_athlete_photo)
        val tvName: TextView = findViewById(R.id.tv_athlete_name)
        val tvName2: TextView = findViewById(R.id.tv_athlete_name2)
        val tvBirth: TextView = findViewById(R.id.tv_athlete_birth)
        val tvSport: TextView = findViewById(R.id.tv_athlete_sport)
        val tvCountry: TextView = findViewById(R.id.tv_athlete_country)
        val tvDesc: TextView = findViewById(R.id.tv_athlete_desc)

        val atlit = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PERSON, Athlete::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PERSON)
        }
        if (atlit != null) {
            val btnShare = findViewById<Button>(R.id.btn_share)
            btnShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, "Biografi ${atlit.name}")
                    putExtra(Intent.EXTRA_TEXT, "${atlit.description}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            // IMAGE
            val imgUrl = "${atlit.foto}"
            Glide.with(this)
                .load(imgUrl)
                .into(ivAtlit)
            // NAME
            val name = "${atlit.name}"
            tvName.text = name
            // NAME
            val name2 = "${atlit.name}"
            tvName2.text = name2
            //BIRTH
            val birth = "${atlit.birth}"
            tvBirth.text = birth
            //COUNTRY
            val country = "${atlit.country}"
            tvCountry.text = country
            // SPORT
            val sport = "${atlit.sports}"
            tvSport.text = sport
            // DESC
            val desc = "${atlit.description}"
            tvDesc.text = desc
        }

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.app_name)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}