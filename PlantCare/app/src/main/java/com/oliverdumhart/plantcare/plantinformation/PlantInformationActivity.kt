package com.oliverdumhart.plantcare.plantinformation

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.oliverdumhart.plantcare.R
import com.oliverdumhart.plantcare.database.PlantDatabase
import com.oliverdumhart.plantcare.databinding.ActivityPlantInformationBinding

class PlantInformationActivity : AppCompatActivity() {

    companion object {
        const val PLANT_ID = "plant_id"
    }

    private lateinit var viewModel: PlantInformationViewModel
    private lateinit var binding: ActivityPlantInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_information)

        actionBar?.setHomeButtonEnabled(true)

        val plantId = intent.getLongExtra(PLANT_ID, 0L)

        val database = PlantDatabase.getInstance(this)
        viewModel =
            ViewModelProvider(this,
                PlantInformationViewModelFactory(
                    database,
                    plantId
                )
            ).get(
                PlantInformationViewModel::class.java
            )

        binding = DataBindingUtil.setContentView<ActivityPlantInformationBinding>(
            this,
            R.layout.activity_plant_information
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.finishedEvent.observe(this, Observer {
            if (it) {
                finish()
                viewModel.finishedEventFinished()
            }
        })

        viewModel.pictureEvent.observe(this, Observer {
            if (it) {
                takePicture()
                viewModel.pictureEventFinished()
            }
        })
    }

    private fun takePicture() {
        //TODO: Reuqest code
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {
                startActivityForResult(intent, 1234)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            viewModel.updatePicture(bitmap)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_plant_information, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
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