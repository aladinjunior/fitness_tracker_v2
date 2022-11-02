package co.aladinjunior.tfitstudio

import co.aladinjunior.tfitstudio.models.Fees

interface OnItemClickListener {
    fun onClick(id: Int)

    fun onLongClick(position: Int, fees: Fees)
}