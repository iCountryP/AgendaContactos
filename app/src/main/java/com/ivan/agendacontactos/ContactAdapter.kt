package com.ivan.agendacontactos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactos: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    // Guarda las referencias a los elementos visuales de cada contacto
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
    }

    // Crea el diseño visual de un contacto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)

        return ContactViewHolder(vista)
    }

    // Coloca los datos del contacto en su posición correspondiente
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contacto = contactos[position]

        holder.tvNombre.text = contacto.nombre
        holder.tvTelefono.text = contacto.telefono
    }

    // Devuelve el número de contactos de la lista
    override fun getItemCount(): Int {
        return contactos.size
    }
}