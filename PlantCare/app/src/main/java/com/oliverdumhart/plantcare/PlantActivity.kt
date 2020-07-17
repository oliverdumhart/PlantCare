package com.oliverdumhart.plantcare

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.oliverdumhart.plantcare.models.Plant

class PlantActivity : AppCompatActivity() {

    companion object {
        public const val PLANT_EXTRA = "plant"
    }

    var plant: Plant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
        setSupportActionBar(findViewById(R.id.toolbar))

        plant = intent.getParcelableExtra<Plant>(PLANT_EXTRA)
        plant?.let {
            findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = it.name
        }

        val informationSection = findViewById<ConstraintLayout>(R.id.information_section)
        informationSection.setOnClickListener {
            showInformation()
        }
    }

    private fun showInformation() {
        plant?.let {
            val intent = Intent(this, PlantInformationActivity::class.java)
            intent.putExtra(PlantInformationActivity.PLANT_ID, it.id)
            startActivity(intent)
        }
    }
}