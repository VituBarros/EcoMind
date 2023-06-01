package com.example.ecomind

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

    class CadastrarDenuncias : AppCompatActivity() {
        private lateinit var binding : ActivityCadastrarDenunciasBinding
        private lateinit var dbRef: DatabaseReference

class CadastrarDenuncias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = ActivityCadastrarDenunciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tituloDenu = binding.tituloDenu
        var detalheDenu = binding.detalheDenu

        dbRef = FirebaseDatabase.getInstance().getReference("Denuncia")

        enviardenuncia.setOnClickListener {
            var tituloDenu = binding.tituloDenu.text.toString()
            var detalheDenu = binding.detalheDenu.text.toString()

            if (tituloDenu.isEmpty()){
                tituloDenu.error = "Por favor dê um titulo a sua denuncia"
            }
            if (detalheDenu.isEmpty()){
                detalheDenu.error = "Por favor descreva sua denuncia"
            }

            val empId = dbRef.push().key!!

            val denuncia = DenunciaModelo(tituloDenu, detalheDenu)

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