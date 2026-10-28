
package com.ivan.agendacontactos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import android.widget.EditText
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtenemos el RecyclerView de nuestro activity_main.xml
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // lista de contactos
        val contactos = ArrayList<Contact>()

        // nombres
        val nombres = arrayOf(
            "Juan", "Paloma", "José Miguel", "Natalia",
            "Emilio", "Mariano", "Belén", "Lucía",
            "Roberto", "Carlos", "Mauri", "Fernando",
            "Marisa", "Vicenta", "Concha", "Isabel",
            "Andrés", "Alicia", "Armando", "Bea"
        )

        // apellidos
        val apellidos = arrayOf(
            "Cuesta", "Hurtado", "Delgado", "López",
            "Álvarez", "Alonso", "Hidalgo", "Navarro",
            "Benito", "de la Fuente", "Ruiz", "Guerra",
            "Sanz", "Rubio", "Villarejo", "de Haro"
        )

        // mientras no tengamos 40 contactos, metemos uno
        while (contactos.size < 40) {
            val nombre = nombres.random() // coge un valor random del array (nombre)
            val apellido = apellidos.random() // coge un valor random del array (apellido)

            // poner un numx..numy define un rango entre esos dos valores
            // ponerle random hace que coja un valor entre esos dos valores
            // el toString lo convierte a texto
            val telefono = (100000000..999999999).random().toString()

            contactos.add(Contact("$nombre $apellido", telefono))
        }

        // Indicamos que los contactos se muestran en una lista vertical
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Creamos el Adapter y lo guardamos en una variable
        val adapter = ContactAdapter(contactos)

        // Conectamos el Adapter con el RecyclerView
        recyclerView.adapter = adapter

        // Obtenemos el botón + del activity_main.xml
        val btnAgregar = findViewById<FloatingActionButton>(R.id.btnAgregar)

        // Detectamos cuando el usuario pulsa el botón
        btnAgregar.setOnClickListener {

            // Campo para introducir el nombre
            val etNombre = EditText(this)
            etNombre.hint = "Nombre"

            // Campo para introducir el teléfono
            val etTelefono = EditText(this)
            etTelefono.hint = "Teléfono"

            // Creamos un contenedor para colocar los campos
            val layout = LinearLayout(this)

            // Colocamos los campos verticalmente
            layout.orientation = LinearLayout.VERTICAL

            // Añadimos los dos campos al contenedor
            layout.addView(etNombre)
            layout.addView(etTelefono)

            // Creamos la ventana para añadir un contacto
            AlertDialog.Builder(this)
                .setTitle("Añadir contacto")
                .setView(layout)
                .setPositiveButton("Guardar") { _, _ ->

                    // Obtenemos los valores escritos en los campos
                    val nombre = etNombre.text.toString()
                    val telefono = etTelefono.text.toString()

                    // Comprobamos que los dos campos tengan contenido
                    if (nombre.isNotBlank() && telefono.isNotBlank()) {

                        // Creamos el contacto y lo añadimos a la lista
                        contactos.add(Contact(nombre, telefono))

                        // Avisamos al RecyclerView de que hay un contacto nuevo
                        adapter.notifyItemInserted(contactos.size - 1)
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }
}
