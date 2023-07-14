package my.edu.tarc.mobileprac2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //show the screen

        //Declare local variables
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset : Button = findViewById(R.id.buttonReset)
        val imageViewBMI : ImageView = findViewById(R.id.imageViewBMI)

        //any input is treated as string first
        buttonCalculate.setOnClickListener {
            //validate if empty input, if empty print value_required
            if(editTextWeight.text.isBlank()){
                editTextWeight.setError(getString(R.string.value_required))
                //stop program execution here
                return@setOnClickListener
            }

            if(editTextHeight.text.isBlank()){
                editTextHeight.setError(getString(R.string.value_required))
                //stop program execution here
                return@setOnClickListener
            }

            //convert height input to flaot
            val weight = editTextWeight.text.toString().toFloat()
            //convert height input to flaot
            val height = editTextHeight.text.toString().toFloat()

            val bmi = weight / (height/100).pow(2)

            //Display output
            //%s = display text "BMI", %.2f = display number | BMI : 18.25
            textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi),bmi)

            if (bmi < 18.5){ //Underweight
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.underweight))
                imageViewBMI.setImageResource(R.drawable.under)
            }
            else if(bmi > 25){ //Overweight
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.overweight))
                imageViewBMI.setImageResource(R.drawable.over)

            }else{ //Normal
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
                imageViewBMI.setImageResource(R.drawable.normal)
            }
        }

        buttonReset.setOnClickListener {
            editTextHeight.text.clear() //editTextHeight.setText("") also can
            editTextWeight.text.clear()
            textViewBMI.setText(getString(R.string.bmi))
            textViewStatus.setText(getString(R.string.status))
            imageViewBMI.setImageResource(R.drawable.empty)
        }



    }
}