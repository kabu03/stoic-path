package hu.bme.aut.qh8vdn.stoicpath.data

import hu.bme.aut.qh8vdn.stoicpath.R
import hu.bme.aut.qh8vdn.stoicpath.utils.SharedPreferencesHelper

object PhilosopherRepository {

    fun getPhilosophers(sharedPreferencesHelper: SharedPreferencesHelper): List<Philosopher> {
        val favoriteIds = sharedPreferencesHelper.getFavoriteIds()
        return listOf(
            Philosopher(
                id = 1,
                nameResId = R.string.name_marcus_aurelius,
                descriptionResId = R.string.description_marcus_aurelius,
                imageResId = R.drawable.marcus_aurelius,
                isFavorite = favoriteIds.contains(1)
            ),
            Philosopher(
                id = 2,
                nameResId = R.string.name_seneca,
                descriptionResId = R.string.description_seneca,
                imageResId = R.drawable.seneca,
                isFavorite = favoriteIds.contains(2)
            ),
            Philosopher(
                id = 3,
                nameResId = R.string.name_epictetus,
                descriptionResId = R.string.description_epictetus,
                imageResId = R.drawable.epictetus,
                isFavorite = favoriteIds.contains(3)
            ),
            Philosopher(
                id = 4,
                nameResId = R.string.name_zeno,
                descriptionResId = R.string.description_zeno,
                imageResId = R.drawable.zeno_of_citium,
                isFavorite = favoriteIds.contains(4)
            ),
            Philosopher(
                id = 5,
                nameResId = R.string.name_cleanthes,
                descriptionResId = R.string.description_cleanthes,
                imageResId = R.drawable.cleanthes,
                isFavorite = favoriteIds.contains(5)
            ),
            Philosopher(
                id = 6,
                nameResId = R.string.name_chrysippus,
                descriptionResId = R.string.description_chrysippus,
                imageResId = R.drawable.chrysippus,
                isFavorite = favoriteIds.contains(6)

            )
        )
    }

    fun getPhilosopherById(id: Int, sharedPreferencesHelper: SharedPreferencesHelper): Philosopher? {
        val philosophers = getPhilosophers(sharedPreferencesHelper)
        return philosophers.find { it.id == id }
    }
}
