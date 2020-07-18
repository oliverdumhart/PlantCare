package com.oliverdumhart.plantcare.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.oliverdumhart.plantcare.*
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.models.Plant
import com.oliverdumhart.plantcare.main.plantlist.PlantClickListener
import com.oliverdumhart.plantcare.main.plantlist.PlantListAdapter
import com.oliverdumhart.plantcare.plantinformation.PlantInformationActivity
import com.oliverdumhart.plantcare.plantoverview.PlantActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val plantList = findViewById<RecyclerView>(R.id.plant_list)

        val adapter = PlantListAdapter(PlantClickListener {
            viewModel.onPlantClicked(it)
        })

        plantList.adapter = adapter

        val database = PlantDatabase.getInstance(this)

        viewModel =
            ViewModelProvider(this,
                MainViewModelFactory(database)
            ).get(MainViewModel::class.java)

        viewModel.plants.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.addPlantEvent.observe(this, Observer {
            if (it == true) {
                addPlant()
                viewModel.addPlantEventFinished()
            }
        })

        viewModel.showPlantEvent.observe(this, Observer {
            it?.let {
                showPlant(it)
                viewModel.showPlantEventFinished()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_item_add_plant -> {
            viewModel.onAddPlantClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun addPlant() {
        val intent = Intent(this, PlantInformationActivity::class.java)
        intent.putExtra(PlantInformationActivity.PLANT_ID, 0L)
        startActivity(intent)
    }

    private fun showPlant(plant: Plant) {
        val intent = Intent(this, PlantActivity::class.java)
        intent.putExtra(PlantActivity.PLANT_EXTRA, plant)
        startActivity(intent)
    }
}
