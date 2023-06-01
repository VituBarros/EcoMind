package com.example.ecomind

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ecomind.databinding.ActivityCadastrarDenunciasBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import layout.denuncias

class CadastrarDenuncias : AppCompatActivity() {
        private lateinit var binding : CadastrarDenuncias
        private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = CadastrarDenuncias.inflate(layoutInflater)
        setContentView(binding.root)

        var tituloDenu = binding.tituloDenu
        var detalheDenu = binding.detalheDenu

        dbRef = FirebaseDatabase.getInstance().getReference("Denuncia")

        btncadastro.setOnClickListener {
            var tituloDenu = binding.tituloDenu.text.toString()
            var detalheDenu = binding.detalheDenu.text.toString()

            if (tituloDenu.isEmpty()){
                tituloDenu.error = "Por favor dê um titulo a sua denuncia"
            }
            if (detalheDenu.isEmpty()){
                detalheDenu.error = "Por favor descreva sua denuncia"
            }

            val empId = dbRef.push().key!!

            val denuncia = denuncias(tituloDenu, detalheDenu)

            dbRef.child(empId).setValue(denuncia)
                .addOnCompleteListener{
                    Toast.makeText(this, "Denuncia enviada", Toast.LENGTH_SHORT).show()

                    tituloDenu.text.clear()
                    detalheDenu.text.clear()


                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }

        }
    }
}
}