package com.kzaf.weatherforecastmvvm.data.provider

import com.kzaf.weatherforecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}