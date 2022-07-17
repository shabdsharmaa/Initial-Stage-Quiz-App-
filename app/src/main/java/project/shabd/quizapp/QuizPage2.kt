package project.shabd.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*

class QuizPage2 : AppCompatActivity() {

    lateinit var true_button : Button
    lateinit var false_button : Button
    lateinit var prev_button : ImageButton
    lateinit var next_button : ImageButton
    private var progressBar: ProgressBar? = null
    private var i = 0
    private var txtView: TextView? = null
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_page2)


        true_button = findViewById<Button>(R.id.true_button)
        false_button = findViewById<Button>(R.id.false_button)
        prev_button = findViewById<ImageButton>(R.id.prev_button)
        next_button = findViewById<ImageButton>(R.id.next_button)
        // finding textview by its id
        txtView = findViewById<TextView>(R.id.text_view)
        // finding progressbar by its id
        progressBar = findViewById<ProgressBar>(R.id.pBar) as ProgressBar
        next_button.setOnClickListener {
            // Before clicking the button the progress bar will invisible
            // so we have to change the visibility of the progress bar to visible
            // setting the progressbar visibility to visible
            progressBar!!.visibility = View.VISIBLE

            i = progressBar!!.progress

            Thread(Runnable {
                // this loop will run until the value of i becomes 99
                while (i < 100) {
                    i += 1
                    // Update the progress bar and display the current value
                    handler.post(Runnable {
                        progressBar!!.progress = i
                        // setting current progress to the textview
                        txtView!!.text = i.toString() + "/" + progressBar!!.max
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }

                // setting the visibility of the progressbar to invisible
                // or you can use View.GONE instead of invisible
                // View.GONE will remove the progressbar
                progressBar!!.visibility = View.INVISIBLE

            }).start()

        }
        true_button.setOnClickListener{
            Toast.makeText(this, "Correct Answer!-Click on next to continue", Toast.LENGTH_SHORT).show()
        }
        false_button.setOnClickListener{
            Toast.makeText(this, "Wrong Answer!- Try Again", Toast.LENGTH_SHORT).show()
        }
        prev_button.setOnClickListener{
            Toast.makeText(this, "No previous question", Toast.LENGTH_SHORT).show()
        }
        next_button.setOnClickListener{

            val intent = Intent(this, QuizPage2::class.java)
            startActivity(intent)

        }
    }
}