package com.example.stoicpath.data

import com.example.stoicpath.R

object PhilosopherRepository {

    fun getPhilosophers(): List<Philosopher> {
        return listOf(
            Philosopher(
                nameResId = R.string.name_marcus_aurelius,
                descriptionResId = R.string.description_marcus_aurelius,
                imageResId = R.drawable.marcus_aurelius
            ),
            Philosopher(
                nameResId = R.string.name_seneca,
                descriptionResId = R.string.description_seneca,
                imageResId = R.drawable.seneca
            ),
            Philosopher(
                nameResId = R.string.name_epictetus,
                descriptionResId = R.string.description_epictetus,
                imageResId = R.drawable.epictetus
            ),
            Philosopher(
                nameResId = R.string.name_zeno,
                descriptionResId = R.string.description_zeno,
                imageResId = R.drawable.zeno_of_citium
            ),
            Philosopher(
                nameResId = R.string.name_cleanthes,
                descriptionResId = R.string.description_cleanthes,
                imageResId = R.drawable.cleanthes
            ),
            Philosopher(
                nameResId = R.string.name_chrysippus,
                descriptionResId = R.string.description_chrysippus,
                imageResId = R.drawable.chrysippus
            )
        )
    }
}
