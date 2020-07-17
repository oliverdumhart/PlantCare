package com.oliverdumhart.plantcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.databinding.ActivityPlantInformationBinding
import com.oliverdumhart.plantcare.models.Plant

class PlantInformationActivity : AppCompatActivity() {

    companion object{
        const val PLANT_ID = "plant_id"
    }

    private lateinit var viewModel : PlantInformationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_information)

        val plantId = intent.getLongExtra(PLANT_ID, 0L)

        val database = PlantDatabase.getInstance(this)
        viewModel = ViewModelProvider(this, PlantInformationViewModelFactory(database, plantId)).get(PlantInformationViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityPlantInformationBinding>(this, R.layout.activity_plant_information)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.finishedEvent.observe(this, Observer {
            if(it){
                finish()
                viewModel.finishedEventFinished()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_plant_information, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.save_changes -> {
            saveChanges()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun saveChanges() {
        viewModel.saveChanges()
    }
}