package com.vastausf.volunteers.di.fragment

import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.VolunteersApplication
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    companion object {
        const val PICASSO_LRU_CACHE = 64000
    }

    @Provides
    fun providePicasso(volunteersApplication: VolunteersApplication): Picasso =
        Picasso
            .Builder(volunteersApplication)
            .memoryCache(LruCache(PICASSO_LRU_CACHE))
            .build()

}