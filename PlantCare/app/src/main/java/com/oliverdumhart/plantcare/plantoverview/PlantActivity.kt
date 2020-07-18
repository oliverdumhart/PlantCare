package com.oliverdumhart.plantcare.plantoverview

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.oliverdumhart.plantcare.plantinformation.PlantInformationActivity
import com.oliverdumhart.plantcare.R
import com.oliverdumhart.plantcare.databinding.ActivityPlantBinding
import com.oliverdumhart.plantcare.models.Plant

class PlantActivity : AppCompatActivity() {

    companion object {
        const val PLANT_EXTRA = "plant"
    }

    private lateinit var plant: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityPlantBinding>(this, R.layout.activity_plant)
        setSupportActionBar(findViewById(R.id.toolbar))

        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }

        val plant = intent.getParcelableExtra<Plant?>(PLANT_EXTRA)

        if (plant != null) {
            this.plant = plant
        } else {
            //TODO: LOG
            finish()
        }

        val viewModel =
            ViewModelProvider(
                this,
                PlantViewModelFactory(this.plant)
            ).get(PlantViewModel::class.java)
        binding.viewModel = viewModel

        val informationSection = findViewById<ConstraintLayout>(R.id.information_section)
        informationSection.setOnClickListener {
            showInformation()
        }
    }

    private fun showInformation() {
        val intent = Intent(this, PlantInformationActivity::class.java)
        intent.putExtra(PlantInformationActivity.PLANT_ID, this.plant.id)
        startActivity(intent)
    }
}